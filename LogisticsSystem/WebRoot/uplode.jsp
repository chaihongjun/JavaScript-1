<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Uplode Page</title>
   <meta charset="utf-8">
	
	<style type="text/css">
		body{
			background-image: url(demo/img/pic.jpg);
			background-repeat:no-repeat;
			background-size:100%
		}
		.content{
			width: 1000px;
			height: 500px;
			text-align: center;
			margin:0px auto;
			padding: 50px;
			
		}
	</style>
  </head>
  
  <body>
  <div  class = "content">
   	<form action="${pageContext.request.contextPath }/servlet/UpLodeServletDemo"  method="post"
   	enctype="multipart/form-data"
   	>
   		<!-- 姓名: <input type="text"  name = "name"/><br/>-->
   		<div>
   			文件: <input type="file"  name = "phone"><br/>
   		</div>
   		<div>
   			<input type="submit"  value="保存">
   		</div>
   		
   	</form> 
   </div>	
  </body>
</html>
