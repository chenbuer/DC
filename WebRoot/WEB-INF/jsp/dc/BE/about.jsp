<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>
<div class="row">
	<div class="col-md-11">
		<a href="<%=basePath %>be/about/update"><button class="btn btn-danger pull-right">不完美，我要修改</button></a>
	</div>
</div>

<h2 class="text-center">${aboutArticle.aTitle}</h2>
<br>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-1">
		</div>
		<div class="col-md-10">
		${aboutArticle.aContent}
		</div>
		<div class="col-md-1">
		</div>
	</div>
</div>




<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>