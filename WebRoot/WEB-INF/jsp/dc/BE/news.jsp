<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>


<style>
body {
	background-image: url("<%=basePath%>img/be_img.jpg");
	background-size: 100% 100%;
	background-repeat: no-repeat;
}
</style>
<script type="text/javascript">
	var url;

	KindEditor.ready(function(K) {
		var editor1 = K.create('#newsContent', {
			cssPath : '${baseUrl}/kindeditor/plugins/code/prettify.css',
			uploadJson : '${baseUrl}/file/upload',
			fileManagerJson : '${baseUrl}/file/manager',
			allowFileManager : true,
			afterCreate : function() {
				this.sync();
			},
			afterChange : function() {
				this.sync();
			},
			afterBlur : function() {
				this.sync();
			}
		});
//		prettyPrint();
	});

	
	function addNews() {
		$('#dlg').dialog('open').dialog('setTitle', '添加新闻');
		$('#fm').form('clear'); 
		url = 'addNews';
	}
	function editNews() {
			$('#dlg').dialog('open').dialog('setTitle', '编辑新闻');
			//fresh the dialog(actually window) with ajax
			$('#fm').form('load', 'qryOneNews/'+$('#dg').datagrid('getSelected').title);
			url = 'editNews';
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
			$.messager.confirm('再想一下', '确定删除这条新闻？', function(r) {
				if (r) {
					$.post('delNews', {
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


<!-- 主要显示的datagrid -->
<table id="dg" title="新闻后台管理" class="easyui-datagrid"
	style="width:1000px;height:280px" url="listNews" toolbar="#toolbar"
	pagination="true" rownumbers="false" fitColumns="true"
	singleSelect="true">
	<thead>
		<tr>
			<th field="title" width="30%">新闻标题</th>
			<th field="descr" width="30%">简要描述</th>
			<th field="createTime" width="30%">创建时间</th>
			<th field="rec" width="10%">是否推送</th>
		</tr>
	</thead>
</table>
<div id="toolbar">
	<a href="javascript:void(0);" class="easyui-linkbutton"
		iconCls="icon-add" plain="true" onclick="addNews()">新增</a> <a href="#"
		class="easyui-linkbutton" iconCls="icon-edit" plain="true"
		onclick="editNews()">编辑</a> <a href="#" class="easyui-linkbutton"
		iconCls="icon-remove" plain="true" onclick="removeNews()">删除</a>
</div>


<!-- 弹出来的对话框 -->
<div id="dlg" class="easyui-dialog"
	style="width:1000px;height:600px;padding:10px 20px" closed="true"
	buttons="#dlg-buttons">
	<div class="ftitle">新闻内容</div>
	<form id="fm" method="post" novalidate>
		<div class="fitem">
			<label>新闻标题:</label>
			<input name="title" class="easyui-textbox" required="true" style="width:400px">
		</div>
		<div class="fitem">
			<label>创建时间:</label> <input class="easyui-datebox" name="createTime" required="true">
		</div>
		<div class="fitem">
			<label>是否推送:</label> <input class="easyui-switchbutton" checked name="rec">
		</div>
		<div class="fitem">
			<label>新闻简介:</label> <br/>
			<input class="easyui-textbox" name="descr" data-options="multiline:true" required="true" style="width:700px; height:80px;">
		</div>
		<div class="fitem">
			<label>正文:</label>
			<textarea id="newsContent" name="content"
				style="width:700px; height:300px;"></textarea>
		</div>
	</form>
</div>
<div id="dlg-buttons">
	<a href="javascript:void(0);" class="easyui-linkbutton"
		iconCls="icon-ok" onclick="saveNews()">保存</a> <a
		href="javascript:void(0);" class="easyui-linkbutton"
		iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
</div>


<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>