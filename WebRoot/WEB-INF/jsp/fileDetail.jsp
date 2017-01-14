<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'fileDetail.jsp' starting page</title>
</head>

<body>
	上传的文件的名称是：${filename }
	<br>
	图片是:<img width="100" height="100" src="<c:url value="/WEB-INF/image/"/>${filename}">
</body>
</html>
