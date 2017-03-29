package com.wcc.servlet.controlleer;

import java.io.IOException;

import java.lang.reflect.InvocationTargetException;

import java.util.Date;
import java.util.List;

import javassist.bytecode.Opcode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import com.wcc.daomain.Customer;
import com.wcc.service.impl.BusinessServiceImpl;
import com.wcc.uitl.Page;
import com.wcc.uitl.WebUtils;
import com.wcc.web.bean.CustomerFromBean;


public class Controller extends HttpServlet {
	
	private BusinessServiceImpl mServiceImpl = new BusinessServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String  string  = request.getParameter("op");
		// 根据jsp页面中的请求参宿 ，如果客户端请求的要求是显示所有的用户的化，就执行相应的要求
		if ("showAllCustomer".equals(string)) {
			showAllCustomer( request, response);
		}else if("addCustomer".equals(string)){
			addCustomer(request, response);
		}else if("editCustomerUI".equals(string)){
			editCustomerUI(request, response);
		}else if("editCustomer".equals(string)){
			editCustomer(request, response);
		}else if("delOne".equals(string)){
			delOne(request, response);
			return;
		}else if("delMuti".equals(string)){
			delMutile(request, response);
			return;
		}else if("showMain".equals(string)) {
			// 显示主页面
			showMainContent(request, response);
		}
	}

	// 删除多个
	private  void delMutile(HttpServletRequest request, HttpServletResponse response){
		String ids[] = request.getParameterValues("ids");
		if(ids!=null&&ids.length>0){
			for(String id:ids)
				mServiceImpl.deleteCustomer(id);
		}
	}
	// 删除所一个
	private void delOne(HttpServletRequest  request , HttpServletResponse response){
		String customerId = request.getParameter("customerId");
		mServiceImpl.deleteCustomer(customerId);
		System.out.println("这里执行了吗？");
		showAllCustomer(request, response);
		System.out.println("执行了showAllCustom");
		
	}
	
	
	// 显示主要的Content()
	private void showMainContent(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/Main.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 修改客户的信息
	private void editCustomer(HttpServletRequest request,
			HttpServletResponse response) {
		// 其实修改就和 添加操作是一样的。只是在最后的操作不是添加，而是修改
		// 将数据封装到bean 对象中去。
		CustomerFromBean cBean = WebUtils.fillBean(request, CustomerFromBean.class);
		if (!cBean.inilateData()) {
			// 验证出现错误，那么我们就回显数
			request.setAttribute("formBean", cBean);
			// 重定向会用户的注册页面
			try {
				request.getRequestDispatcher("/addCustomer.jsp").forward(request, response);
				return ;
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 将用户的类型进行转换
		Customer customer  = new Customer();
		//单独处理生日日期的
		// 这里的data是 util的data呀。 不是sql 的data 呀 要注意这一点
		ConvertUtils.register(new DateLocaleConverter(), Date.class);
		// 进行拷贝
		try {
			BeanUtils.copyProperties(customer, cBean);
			//处理爱好的字符串
			String hobbies[] =  cBean.getHobbies();
			if (hobbies != null &&  hobbies.length != 0) {
				// 构造一个字符串 
				StringBuffer stringBuffer  =   new   StringBuffer();
				//遍历这个从界面中取得的数组
				for (int i = 0; i < hobbies.length; i++) {
					//在每一个爱好之间用  , 进行分割
					if ( i > 0) {
						stringBuffer.append(",");
					}
					stringBuffer.append(hobbies[i]);
				}
				customer.setHobby(stringBuffer.toString());
			}
			
			// 调用处理业务的服务接口 添加 用户到数据库中
			//mServiceImpl.addCustomer(customer);
			// 做更新的操作了
			mServiceImpl.updateCustomer(customer);
			//提示用户添加用户成功
			//刷新页面，将页面跳转到默认的主页中
			System.out.println("我设置的路径是："  + request.getContextPath());
			response.setHeader("Refresh", "3;URL=" + request.getContextPath() +"/servlet/Controller?op=showAllCustomer");
			
			try {
				response.getWriter().write("客户信息修改成功，欢迎您使用！ 3秒 后进行客户信息列表页面");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	// 提取到指定用的户的信息，然后携带信息，跳转到客户端中去。
	private void editCustomerUI(HttpServletRequest request,
			HttpServletResponse response) {
		//根据在表现层中的id, 取出id之后 交给业务层去查找，查找到的对象再传递给 变现层
		String customerId = request.getParameter("customerId");
		Customer customer = mServiceImpl.findCustomerById(customerId);
		request.setAttribute("c", customer);
		// 转向修改客户信息的界面中
		try {
			request.getRequestDispatcher("/editCustomer.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	// 添加用户
	private void addCustomer(HttpServletRequest request,
			HttpServletResponse response) {
		// 将数据封装到bean 对象中去。
		CustomerFromBean cBean = WebUtils.fillBean(request, CustomerFromBean.class);
		if (!cBean.inilateData()) {
			// 验证出现错误，那么我们就回显数
			request.setAttribute("formBean", cBean);
			// 重定向会用户的注册页面
			try {
				request.getRequestDispatcher("/addCustomer.jsp").forward(request, response);
				return ;
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// 将用户的类型进行转换
		Customer customer  = new Customer();
		//单独处理生日日期的
		// 这里的data是 util的data呀。 不是sql 的data 呀 要注意这一点
		ConvertUtils.register(new DateLocaleConverter(), Date.class);
		// 进行拷贝
		try {
			BeanUtils.copyProperties(customer, cBean);
			
			//处理爱好的字符串
			String hobbies[] =  cBean.getHobbies();
			if (hobbies != null &&  hobbies.length != 0) {
				// 构造一个字符串 
				StringBuffer stringBuffer  =   new   StringBuffer();
				//遍历这个从界面中取得的数组
				for (int i = 0; i < hobbies.length; i++) {
					//在每一个爱好之间用  , 进行分割
					if ( i > 0) {
						stringBuffer.append(",");
					}
					stringBuffer.append(hobbies[i]);
				}
				customer.setHobby(stringBuffer.toString());
			}
			
			// 调用处理业务的服务接口 添加 用户到数据库中
			mServiceImpl.addCustomer(customer);
			
			//提示用户添加用户成功
			//刷新页面，将页面跳转到默认的主页中
			response.setHeader("Refresh", "3;URL=" + request.getContextPath()+"/servlet/Controller?op=showAllCustomer");
			try {
				response.getWriter().write("添加用户成功，欢迎您使用！3秒 后进行客户信息列表页面");
			} catch (IOException e) {
				e.printStackTrace();
			}	
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		//将用户进行添加
		
		//显示添加成功的操作
	}
	// 负责在里面执行显示所有用户的功能的方法
	private void showAllCustomer(HttpServletRequest request,
			HttpServletResponse response) {
		//业务处理层将所得到的集合添加到集合中
		//List<Customer> list = mServiceImpl.findAll();
		//request.setAttribute("customerList", list);
		// 封装到请求对象的域中去
		String num = request.getParameter("num");
		System.out.println("显示的数量是：" + num);
		Page page = mServiceImpl.findPageRecords(num);
		request.setAttribute("page", page);	
		// 将页面转向 显示用户 信息列表的jsp页面中
		try {
			request.getRequestDispatcher("/listCustomer.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
