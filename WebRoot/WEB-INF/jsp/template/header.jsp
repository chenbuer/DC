<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>定创科技</title>

<meta name="description"
	content="Source code generated using layoutit.com">
<meta name="author" content="LayoutIt!">

<style type="text/css">
@import
url(<%=basePath %>css/bootstrap.min.css)
</style>
<style type="text/css">
@import
url(<%=basePath %>css/style.css)
</style>
<style type="text/css">
@import
url(<%=basePath %>css/easyui.css)
</style>
<!-- 下面的引入方式也是可以的 -->
<!-- 
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
-->
<script src="<%=basePath%>js/scripts.js"></script>
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="<%=basePath%>easyUI/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>easyUI/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>easyUI/themes/icon.css">
<link rel="shortcut icon" href="<%=basePath %>favicon.ico" type="image/x-icon">

</head>
<body>

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="page-header">
					<h1>
						天津定创科技<small>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;专注改变您的生活。</small>
					</h1>
				</div>
				<nav class="navbar navbar-default" role="navigation">
				<div class="navbar-header">

					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span><span
							class="icon-bar"></span><span class="icon-bar"></span><span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="<%=basePath %>">定创首页</a>
				</div>

				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li><a href="about">关于定创</a></li>
						<li class="dropdown">
							 <a href="#" class="dropdown-toggle" data-toggle="dropdown">产品展示<strong class="caret"></strong></a>
							<ul class="dropdown-menu">							
								<li>
									<a href="<%=basePath %>productshow/list">所有产品</a>
								</li>
								<li class="divider"/>
								<li>
									<a href="<%=basePath %>productshow/1">产品一</a>
								</li>
								<li class="divider"/>
								<li>
									<a href="<%=basePath %>productshow/2">产品二</a>
								</li>
								<li class="divider"/>
								<li>
									<a href="<%=basePath %>productshow/3">产品三</a>
								</li>		
															
							</ul>
						</li>
						<li><a href="<%=basePath %>patent">专利技术</a></li>
						<li><a href="<%=basePath %>news">新闻动态</a></li>
						<li><a href="<%=basePath %>hr">人力资源</a></li>
						<li><a href="<%=basePath %>contact">联系我们</a></li>
					</ul>
<!-- 					<form name="search_form" class="navbar-form navbar-right" role="search_form" target="_blank" onsubmit="return gowhere1(this)">
							<input id="word" type="text" class="form-control" name="word" placeholder="baidu" >
						<button type="submit" class="btn btn-default">百度</button>
					</form>
				</div>
-->
				</nav>
