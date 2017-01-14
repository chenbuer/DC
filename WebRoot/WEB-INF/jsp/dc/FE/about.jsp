<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>


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