<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>LoginPage</title>
    <meta charset="utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
		*{
			font-family: 微软雅黑;

		}
		.content{
			width: 99%;
			height: 98%;
			background: #0ff;
			/*
			margin-left: auto; //左边距自适应
			margin-top: 0px;   //距离上边距为0
			margin-right: auto;//距离右边距为自适应
			margin-bottom: 0px;//距离下边距为opx
			上面的这段代码可以将这个div块元素放置在中间
			可以简写成
			margin:0px auto 0px auto ==>> 进而转换成
			margin:0px auto;
			*/
			margin:0px auto;
			background-image: url(/i/eg_bg_04.gif);
			position: absolute;
		}
		.login{
			width: 100px;
			height: 100px;
			background: transparent;
			position: relative;
			left: 50%;
			top: 50%;
			margin-left: -50px;
			margin-top: -50px;
		}
	</style>
  </head>
  <body>
  		<c:redirect url="/login.jsp"></c:redirect>
  		
  </body>
 
 
  
</html>
