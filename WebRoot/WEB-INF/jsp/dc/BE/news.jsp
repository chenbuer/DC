<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>
<div class="container">
		<form id="queryForm" class="row">
			<p>
	    		<span class="littleParam">
		    		<label>新闻标题：</label>
					<input type="text" name="title" class="paramInput" style="width:200px;"/>
				</span>
				<span class="littleParam">
					<label>新闻来源：</label>
					<input type="text" name="sfrom" class="paramInput" style="width:200px;"/>
				</span>
			</p>
			<span class="button pull-right">
			 	<input class="btn submit btn-info" type="button" value="搜索" />
			 	<input class="btn reset btn-danger" type="button" value="重置" />
			</span>
		</form>
</div>


		<!-- 数据datagrid -->
 		<table id="datagrid"></table>
 		<!-- 弹出窗口-查看详情 -->
 		<div id="lookWin" class="elegant-aero hiddenWin" >
   		    <form id="lookForm"  method="post">
   		    	<input type="hidden" name="id" id="lookId"/>
		        <label>
		            <span>新闻标题:</span>
		            <input class="easyui-validatebox" type="text" name="title"/>
		        </label>
		        <label>
		            <span>来源:</span>
		            <input class="easyui-validatebox" type="text" name="sfrom"/>
		        </label>
		        <label>
		            <span>编辑者:</span>
		            <input class="easyui-validatebox" type="text" name="editor" />
		        </label>
		        <label>
		            <span>排序:</span>
		            <input class="easyui-validatebox" type="text" name="sort" />
		        </label>
		        <label>
		            <span>描述:</span>
		            <textarea class="textarea easyui-validatebox" name="descr" style="width:document.body.scrollWidth*0.7;height:200px;"></textarea>
		        </label>
		        <label>
		        	<span>新闻图片:</span>
		        	<input class="easyui-validatebox" type="text" name="imgUrl" />
		        	<label class="button myUpload img" style="width:4%;float:right;" opt="{url:CONFIG.url.uploadFile,allowTypes:'jpg,gif,bmp,png',success:'afterDo'}">上传</label>
		        </label>
		        <label>
		            <span>内容:</span>
		            <textarea class="textarea easyui-validatebox" name="content" id="content1" style="width:document.body.scrollWidth*0.7;height:400px;"></textarea>
		        </label>
		        <input class="hidden" type="hidden" id="allFileArray" name="allFileArray" value="">
		    </form>
		</div>
 		<div id="editWin" class="elegant-aero hiddenWin">
 			<form id="editForm"  method="post">
   		    	<input type="hidden" name="id" id="editId"/>
		        <label>
		            <span>新闻标题:</span>
		            <input class="easyui-validatebox" type="text" name="title" data-options="required:true"/>
		        </label>
		        <label>
		            <span>来源:</span>
		            <input class="easyui-validatebox" type="text" name="sfrom" data-options="required:true"/>
		        </label>
		        <label>
		            <span>编辑者:</span>
		            <input class="easyui-validatebox" type="text" name="editor" data-options="required:true"/>
		        </label>
		        <label>
		            <span>排序:</span>
		            <input class="easyui-validatebox" type="text" name="sort" data-options="required:true,validType:['number']"/>
		        </label>
		        <label>
		        	<span>新闻图片:</span>
		        	<input class="easyui-validatebox" type="text" name="imgUrl" />
		        	<label class="button myUpload img" style="width:4%;float:right;" opt="{url:CONFIG.url.uploadFile,allowTypes:'jpg,gif,bmp,png',success:'afterDo'}">上传</label>
		        </label>
		        <label>
		            <span>描述:</span>
		            <textarea class="textarea easyui-validatebox" name="descr" style="width:document.body.scrollWidth*0.7;height:200px;"></textarea>
		        </label>
		         <label>
		            <span>内容:</span>
		            <textarea class="textarea easyui-validatebox" name="content" id="content2" style="width:document.body.scrollWidth*0.7;height:400px;"></textarea>
		        </label>
		        <input class="hidden" type="hidden" id="allFileArray" name="allFileArray" value="">
		        <label class="btns">
					<input type="submit" class="button submit" value="保存" />
					<input type="button" class="button cancel" value="取消" />
				</label>
		    </form>
 		</div>
 		<div id="addWin" class="elegant-aero hiddenWin">
 			<form id="addForm"  method="post">
		        <label>
		            <span>新闻标题:</span>
		            <input class="easyui-validatebox" type="text" name="title" data-options="required:true"/>
		        </label>
		        <label>
		            <span>来源:</span>
		            <input class="easyui-validatebox" type="text" name="sfrom" data-options="required:true"/>
		        </label>
		        <label>
		            <span>编辑者:</span>
		            <input class="easyui-validatebox" type="text" name="editor" data-options="required:true"/>
		        </label>
		        <label>
		            <span>排序:</span>
		            <input class="easyui-validatebox" type="text" name="sort" data-options="required:true,validType:['number']" />
		        </label>
		        <label>
		        	<span>新闻图片:</span>
		        	<input class="easyui-validatebox" type="text" name="imgUrl" />
		        	<label class="button myUpload img" style="width:4%;float:right;" opt="{url:CONFIG.url.uploadFile,allowTypes:'jpg,gif,bmp,png',success:'afterDo'}">上传</label>
		        </label>
		        <label>
		            <span>描述:</span>
		            <textarea class="textarea" name="descr" style="width:document.body.scrollWidth*0.7;height:200px;"></textarea>
		        </label>
		         <label>
		            <span>内容:</span>
		            <textarea class="textarea easyui-validatebox" name="content" id="content3" style="width:document.body.scrollWidth*0.7;height:400px;" data-options="required:true"></textarea>
		        </label>
		        <input class="hidden" type="hidden" id="allFileArray" name="allFileArray" value="">
		        <label class="btns">
					<input type="submit" class="button submit" value="保存" />
					<input type="button" class="button cancel" value="取消" />
				</label>
		    </form>
		    <div class="openDiv imgDiv">
				<img src="" width='295' height='145'/>
			</div>
 		</div>
	

