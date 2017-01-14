<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>
<h2>创建表格成功,但是数据库的内容已经被清空了</h2>
<hr/>

<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>