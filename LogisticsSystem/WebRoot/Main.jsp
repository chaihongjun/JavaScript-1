<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>物流仓储系统</title>	
  </head>
  
  
 <frameset rows="190px,* ">
	<!-- frameset 中的rows 比例是按照在里面的frame来分割分配的，
	第一个frame 分配15% , 剩下的为85% ,如果想在子窗口中再建立子窗口的话
	再建立一个frameset就可以了 -->
	<frame src="top.html" noresize="noresize"  frameborder="0" name = "name">

	<!-- 在框架的里面写东西是显示出不出来的，只有引用外的html文件或者是http：//的这个才是可以的。 -->
			<!-- <a href="www.baidu.com">百度</a><span>|</span><a href="right.html"  target="right">菜单一</a> -->
	</frame>
	<frame src=""  noresize="noresize"  frameborder="0" name="ContentMain">
	</frame>
</frameset>

</html>
