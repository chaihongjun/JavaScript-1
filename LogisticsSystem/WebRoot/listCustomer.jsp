<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>客户信息</title>
	 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
	<!--  引入工程路径 下的 css文件  添加样式    -->
	<style type="text/css">
		.content{
			width: 1000px;
			height: 500px;
			text-align: center;
			margin:0px auto;
			padding: 5px;
		}
	
	</style>

  </head>
  <body>
  	<div  class="content">
    	<h1>客户信息</h1>
    	<br>
    	
    	<table  width="100%">
    		<tr>
    			<th>
    				<a href="${pageContext.request.contextPath }/addCustomer.jsp"/>添加</a>
    				<a href="javascript:delMulti()">删除</a>	
    			</th>
    		</tr>
    		<tr>
    			<td>
    				<table  border="1"   width="100%">
    				<tr>
    					<th nowrap="nowrap"><span id="selectAll">全选</span>
    					/<span id="deSelectAll">全不选</span>/
    					<span id="reSelect">反选</span></th>
    						<th nowrap="nowrap">姓名</th>
    						<th nowrap="nowrap">性别</th>
    						<th nowrap="nowrap">生日</th>
    						<th nowrap="nowrap">电话</th>
    						<th nowrap="nowrap">邮箱</th>
    						 <th nowrap="nowrap">爱好</th>
    						<th nowrap="nowrap">类型</th>
    						<th nowrap="nowrap">描述</th>
    						<th nowrap="nowrap">操作</th>
    				</tr>
    				
    				<!--原理显示这里的是 customerList 是将所有的记录都查询出来，但是我们这里是将用户所选择的哪一页选择出来
    					所以的话，这里显示的是 page
    				 -->
    				<c:forEach items="${page.records}" var="c" varStatus="vs">
    						<tr class="${vs.index%2==0?'odd':'even'}">
	    						<td nowrap="nowrap">
	    							<input type="checkbox" name="ids" value="${c.id}">
	    						</td>
	    						<td nowrap="nowrap">${c.name}</td>
	    						<td nowrap="nowrap">${c.gender=='male'?'男':'女' }</td>
	    						<td nowrap="nowrap">${c.birthday }</td>
	    						<td nowrap="nowrap">${c.cellphone }</td>
	    						<td nowrap="nowrap">${c.email }</td>
	    						<td nowrap="nowrap">${c.hobby }</td>
	    						<td nowrap="nowrap">${c.type }</td>
	    						<td nowrap="nowrap">${c.description }</td>
	    						<td nowrap="nowrap">
	    						<!--  请求controller参数 ，将用户所选择的id携带过去，才好根据id查找相对应的客户 -->
	    							[<a href="${pageContext.request.contextPath }/servlet/Controller?op=editCustomerUI&customerId=${c.id}">修改</a>]
	    							
	    							<!-- 这里进行删除操作  -->
	    							[<a href="javascript:delOne('${c.id}')">删除</a>]
	    						</td>
	    					</tr>
    					</c:forEach>
    				</table>			
    			</td>
    			
    		</tr>
    	</table>
    	<!-- 分页有关  开始 -->
    	第 ${page.currentPageNum}页/共 ${page.totalPage}页&nbsp;&nbsp;
    	<a href="${pageContext.request.contextPath }/servlet/Controller?op=showAllCustomer&num=1">首页</a>
    	<a href="${pageContext.request.contextPath }/servlet/Controller?op=showAllCustomer&num=${page.currentPageNum-1> 0 ? page.currentPageNum-1 : 1}">上一页</a>&nbsp;&nbsp;
    	<c:forEach  begin="${page.startPage}"   end="${page.endPage}"   var="num" >
    		
    		<a href="${pageContext.request.contextPath }/servlet/Controller?op=showAllCustomer&num=${num}">${num}&nbsp;&nbsp;</a>
    	
    	</c:forEach>
    	<a  href="${pageContext.request.contextPath }/servlet/Controller?op=showAllCustomer&num=${page.currentPageNum+1 > page.totalPage ? page.totalPage : page.currentPageNum+1}">下一页</a>&nbsp;&nbsp;
    	<a href="${pageContext.request.contextPath }/servlet/Controller?op=showAllCustomer&num=${page.totalPage}">尾页</a>
    	
    </div>
  </body>
  
  <script type="text/javascript">
    		window.onload=function(){
    			document.getElementById("selectAll").onclick=function(){
    				var idsObjArray = document.getElementsByName("ids");
    				for(var i=0;i<idsObjArray.length;i++){
	    				idsObjArray[i].checked=true;
	    			}
    			}
    			document.getElementById("deSelectAll").onclick=function(){
    				var idsObjArray = document.getElementsByName("ids");
    				for(var i=0;i<idsObjArray.length;i++){
	    				idsObjArray[i].checked=false;
	    			}
    			}
    			document.getElementById("reSelect").onclick=function(){
    				var idsObjArray = document.getElementsByName("ids");
    				for(var i=0;i<idsObjArray.length;i++){
	    				idsObjArray[i].checked=!idsObjArray[i].checked;
	    			}
    			}
    			
    		}
    	
    		function delOne(customerId){
    			var sure = window.confirm("确定要删除所选记录吗?");
    			if(sure){
    				window.location.href="${pageContext.request.contextPath}/servlet/Controller?op=delOne&customerId="+customerId;
    			}
    		}
    		function delMulti(){
    			//确定用户有没有选中记录
    			var idsObjArray = document.getElementsByName("ids");
    			var selected = false;//选中标记
    			for(var i=0;i<idsObjArray.length;i++){
    				if(idsObjArray[i].checked){
    					selected=true;
    					break;
    				}
    			}
    			if(!selected){
    				alert("请先选择要删除的记录");
    				return;
    			}
    			//选择了
    			var sure = window.confirm("确定要删除所选记录吗?");
    			if(sure){
    				document.forms[0].submit();
    			}
    		}
    	</script>
</html>
