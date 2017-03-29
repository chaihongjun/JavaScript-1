<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>login page</title>
    <meta charset="utf8">
	<style type="text/css">
	
	body{
		background-image:url(demo/img/background.jpg);
   	}
		*{
			font-family: 微软雅黑;
		}		
		.content{
			width: 99%;
			height: 98%;
			margin:0px auto;
			background-repeat:no-repeat;
    		background-position:center;
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
    <div class="content">
				<div  class="login">
					<form action="${pageContext.request.contextPath}/servlet/LoginServlet" method="post">
							<p>用户名(wcc)</p>
							<input type="text" name="username"  value="" id="name">
							<p>密码(123)</p>
							<input type="text" name="password"  value = "" id="pass">
							<!-- <input type="checkbox" name="remember"/>下次自动登录(网吧慎用)<br/>-->
							<p><input type="submit"  value="提交" name=""></p>
					</form>
	 			<div>
	</div>
  </body>
</html>
