<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'lasttiemIn.jsp' starting page</title>
   <!--  
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	-->
	<style type="text/css">
		.clock{
			width: 100%;
			text-align: center;
		}
		.myspan{
			width: 100%;
			height: 200px;
			background: #000;
			color: #f00;
			text-align: center;
			line-height: 100px;
			border-radius: 50px;
			font-weight: bold;
			font-size: 50px;
		}
	</style>
  </head>
  
  <body>
    <div class="clock">
		<span class="myspan">恭喜您！登录成功！<span id="mysapn">5</span>秒后，页面将跳转至控制台......</span>
	</div>
  </body>
  <script type="text/javascript">
	mSpan = document.getElementById('mysapn');
	index = 5;

	//倒计时， 倒计时为0 的时候 页面跳转到百度。
	tiem = setInterval(function TimeOver () {
		mSpan.innerHTML = --index;
		if (index == 0) {
			<!--location='${pageContext.request.contextPath}/Controller' ;-->
			
			location='http://139.129.39.131:8080/LogisticsSystem/servlet/Controller?op=showMain';
			clearInterval(tiem);
		}
	}, 1000);
</script>
  
</html>
