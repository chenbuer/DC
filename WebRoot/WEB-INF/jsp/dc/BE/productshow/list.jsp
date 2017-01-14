<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>

<table id="dg" title="My Users" class="" style="width:550px;height:250px"
		toolbar="#toolbar" idField="id"
		rownumbers="true" fitColumns="true" singleSelect="true">
	<thead>
		<tr>
			<th field="firstname" width="50" editor="{type:'validatebox',options:{required:true}}">First Name</th>
			<th field="lastname" width="50" editor="{type:'validatebox',options:{required:true}}">Last Name</th>
			<th field="phone" width="50" editor="text">Phone</th>
			<th field="email" width="50" editor="{type:'validatebox',options:{validType:'email'}}">Email</th>
		</tr>
	</thead>
</table>
<div id="toolbar">
	<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:$('#dg').edatagrid('addRow')">New</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:$('#dg').edatagrid('destroyRow')">Destroy</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="javascript:$('#dg').edatagrid('saveRow')">Save</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="javascript:$('#dg').edatagrid('cancelRow')">Cancel</a>
</div>
<script type="text/javascript">
$('#dg').edatagrid({
	url: 'get_users.php',
	saveUrl: 'save_user.php',
	updateUrl: 'update_user.php',
	destroyUrl: 'destroy_user.php'
});
</script>
<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>