<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>
	<script>
	$.extend($.fn.validatebox.defaults.rules,{
		number:{//验证整数不可为负数
			validator:function(value){
				return /^([+]?[0-9])|([-]?[0-9])+\d*$/i.test(value);
			},
			message:'请输入整数'
		}
	});
	function getFormJson(form) {
		var o = {};
		var a = $(form).serializeArray();
		$.each(a, function () {
			if($.trim(this.value)!=''){
				if (o[this.name] !== undefined) {
					if (!o[this.name].push) {
						o[this.name] = [o[this.name]];
					}
					o[this.name].push(this.value || '');
				} else {
					o[this.name] = this.value || '';
				}
			}
		});
		return o;
	}
	$("#queryForm .submit").click(function(){
		$("#datagrid").datagrid("reload",getFormJson("#queryForm"));
	});
	$("#queryForm .reset").click(function(){
		$("#queryForm")[0].reset();
	});
	function fixWidth(percent){  
	    return ($(document.body).width()-200) * percent ; //这里你可以自己做调整  
	}  
	$('#datagrid').datagrid({
		url:'/be/news/list',
		singleSelect:false,
	    collapsible:true,
	    method:'post',
	    pagination:true,//分页控件 
		rownumbers:true,//行号  
		pageSize: 15,//每页显示的记录条数，默认为10  
	    pageList: [10,15,30,50],//可以设置每页记录条数的列表  
	    columns:[[
	    	{field:'id',title:'全选',width:fixWidth(0.075),align:'center',checkbox:true},
	        {field:'title',title:'新闻标题',width:fixWidth(0.3),align:'center',nowrap:false},
	        {field:'sfrom',title:'新闻来源',width:fixWidth(0.06),align:'center' },
	        {field:'createTime',title:'创建日期',width:fixWidth(0.1),align:'center',nowrap:false},
			{field:'_operate',title:'操作',width:fixWidth(0.15),align:'center',
				formatter: function(value,row,index){
					return '<a type="button" class="opt_edit opt" href="javascript:void(0);" onclick="lookRecord(\''+row.id+'\')">详情</a>'+
						'<a type="button" class="opt_edit opt" href="javascript:void(0);" onclick="editRecord(\''+row.id+'\')">编辑</a>'+
						'<a type="button" class="opt_edit opt" href="javascript:void(0);" onclick="delRecord(\''+row.id+'\')">删除</a>';
				}
			}
	    ]],
	    toolbar: [{
			iconCls: 'icon-add',
			text:"添加",
			handler:addRecord
		},{
			iconCls: 'icon-remove',
			text:"删除",
			handler:function(){
				delRecord(null);
			}
		}]
	});
	//删除没有使用到的已上传到服务器的服务器端文件
	/* var thisPage = false;
	var delUnusedFile = function(ele){
		if(!thisPage){
			var allFileArray = "";
			if(ele==undefined){			
				allFileArray = $("#editWin").find("#allFileArray").val();
				allFileArray += $("#addWin").find("#allFileArray").val();
			}else{
				allFileArray = $(ele).find("#allFileArray").val();
			}
			if(allFileArray!=""){
				$.ajax({
				  type: "post",
				  async: false, 
				  url: "/api/common/file/deleteUnusedFile",
				  data: {'allFileArray':allFileArray},
				  success: function(data){
					thisPage = true;
				  	//只发送请求，不处理返回结果
					$(ele).find("#allFileArray").val("");
				  },
				  dataType: 'json'
				});
			}
		};
	}; */
	$("#lookWin").dialog({
		title:"查看详情",
		width:"40%",
		height:250,
		closed: true,
	    cache: false,
	    shadow:false,
	    iconCls:"",
	    modal: true
	});
	$("#addWin").dialog({
		title:"新增新闻",
		width:"40%",
		height:250,
		closed: true,
	    cache: false,
	    shadow:false,
	    iconCls:"icon-add",
	    modal: true
	});
	$("#editWin").dialog({
		title:"编辑新闻",
		width:"40%",
		height:250,
		closed: true,
	    cache: false,
	    shadow:false,
	    iconCls:"icon-edit",
	    modal: true
	});
	$("#addWin").panel("move",{top:$(document).height()*0.15});
	$("#lookWin").panel("move",{top:$(document).height()*0.15});
	$("#editWin").panel("move",{top:$(document).height()*0.15});
	//查看详情
	function lookRecord(id){
		$("#lookForm").form("clear");
		$.ajax({
			url:'/api/back/news/lookRecord',
			data:{id:id,_d:new Date().getTime()},
			type:'post',
			dataType:'json',
			success:function(data){
  				if(data&&data.success&&data.success==true){
	    			$("#lookForm input[name],#lookForm select[name],#lookForm textarea[name]").each(function(i,ele){
	    				if($(ele).attr("field")!=undefined){
	    					$(ele).val(data["record"][$(ele).attr("field")]);
	    				}else{
		    				$(ele).val(data["record"][$(ele).attr("name")]);
		    				if($(ele).attr("name")=="content"){
	    						var value = data["record"][$(ele).attr("name")];
	    						editor1.html(value);
	    					}	
	    				}
	    			});
	    			$("#lookWin").find("[name='id']").val(id);
			    	$("#lookWin").dialog("open");
		    	}else{
		    		$.messager.alert("信息",data["msg"]?data["msg"]:"当前记录可能不存在，请刷新后重试！","info");
		    	}
			},
			error:function(data){
				$.messager.alert("信息",data["msg"]?data["msg"]:"异常失败，请刷新后重试！","info");
			}
		});
	}
	//编辑
	function editRecord(id){
		$("#editWin").form("clear");
		$.ajax({
			url:'/api/back/news/editRecord',
			data:{id:id,_d:new Date().getTime()},
			type:'post',
			dataType:'json',
			success:function(data){
  				if(data&&data.success&&data.success==true){
	    			$("#editForm input[name],#editForm select[name],#editForm textarea[name]").each(function(i,ele){
	    				if($(ele).attr("field")!=undefined){
	    					$(ele).val(data["record"][$(ele).attr("field")]);
	    				}else{
		    				$(ele).val(data["record"][$(ele).attr("name")]);
		    				if($(ele).attr("name")=="content"){
	    						var value = data["record"][$(ele).attr("name")];
	    						editor2.html(value);
	    					}	
	    				}
	    			});
	    			$("#editWin").find("[name='id']").val(id);
			    	$("#editWin").dialog("open");
		    	}else{
		    		$.messager.alert("信息",data["msg"]?data["msg"]:"当前记录可能不存在，请刷新后重试！","info");
		    	}
			},
			error:function(data){
				$.messager.alert("信息",data["msg"]?data["msg"]:"异常失败，请刷新后重试！","info");
			}
		});
	}
	//新增新闻
	function addRecord(){
		$("#addWin").form("clear");
		$("#addWin").dialog("open");
	};
	//删除记录
	function delRecord(id){
		var rows = $('#datagrid').datagrid('getChecked');
		var ids = '';
		if(id!=null){
			if(rows!=null&&rows.length>0){
				for(var i=0;i<rows.length;i++){
					if(ids==''){
						ids = rows[i].id;
					}else{
						ids = ids+","+rows[i].id;
					}
				}
			}
		}else{
			ids = id;
		}
		$.messager.confirm('Confirm','确认删除吗?',function(r){
			if(r){
				$.ajax({
					url:'/api/back/news/delRecord',
					type:'post',
					data:{ids:ids,_d:new Date().getTime()},
					success:function(data){
						if(data&&data.success&&data.success==true){
							$.messager.alert("信息",data["msg"]?data["msg"]:"删除记录成功！","info",function(){							
								$('#datagrid').datagrid("reload",{});
							});
						}else{
							$.messager.alert("信息",data["msg"]?data["msg"]:"当前记录可能不存在，请刷新后重试！","info");
						}
					},
					error:function(data){
						$.messager.alert("信息",data["msg"]?data["msg"]:"异常失败，请刷新后重试！","info");
					}
				});
			}
		});
	};
	var editor1;
	KindEditor.ready(function(K) {
		editor1 = KindEditor.create("#content1", {
			id:'editor1',
			resizeType : 0,
			allowPreviewEmoticons : false,
			allowImageUpload : true,//容许上传图片
			allowImgFileExts:['jpg','png','bmp','gif'],//图片格式
			fillDescAfterUploadImage:true,
			minWidth:'500px',
			minHeight:'400px',
			width:'400px',
			height:'200px',
			allowUpload : true, //允许上传文件
			allowFileExts:["doc","ppt","pdf","rar","xls","xlsx"],//文件格式
			uploadJson : '/api/back/uploadFile/kindeditorUploadFile', //服务端上传图片处理URI
			beforeUpload : function(obj,allowFileExts){//文件上传前的操作
				if(allowFileExts&&allowFileExts.length>0){
					var _isAlow = false;
					var _fileExt = obj.val().lastIndexOf(".");
					_fileExt  = obj.val().substr(_fileExt+1).toLocaleLowerCase();
					for(fileExt in allowFileExts){
						if(allowFileExts[fileExt]==_fileExt){
							_isAlow = true;
						}
					}
					if(!_isAlow){
						alert("当前格式的文件不支持，仅支持格式："+allowFileExts.join(","));
						return false;
					}
				}
				return true;
			},
			afterUpload : function(url,data,name) {//图片上传后
				$("#allFileArray").val($("#allFileArray").val()==""?url:$("#allFileArray").val()+","+url);
			},
			items : [
				'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
				'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
				'insertunorderedlist','formatblock', '|', 'emoticons', 'image', 'insertfile','|', 'preview']
		});
	});
	var editor2;
	KindEditor.ready(function(K) {
		editor2 = KindEditor.create("#content2", {
			id:'editor2',
			resizeType : 0,
			allowPreviewEmoticons : false,
			allowImageUpload : true,//容许上传图片
			allowImgFileExts:['jpg','png','bmp','gif'],//图片格式
			fillDescAfterUploadImage:true,
			minWidth:'500px',
			minHeight:'400px',
			width:'400px',
			height:'200px',
			allowUpload : true, //允许上传文件
			allowFileExts:["doc","ppt","pdf","rar","xls","xlsx"],//文件格式
			uploadJson : '/api/back/uploadFile/kindeditorUploadFile', //服务端上传图片处理URI
			beforeUpload : function(obj,allowFileExts){//文件上传前的操作
				if(allowFileExts&&allowFileExts.length>0){
					var _isAlow = false;
					var _fileExt = obj.val().lastIndexOf(".");
					_fileExt  = obj.val().substr(_fileExt+1).toLocaleLowerCase();
					for(fileExt in allowFileExts){
						if(allowFileExts[fileExt]==_fileExt){
							_isAlow = true;
						}
					}
					if(!_isAlow){
						alert("当前格式的文件不支持，仅支持格式："+allowFileExts.join(","));
						return false;
					}
				}
				return true;
			},
			afterUpload : function(url,data,name) {//图片上传后
				$("#allFileArray").val($("#allFileArray").val()==""?url:$("#allFileArray").val()+","+url);
			},
			items : [
				'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
				'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
				'insertunorderedlist','formatblock', '|', 'emoticons', 'image', 'insertfile','|', 'preview']
		});
	});
		
	var editor3;
	KindEditor.ready(function(K) {
		editor3 = KindEditor.create("#content3", {
			id:"editor3",
			resizeType : 0,
			allowPreviewEmoticons : false,
			allowImageUpload : true,//容许上传图片
			allowImgFileExts:['jpg','png','bmp','gif'],//图片格式
			fillDescAfterUploadImage:true,
			minWidth:'500px',
			minHeight:'400px',
			width:'400px',
			height:'200px',
			allowUpload : true, //允许上传文件
			allowFileExts:["doc","ppt","pdf","rar","xls","xlsx"],//文件格式
			uploadJson : '/api/back/uploadFile/kindeditorUploadFile', //服务端上传图片处理URI
			beforeUpload : function(obj,allowFileExts){//文件上传前的操作
				if(allowFileExts&&allowFileExts.length>0){
					var _isAlow = false;
					var _fileExt = obj.val().lastIndexOf(".");
					_fileExt  = obj.val().substr(_fileExt+1).toLocaleLowerCase();
					for(fileExt in allowFileExts){
						if(allowFileExts[fileExt]==_fileExt){
							_isAlow = true;
						}
					}
					if(!_isAlow){
						alert("当前格式的文件不支持，仅支持格式："+allowFileExts.join(","));
						return false;
					}
				}
				return true;
			},
			afterUpload : function(url,data,name) {//图片上传后
				$("#allFileArray").val($("#allFileArray").val()==""?url:$("#allFileArray").val()+","+url);
			},
			items : [
				'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
				'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
				'insertunorderedlist','formatblock', '|', 'emoticons', 'image', 'insertfile','|', 'preview']
		});
	});
	
	$("#addForm,#editForm").find(".button.cancel").click(function(){
		$(this).parents(".hiddenWin").dialog("close");
	});
	
	$("#addForm,#editForm").form({
		url:'/api/back/news/recordSave',
		onSubmit:function(){
			return $(this).form('validate');
		},
		success:function(data){
			data = eval("("+data+")");
			if(data&&data.success&&data.success==true){
				$.messager.alert('信息', data["msg"], 'info',function(){		
					$("#addWin").dialog("close");
					$("#editWin").dialog("close");
					$('#datagrid').datagrid("reload",{});
					$("#addWin,#editWin").form("clear");
				});
			}else{
				$.messager.alert('信息', data["msg"]?data["msg"]:"操作失败！", 'info');
			}
		}
	});
	
	//资源图片上传的后置事件
	function afterDo(data){
		if(data["success"]==true||data["success"]=='true'){
			$("#allFileArray").val($("#allFileArray").val()==""?data["file"]["serverFName"]:$("#allFileArray").val()+","+data["file"]["serverFName"]);
			$("#imgUrl").val(data["file"]["serverFName"]);
			$(".imgDiv").html("<img src='"+data["file"]["serverFName"]+"' width='295' height='145' />");
			openNewDiv(undefined,".imgDiv");
		}else{
			alert("文件上传失败！");
		};
	}
	</script>
