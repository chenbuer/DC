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
	var xhr = new XMLHttpRequest();
	var setNews=function(threeNews){
		document.getElementById("news1title").innerHTML=threeNews[0]["title"];
		document.getElementById("news1desc").innerHTML=threeNews[0]["descr"];
		
		document.getElementById("news2title").innerHTML=threeNews[1]["title"];
		document.getElementById("news2desc").innerHTML=threeNews[1]["descr"];
		
		document.getElementById("news3title").innerHTML=threeNews[2]["title"];
		document.getElementById("news3desc").innerHTML=threeNews[2]["descr"];
	}
	xhr.open("GET", "index/newsQuery");
	xhr.onreadystatechange=function(){
		if(4==xhr.readyState){
			if(200==xhr.status){
				console.log("get news success:"+xhr.responseText);
				setNews(JSON.parse(xhr.response));
			}
			else{
				console.log("获取news失败");
			}
		}
	}
	xhr.send();
	
</script>
<div class="carousel slide" id="carousel-958836">
					<ol class="carousel-indicators">
						<li class="active" data-slide-to="0"
							data-target="#carousel-958836"></li>
						<li data-slide-to="1" data-target="#carousel-958836"></li>
						<li data-slide-to="2" data-target="#carousel-958836"></li>
					</ol>
					<div class="carousel-inner">
						<div class="item active">
							<img alt="Carousel Bootstrap First"
								src="http://lorempixel.com/output/sports-q-c-1600-500-1.jpg">
							<div class="carousel-caption">
								<h4>First Thumbnail label</h4>
								<p>Cras justo odio, dapibus ac facilisis in, egestas eget
									quam. Donec id elit non mi porta gravida at eget metus. Nullam
									id dolor id nibh ultricies vehicula ut id elit.</p>
							</div>
						</div>
						<div class="item">
							<img alt="Carousel Bootstrap Second"
								src="http://lorempixel.com/output/sports-q-c-1600-500-2.jpg">
							<div class="carousel-caption">
								<h4>Second Thumbnail label</h4>
								<p>Cras justo odio, dapibus ac facilisis in, egestas eget
									quam. Donec id elit non mi porta gravida at eget metus. Nullam
									id dolor id nibh ultricies vehicula ut id elit.</p>
							</div>
						</div>
						<div class="item">
							<img alt="Carousel Bootstrap Third"
								src="http://lorempixel.com/output/sports-q-c-1600-500-3.jpg">
							<div class="carousel-caption">
								<h4>Third Thumbnail label</h4>
								<p>Cras justo odio, dapibus ac facilisis in, egestas eget
									quam. Donec id elit non mi porta gravida at eget metus. Nullam
									id dolor id nibh ultricies vehicula ut id elit.</p>
							</div>
						</div>
					</div>
					<a class="left carousel-control" href="#carousel-958836"
						data-slide="prev"><span
						class="glyphicon glyphicon-chevron-left"></span></a> <a
						class="right carousel-control" href="#carousel-958836"
						data-slide="next"><span
						class="glyphicon glyphicon-chevron-right"></span></a>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4" id="news1">
				<h2 id="news1title">======</h2>
				<p id="news1desc">===========================</p>
				<p id="news1url">
					<a class="btn" href="#">View details »</a>
				</p>
			</div>
			<div class="col-md-4" id="news2">
				<h2 id="news2title">======</h2>
				<p id="news2desc">===========================</p>
				<p id="news2url">
					<a class="btn" href="#">View details »</a>
				</p>
			</div>
			<div class="col-md-4" id="news3">
				<h2 id="news3title">======</h2>
				<p id="news3desc">===========================</p>
				<p id="news3url">
					<a class="btn" href="#">View details »</a>
				</p>
			</div>
		</div>
	</div>

	<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>