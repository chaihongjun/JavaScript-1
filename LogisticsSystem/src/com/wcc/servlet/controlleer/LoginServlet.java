package com.wcc.servlet.controlleer;

import java.io.IOException;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wcc.daomain.User;
import com.wcc.sysdao.UserDB;


public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		System.out.println("username" + username);
		
		String password = request.getParameter("password");
		System.out.println("password" + password);
		String remember = request.getParameter("remember");
		User user = UserDB.findUser(username, password);
		if(user!=null){
			/*request.getSession().setAttribute("user", user);
			username = Base64Util.base64encode(username);
			password = MD5Util.md5(password);
			//����cookie
			Cookie cookie = new Cookie("loginInfo", username+"_"+password);//�û���_����
			cookie.setPath(request.getContextPath());//��ǰӦ�ö��ܻ�ȡ��Cookie
			if(remember!=null){
				cookie.setMaxAge(Integer.MAX_VALUE);
			}else{
				cookie.setMaxAge(0);
			}
			response.addCookie(cookie);
			*/
			//response.getWriter().write("用户登入成功");
			System.out.println("request.getContextPath()的路径是：" + request.getContextPath());
			response.setHeader("Refresh", "1;URL="+request.getContextPath() +"/lasttiemIn.jsp");
		}else{
			response.getWriter().write("用户名或密码有错误！ 请重新登入。");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
