package com.wcc.servlet.controlleer;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UpLodeServlet extends HttpServlet {

	protected String storeDirectoryreadPath;  // 存放文件目录的真实路径
	
	/**
	 * 获取初始化参数： 上传文件的存放目录
	 */
	@Override
	public void init() throws ServletException {
		String storeDirectory  = "/upload"; // 默认的存放上传文件位置
		// 我们在web.xml配置文件中进行了上传文件目录的设置，我们在这里进行获取。
		String value = getServletContext().getInitParameter("storeDirectory");
		if(value != null){
			storeDirectory = value;
		}
		//该目录可能不存在
		//那么得到文件的真实路径
		storeDirectoryreadPath = getServletContext().getRealPath(storeDirectory);
		// 判断文件是否存在，如果不存在，就创建它
		File directory = new File(storeDirectoryreadPath);
		if (!directory.exists()) {
			// 如果目录是不存在就直接穿件。
			directory.mkdir();
		}
		
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		//判断用户的请求内容是否是mutlipart/form-data类型的
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			throw new RuntimeException("not a file uplode request");
		}
		//创建DiskFileItemFactory，并设置缓存和临时文件的存放目录
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		//diskFileItemFactory.setRepository(new File("f:/"));
		//创建ServletFileUpload, 核心解析类
		ServletFileUpload  servletFileUpload = new ServletFileUpload(diskFileItemFactory);
	
		// 设置单个上传文件的大小
		servletFileUpload.setFileSizeMax(2*1024*1024);
		// 设置多个上传文件的总大小 
		servletFileUpload.setSizeMax(3*1024*1024);
		
		try {
			// 通过FileUpload对象解析request对象，从中得表单中的提交字段
			List<FileItem> items =  servletFileUpload.parseRequest(request);
			for(FileItem item : items){
				//遇到普通字段:做出自己的处理
				if (item.isFormField()) { 
					processFormField(item);
				}else{
					processUpLoadField(item);
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 处理上传字段的方法
	 * @param item
	 */
	public void processUpLoadField(FileItem item) {
		//获取输入流
		//得到上传文件的文件名    //当用户没有在文件的选择框中进行选择的时候，我们就不能够在这里进行输入的错误。
		if (item.getName() != null && !item.getName().trim().equals("")) {
			System.out.println(item.getName());
			String fileName = getFileName(item.getName());
			try {
				if (fileName != null && !fileName.trim().equals("")) {
					item.write(new File(storeDirectoryreadPath + "/" + fileName));
				}
				// 将上传的文件写入到我们服务器中的磁盘上。
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/*
		 * 这个是传统的做法，但是item中已经有方法直接往里面写就可以了。
		 * //获取输入流
//			InputStream in = item.getInputStream();
			//得到上传文件的文件名
			String fileName = getFileName(item.getName());//  浏览：C:\Users\wzhting\Desktop\a.txt   浏览器：a.txt
			//构建输出流
//			OutputStream out = new FileOutputStream(storeDirectoryRealPath+"/"+fileName);
//			int len = -1;
//			byte b[] = new byte[1024];
//			while((len=in.read(b))!=-1){
//				out.write(b, 0, len);
//			}
//			in.close();
//			out.close();
		 */
	}

	/**
	 * 获取文件的名称
	 * 
	 * 因为上传文件，有的是传过的路径是whole file name 
	 * 但是有的却是 单独的file name ,所以我们要进行设置。
	 * @param name
	 * @return
	 */
	protected  String getFileName(String name) {
		if (name.lastIndexOf("\\") != -1) {
			name = name.substring(name.lastIndexOf("\\") + 1);
		}
		return name;
	}

	/**
	 * 处理普通字段的方法
	 * @param item
	 */
	public void processFormField(FileItem item) {
		String   fieldName = item.getFieldName(); // 获取普通制度按的名称。
		String  fieldValue = item.getString(); // 获取普通字段的值
		
		System.out.println(fieldName+"=" + fieldValue);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
