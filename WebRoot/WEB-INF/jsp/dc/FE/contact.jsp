<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>



<script type="text/javascript" src="http://api.map.baidu.com/api?key=&v=1.1&services=true"></script>
<!--百度地图容器-->


	<div class="row">
		<div class="col-md-4">
			<br>
			
			<address>
				  <strong>天津定创科技发展有限公司</strong><br>
				  	地   址:中国天津市河西区洞庭路与东江道交口南侧香年广场A座91<br>
				  <abbr title="FreeHotline">服务热线:</abbr> (123) xxxx<br>
				  <abbr title="SalesHotline">销售热线:</abbr> (123) xxxx<br>
				  <abbr title="Fax">传        真:</abbr> (123) xxxx<br>
			</address>
			<address>
			  <strong>邮件请寄往</strong><br>
			  <a href="mailto:#">lin.wang@sure-creation.com</a>
			</address>
			
		</div>
		<div class="col-md-8">
			<div class="center-block" style="width:697px;height:380px;border:#ccc solid 1px;" id="dituContent"></div>
		</div>
	</div>





<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>