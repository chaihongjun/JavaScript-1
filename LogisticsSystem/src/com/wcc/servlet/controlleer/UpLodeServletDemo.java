package com.wcc.servlet.controlleer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

public class UpLodeServletDemo extends UpLodeServlet {
	
	/**
	 * 继承UpLodeServlet 然后就重写里面的方法，执行我们想要做的代码
	 * 对于普通字段的中文乱码问题的解决方案。
	 */
	@Override
	public void processFormField(FileItem item) {
		String nameString =  item.getName();
		try {
			//普通字段值,指定编码，该编码和浏览器使用的编码要对应
			String value = item.getString("utf-8");
			//System.out.println(nameString + "2222=" + value);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	/*
	//按照日期分目录存储文件
	@Override
	protected String getFileName(String fileName) {
		String name = super.getFileName(fileName);// a.txt
		//按照日期创建目录
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String date = df.format(now);
		//拼接该目录的真实存放路径
		String directoryPath = storeDirectoryreadPath+"/"+date;
		File directory = new File(directoryPath);
		if(!directory.exists())
			directory.mkdirs();
		System.out.println("我们输出的是：" + date+"/"+UUID.randomUUID().toString()+"_"+name);
		return date+"/"+UUID.randomUUID().toString()+"_"+name;
	}
	
	*/
	
	//按照UUID文件名的hashCode计算存放目录
		protected String getFileName(String fileName) {
			String name = super.getFileName(fileName);// a.txt
			String uuidFileName = UUID.randomUUID().toString()+"_"+name;// ae427cfa-4920-418a-992a-59553e54a302_a.txt
			
			/*
			hashCode:
				1001 1100 0101 1010 1111 0001
				0000 0000 0000 0000 0000 1111  &0xf
				-----------------------------------
				0000 0000 0000 0000 0000 0001    取hash码的最低4位：0000~1111 十进制：0~15
			hashCode:
				1001 1100 0101 1010 1111 0001
				0000 0000 0000 0000 1111 0000 &0xf0
				-----------------------------------
				0000 0000 0000 0000 1111 0000  取hash码的最低5~8位
				-----------------------------------
							>>4
				0000 0000 0000 0000 0000 1111：0000~1111 十进制：0~15
			 */
			
			int hashCode = uuidFileName.hashCode();
			int dir1 = hashCode&0xf;//一级目录
			int dir2 = (hashCode&0xf0)>>4;//二级目录
			
			String newPath = dir1+"/"+dir2;
			String directoryPath = storeDirectoryreadPath+"/"+newPath;
			File directory = new File(directoryPath);
			
			if(!directory.exists())
				directory.mkdirs();
			
			return newPath+"/"+uuidFileName;
		}
	}
