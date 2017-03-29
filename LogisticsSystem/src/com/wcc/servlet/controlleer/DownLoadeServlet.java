package com.wcc.servlet.controlleer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownLoadeServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uuidfilename = request.getParameter("filename");//get方式，注意编码问题
		//计算文件所在的目录
		String storeDirectory = getServletContext().getRealPath("/WEB-INF/files");
		String path = makeDirs(storeDirectory, uuidfilename);
		File file = new File(storeDirectory+"\\"+path+"\\"+uuidfilename);
		if(file.exists()){
			//通知客户端以下载的方式打开
			
			String filename = uuidfilename.substring(uuidfilename.indexOf("_")+1);//获取老文件名  哈里波波.jpg
			response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(filename, "UTF-8"));
			response.setHeader("Content-Type", "application/octet-stream");//通知客户端正文的MIME类型
			//开始下载
			InputStream in = new FileInputStream(file);
			OutputStream out = response.getOutputStream();
			int len = -1;
			byte b[] = new byte[1024];
			while((len=in.read(b))!=-1){
				out.write(b, 0, len);
			}
			in.close();
		}else{
			response.getOutputStream().write("您下载的资源不存在".getBytes("UTF-8"));
		}
		
	}


	public String makeDirs(String storeDirectoryRealPath,String uuidFilename){
		int hashCode = uuidFilename.hashCode();
		int dir1 = hashCode&0xf;//一级目录
		int dir2 = (hashCode&0xf0)>>4;//二级目录
		
		String newPath = dir1+"/"+dir2;
		String directoryPath = storeDirectoryRealPath+"/"+newPath;
		File directory = new File(directoryPath);
		if(!directory.exists())
			directory.mkdirs();
		return newPath;
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
