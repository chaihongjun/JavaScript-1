<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">    
    <title>ListFile Page</title>
    <style type="text/css">
      .content{
        text-align: center;
      }
    h1:hover{
      text-shadow: 5px 5px 5px #ccc;
      cursor: pointer;
    }
    </style>
  </head>
  
  <body>
  <div  class="content">
      <h1>共享文件有以下的文件：</h1>
       <c:forEach items="${map}" var="me">
      <c:url value="/servlet/DownLoadeServlet" var="url">
        <c:param name="filename" value="${me.key}"></c:param>
      </c:url>
      ${me.value}&nbsp;&nbsp;<a href="${url}">下载</a><br/>
    </c:forEach>
  </div>
    	
  </body>
</html>
