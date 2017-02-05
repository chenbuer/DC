<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>

<script type="text/javascript">
	var url;
	function newNews() {
		$('#dlg').dialog('open').dialog('setTitle', 'New News');
		$('#fm').form('clear');
		url = 'addNews';
	}
	function editNews() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', 'Edit News');
			$('#fm').form('load', row);
			url = 'news/editNews?id=' + row.id;
		}
	}
	function saveNews() {
		$('#fm').form('submit', {
			url : url,
			onSubmit : function() {
				return $(this).form('validate');
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.success) {
					$.messager.show({
						title : '结果',
						msg : result.msg,
						showType : 'fade',
						style : {
							right : '',
							bottom : ''
						}
					});
					$('#dlg').dialog('close'); // close the dialog  
					$('#dg').datagrid('reload'); // reload the News data  
				} else {
					$.messager.show({
						title : 'Error',
						msg : result.msg
					});
				}
			}
		});
	}
	function removeNews() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm('Confirm',
					'Are you sure you want to remove this News?', function(r) {
						if (r) {
							$.post('remove_News.php', {
								id : row.id
							}, function(result) {
								if (result.success) {
									$('#dg').datagrid('reload'); // reload the News data  
								} else {
									$.messager.show({ // show error message  
										title : 'Error',
										msg : result.msg
									});
								}
							}, 'json');
						}
					});
		}
	}
</script>
<h2>新闻后台管理中心</h2>
<div class="demo-info" style="margin-bottom:10px">
	<div class="demo-tip icon-tip"></div>
	<div>该页面可以对新闻进行编辑，请谨慎操作</div>
</div>

<table id="dg" title="新闻后台管理" class="easyui-datagrid"
	style="width:700px;height:250px" url="listNews"
	toolbar="#toolbar" pagination="true" rownumbers="true"
	fitColumns="true" singleSelect="true">
	<thead>
		<tr>
			<th field="newsId" width="50">ID</th>
			<th field="newsTitle" width="50">新闻标题</th>
			<th field="newsDesc" width="50">简要描述</th>
		</tr>
	</thead>
</table>
<div id="toolbar">
	<a href="javascript:void(0);" class="easyui-linkbutton"	iconCls="icon-add" plain="true" onclick="newNews()">新增</a> 
	<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editNews()">编辑</a> 
	<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeNews()">删除</a>
</div>

<div id="dlg" class="easyui-dialog"
	style="width:400px;height:280px;padding:10px 20px" closed="true"
	buttons="#dlg-buttons">
	<div class="ftitle">新闻内容</div>
	<form id="fm" method="post" novalidate>
		<div class="fitem">
			<label>ID:</label> <input name="id"
				class="easyui-validatebox" required="true">
		</div>
		<div class="fitem">
			<label>新闻标题:</label> <input name="title"
				class="easyui-validatebox" required="true">
		</div>
		<div class="fitem">
			<label>简要描述:</label> <input name="descr">
		</div>
		<div class="fitem">
			<label>内容:</label> <input name="content">
		</div>
	</form>
</div>
<div id="dlg-buttons">
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveNews()">保存</a> 
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
</div>


<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>