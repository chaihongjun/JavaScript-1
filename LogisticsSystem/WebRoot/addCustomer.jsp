<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--  将自己定义的tl函数引进来 -->
<%@ taglib uri="http://www.itheima.com/jsp/myfn" prefix="myfn"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<title>添加新客户</title> 
	<!--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">-->
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
  	<div class="mybody1">
    <form    action="${pageContext.request.contextPath}/servlet/Controller?op=addCustomer" method="post">
    	<table border="1">
    		<tr>
    			<td nowrap="nowrap">姓名：</td>
    			<td nowrap="nowrap">
    				<input type="text" name="name" value="${formBean.name}"/>${formBean.errors.name}
    			</td>
    		</tr>
    		<tr>
    			<td nowrap="nowrap">性别：</td>
    			<td nowrap="nowrap">
    				<input type="radio" name="gender" value="male" ${empty formBean?'checked="checked"':(formBean.gender=='male'?'checked="checked"':'') }>男性
    				<input type="radio" name="gender" value="female" ${formBean.gender=='female'?'checked="checked"':'' }>女性
    			</td>
    		</tr>
    		<tr>
    			<td nowrap="nowrap">生日：</td>
    			<td nowrap="nowrap">
    				<input type="text" name="birthday" value="${formBean.birthday}"/>yyyy-MM-dd ${formBean.errors.birthday }
    			</td>
    		</tr>
    		<tr>
    			<td nowrap="nowrap">电话：</td>
    			<td nowrap="nowrap">
    				<input type="text" name="cellphone" value="${formBean.cellphone }"/>${formBean.errors.cellphone }
    			</td>
    		</tr>
    		<tr>
    			<td nowrap="nowrap">邮箱：</td>
    			<td nowrap="nowrap">
    				<input type="text" name="email" value="${formBean.email }"/>${formBean.errors.email }
    			</td>
    		</tr>
    		 <tr>
    			<td nowrap="nowrap">爱好：</td>
    			<td nowrap="nowrap">
    				<input type="checkbox" name="hobbies" value="高尔夫" ${myfn:myfunction(formBean.hobbies,"高尔夫")?'checked="checked"':'' }/>高尔夫
    				<input type="checkbox" name="hobbies" value="麻将" ${myfn:myfunction(formBean.hobbies,"麻将")?'checked="checked"':'' }/>麻将
    				<input type="checkbox" name="hobbies" value="赛马" ${myfn:myfunction(formBean.hobbies,"赛马")?'checked="checked"':'' }/>赛马
    				<input type="checkbox" name="hobbies" value="登山" ${myfn:myfunction(formBean.hobbies,"登山")?'checked="checked"':'' }/>登山
    				<input type="checkbox" name="hobbies" value="旅游" ${myfn:myfunction(formBean.hobbies,"旅游")?'checked="checked"':'' }/>旅游
    			</td>
    		</tr>
    		
    		<tr>
    			<td nowrap="nowrap">类型：</td>
    			<td nowrap="nowrap">
    				<input type="radio" name="type" value="普通客户" ${formBean.type=='普通客户'?'checked="checked"':'' }>普通客户
    				<input type="radio" name="type" value="VIP"  ${formBean.type=='VIP'?'checked="checked"':'' }>VIP
    			</td>
    		</tr>
    		<tr>
    			<td nowrap="nowrap">描述：</td>
    			<td nowrap="nowrap">
    				<textarea rows="3" cols="38" name="description">${formBean.description}</textarea>
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

