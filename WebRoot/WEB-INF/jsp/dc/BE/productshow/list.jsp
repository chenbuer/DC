<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>

<table id="productlist" title="Product Show" class="easyui-datagrid" style="width:700px;height:250px"  
        url="be/productshow/list"  
        toolbar="#toolbar" pagination="true"  
        rownumbers="true" fitColumns="true" singleSelect="true">  
    <thead>  
        <tr>  
            <th field="pId" width="50">序号</th>  
            <th field="pName" width="50">产品名称</th>  
            <th field="pDesc" width="50">产品简介</th>  
            <th field="pPic" width="50">产品 图片</th>  
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