<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>


<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link href="<%=basePath%>kindeditor/themes/default/default.css" rel="stylesheet">
<script charset="utf-8" src="<%=basePath%>kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="<%=basePath%>kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript">
	KindEditor.ready(function(K) {
		var editor1 = K.create('#editor_id', {
			cssPath : '${baseUrl}/kindeditor/plugins/code/prettify.css',
			uploadJson : '${baseUrl}/file/upload',
			fileManagerJson : '${baseUrl}/file/manager',
			allowFileManager : true,
			afterCreate : function() {
				this.sync();
			}
		});
		//prettyPrint();
	});
</script>



<form:form commandName="aboutArticle" name="aboutArticle"
	action="${baseUrl}/be/about/update_success" method="post" enctype="multipart/form-data">
	<label>标题:</label>
	<div class="row">
		<div class="col-md-7">
			<form:input path="aTitle" class="form-control"></form:input>
		</div>
	</div>
	<label>内容：</label>
	<form:textarea path="aContent" id="editor_id" name="content"
		style="width:750px;height:300px;" class="pull-right" />
	<br>
	<div class="col-md-7">
		<input type="submit" class="btn btn-danger pull-right"
			value="修改“关于定创”" />
	</div>
</form:form>





<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>