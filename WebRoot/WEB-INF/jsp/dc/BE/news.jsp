<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>

<style type="text/css">
#fm {
	margin: 0;
	padding: 10px 30px;
}

.ftitle {
	font-size: 14px;
	font-weight: bold;
	color: #666;
	padding: 5px 0;
	margin-bottom: 10px;
	border-bottom: 1px solid #ccc;
}

.fitem {
	margin-bottom: 5px;
}

.fitem label {
	display: inline-block;
	width: 80px;
}
</style>
<script type="text/javascript">
	var url;
	function newNews() {
		$('#dlg').dialog('open').dialog('setTitle', 'New News');
		$('#fm').form('clear');
		url = 'be/news/addNews';
	}
	function editNews() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', 'Edit News');
			$('#fm').form('load', row);
			url = 'be/news/editNews?id=' + row.id;
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
						title : 'Info',
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
<h2>Basic CRUD Application</h2>
<div class="demo-info" style="margin-bottom:10px">
	<div class="demo-tip icon-tip"></div>
	<div>Click the buttons on datagrid toolbar to do crud actions.</div>
</div>

<table id="dg" title="My News" class="easyui-datagrid"
	style="width:700px;height:250px" url="be/news/listNews"
	toolbar="#toolbar" pagination="true" rownumbers="true"
	fitColumns="true" singleSelect="true">
	<thead>
		<tr>
			<th field="newsId" width="50">NewsId</th>
			<th field="newsTitle" width="50">NewsTitle</th>
			<th field="newsDesc" width="50">NewsDesc</th>
		</tr>
	</thead>
</table>
<div id="toolbar">
	<a href="javascript:void(0);" class="easyui-linkbutton"
		iconCls="icon-add" plain="true" onclick="newNews()">New News</a> <a
		href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true"
		onclick="editNews()">Edit News</a> <a href="#"
		class="easyui-linkbutton" iconCls="icon-remove" plain="true"
		onclick="removeNews()">Remove News</a>
</div>

<div id="dlg" class="easyui-dialog"
	style="width:400px;height:280px;padding:10px 20px" closed="true"
	buttons="#dlg-buttons">
	<div class="ftitle">News Information</div>
	<form id="fm" method="post" novalidate>
		<div class="fitem">
			<label>NewsId:</label> <input name="NewsId"
				class="easyui-validatebox" required="true">
		</div>
		<div class="fitem">
			<label>NewsTitle:</label> <input name="NewsTitle"
				class="easyui-validatebox" required="true">
		</div>
		<div class="fitem">
			<label>newsDesc:</label> <input name="newsDesc">
		</div>
	</form>
</div>
<div id="dlg-buttons">
	<a href="javascript:void(0);" class="easyui-linkbutton"
		iconCls="icon-ok" onclick="saveNews()">Save</a> 
	<a href="javascript:void(0);" class="easyui-linkbutton"
		iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
</div>


<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>