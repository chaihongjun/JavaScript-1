<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--  将自己定义的tl函数引进来 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<title>添加新客户</title> 
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
	<style type="text/css">
		.mybody1{
			width: 30%;
			height: 500px;
			text-align: center;
			padding: 40px;
			margin:0px auto;	
		}
	</style>
  </head>
  
  <body>
  <div class = "mybody1" >
  <!--  这个页面 显示用户要修改的那个用户的信息，因为 将这个对象传递过来了， 这个对象在el表达式中展现出来了 -->
    <form action="${pageContext.request.contextPath}/servlet/Controller?op=editCustomer" method="post">
    <!--  注意 ：  在修改的页面中虽然是没有显示 用户的id  但是我们是要将id 写在表单中 ，以便在 做数据修改中对指定的数据进行修改
    -->
    <input type="hidden"   name="id"  value="${c.id}"   >
    	<table border="1">
    		<tr>
    			<td nowrap="nowrap">姓名：</td>
    			<td nowrap="nowrap">
    				<input type="text" name="name" value="${c.name}"/>
    			</td>
    		</tr>
    		<tr>
    			<td nowrap="nowrap">性别：</td>
    			<td nowrap="nowrap">
    				<input type="radio" name="gender" value="male" ${empty c?'checked="checked"':(c.gender=='male'?'checked="checked"':'') }>男性
    				<input type="radio" name="gender" value="female" ${c.gender=='female'?'checked="checked"':'' }>女性
    			</td>
    		</tr>
    		<tr>
    			<td nowrap="nowrap">生日：</td>
    			<td nowrap="nowrap">
    				<input type="text" name="birthday" value="${c.birthday}"/>yyyy-MM-dd 
    			</td>
    		</tr>
    		<tr>
    			<td nowrap="nowrap">电话：</td>
    			<td nowrap="nowrap">
    				<input type="text" name="cellphone" value="${c.cellphone }"/>
    			</td>
    		</tr>
    		<tr>
    			<td nowrap="nowrap">邮箱：</td>
    			<td nowrap="nowrap">
    				<input type="text" name="email" value="${c.email }"/>
    			</td>
    		</tr>
    		<tr>
    			<td nowrap="nowrap">爱好：</td>
    			<td nowrap="nowrap">
    				<input type="checkbox" name="hobbies" value="吃饭" ${fn:contains(c.hobby,"吃饭")?'checked="checked"':'' }/>吃饭
    				<input type="checkbox" name="hobbies" value="睡觉" ${fn:contains(c.hobby,"睡觉")?'checked="checked"':'' }/>睡觉
    				<input type="checkbox" name="hobbies" value="学java" ${fn:contains(c.hobby,"学java")?'checked="checked"':'' }/>学java
    			</td>
    		</tr>
    		<tr>
    			<td nowrap="nowrap">类型：</td>
    			<td nowrap="nowrap">
    				<input type="radio" name="type" value="普通客户" ${c.type=='普通客户'?'checked="checked"':'' }>普通客户
    				<input type="radio" name="type" value="VIP"  ${c.type=='VIP'?'checked="checked"':'' }>VIP
    			</td>
    		</tr>
    		<tr>
    			<td nowrap="nowrap">描述：</td>
    			<td nowrap="nowrap">
    			<!-- 如果 描述过长的话，那么就直接进行省略 -->
    				<textarea rows="3" cols="38" name="description">${fn:length(c.description) > 3 ?fn:substring(c.descrption, 0, 3):'' }...</textarea>
    			</td>
    		</tr>
    		<tr>
    			<td nowrap="nowrap" colspan="2">
    				<input type="submit" value="保存"/>
    			</td>
    		</tr>
    	</table>
    </form>
    </div>
  </body>
</html>

