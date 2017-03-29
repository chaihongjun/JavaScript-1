<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>西安邮电大学信息管理与信息系统</title>
  
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="demo/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/htmleaf-demo.css">
	<link rel="stylesheet" href="dist/css/pignose.parallaxslider.min.css" />
	
	<style type="text/css">
		.sysTitle{
			text-align: center;
		}
		.hello{
			text-align: center;
		}
		.relTtitle{
			text-align: center;
		}
		.areft{
		 float:right;
		 margin:60px;
		}
		.mytitle1{
			text-align: center;
		}
	</style>
  </head>
 
 <body>
	<div class="htmleaf-container">
		<header class="htmleaf-header">
			<div class="sysTitle">
			<h1>西安邮电大学信息管理与信息系统物流仓储系统</h1>
			</div>
			<div class="sysTitle">
			<h3><span>Xi'an University of Posts and Telecommunications Information Management and Information System Logistics Warehousing System</span></h3>
			</div>
			<div class="hello">
				<a  href="${pageContext.request.contextPath }/login.jsp"  target="_self"><span>登录</span></a>
				<span>____</span>
				
				<a  href=""  target="_blank"><span>注册</span></a>
			</div>
		</header>
		<div id="wrapper">
			<div id="visual">
				<div class="slide-visual">
					<!-- Slide Image Area (1000 x 424) -->
					<ul class="slide-group">
						<li><img src="demo/img/visual_slide01.jpg" alt="slide image" /></li>
						<li><img src="demo/img/visual_slide02.jpg" alt="slide image" /></li>
						<li><img src="demo/img/visual_slide03.jpg" alt="slide image" /></li>
						<li><img src="demo/img/visual_slide04.jpg" alt="slide image" /></li>
						<li><img src="demo/img/visual_slide05.jpg" alt="slide image" /></li>
						<li><img src="demo/img/visual_slide06.jpg" alt="slide image" /></li>
					</ul>

					<!-- Slide Description Image Area (316 x 328) -->
					<div class="script-wrap">
						<ul class="script-group">
							<li><div class="inner-script"><img src="demo/img/visual_slide_script01.jpg" alt="thumbnail slider image" /></div></li>
							<li><div class="inner-script"><img src="demo/img/visual_slide_script02.jpg" alt="thumbnail slider image" /></div></li>
							<li><div class="inner-script"><img src="demo/img/visual_slide_script03.jpg" alt="thumbnail slider image" /></div></li>
							<li><div class="inner-script"><img src="demo/img/visual_slide_script04.jpg" alt="thumbnail slider image" /></div></li>
							<li><div class="inner-script"><img src="demo/img/visual_slide_script05.jpg" alt="thumbnail slider image" /></div></li>
							<li><div class="inner-script"><img src="demo/img/visual_slide_script06.jpg" alt="thumbnail slider image" /></div></li>
						</ul>
						<div class="slide-controller">
							<a href="#" class="btn-prev"><img src="demo/img/btn_prev.png" alt="prev slide" /></a>
							<a href="#" class="btn-play"><img src="demo/img/btn_play.png" alt="start slide" /></a>
							<a href="#" class="btn-pause"><img src="demo/img/btn_pause.png" alt="pause slide" /></a>
							<a href="#" class="btn-next"><img src="demo/img/btn_next.png" alt="next slide" /></a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="rels">
		    <h4 class = "relTtitle">友情链接</h4>
		    <div>
		    <a  class = "areft" href="http://www.baidu.com">
			  <img src="related/baidu.jpg" width="100" alt=""/>
			  <h5 class="mytitle1" >百度</h3>
			</a>
			<a class = "areft" href="http://www.xiyou.edu.cn/">
			  <img src="related/xiyou.jpg" width="100" alt=""/>
			  <h5 class="mytitle1" >西安邮电大学</h3>
			</a>
			<a  class = "areft" href="http://www.ztky.com/">
			  <img src="related/zhongtie.jpg" width="100" alt=""/>
			  <h5 class="mytitle1" >中铁物流</h3>
			</a>
			<a class = "areft" href="http://www.chinapost.com.cn/">
			  <img src="related/youzheng.jpg" width="100" alt=""/>
			  <h5 class="mytitle1" >中国邮政</h3>
			</a>
			<a class = "areft" href="http://www.sf-express.com/cn/sc/">
			  <img src="related/sunfeng.jpg" width="100" alt=""/>
			  <h5 class="mytitle1" >顺丰</h3>
			</a>
			<a  class = "areft" href="http://www.yto.net.cn/gw/index/index.html">
			  <img src="related/yuantong.jpg" width="100" alt=""/>
			  <h5 class="mytitle1" >圆通</h3>
			</a>
			
		</div>
	</div>
	
	<!--<script src="http://libs.useso.com/js/jquery/1.11.0/jquery.min.js" type="text/javascript"></script>-->
	<script>window.jQuery || document.write('<script src="js/jquery-1.11.0.min.js"><\/script>')</script>
	<script type="text/javascript" src="demo/js/jquery.easing.js"></script>
	<script type="text/javascript" src="demo/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="dist/js/pignose.parallaxslider.min.js"></script>
	<script type="text/javascript">
		$(window).load(function() {
			$('#visual').pignoseParallaxSlider({
				play    : '.btn-play',
				pause   : '.btn-pause',
				next    : '.btn-next',
				prev    : '.btn-prev'
			});
		});
	</script>
</body>
</html>
