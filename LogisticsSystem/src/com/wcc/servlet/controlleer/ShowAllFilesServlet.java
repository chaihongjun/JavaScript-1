package com.wcc.servlet.controlleer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowAllFilesServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			
			Map<String, String> map = new HashMap<String, String>();// key:UUID的文件名  value:老文件名 a.txt
			//遍历WEB-INF/files下的所有文件，并把文件名放到map中
			String storeDirectory = getServletContext().getRealPath("/WEB-INF/files");
			File file = new File(storeDirectory);
			treeWalk(file,map);			
			//封装参数，交给JSP显示
			request.setAttribute("map", map);
			request.getRequestDispatcher("/listFile.jsp").forward(request, response);
	}
		
		//递归遍历，找出所有的文件名称
	private void treeWalk(File file, Map<String, String> map) {
			if(file.isFile()){
				String uuidFileName = file.getName();// UUID_a_a.txt
				String fileName =uuidFileName.substring(uuidFileName.indexOf("_")+1);//a_a.txt
				map.put(uuidFileName, fileName);
			}else{
				File[] files = file.listFiles();
				for(File f:files){
					treeWalk(f, map);
				}
			}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
