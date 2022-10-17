<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
	<jsp:include page="/templates/head.jsp"></jsp:include>
<body>
	<jsp:include page="/templates/nav.jsp"></jsp:include>
	
	<div class="main">
		<jsp:include page="/templates/menu.jsp"></jsp:include>
		<div class="main-right">

			<div class="panel">
				<div class="panel-desc">
					 您好，<span>${user.userName }</span> 欢迎使用学生成绩管理系统！
				</div>
			</div>

			<div class="panel">
				<div class="panel-title">
					 系统详情
				</div>
				<div class="panel-desc">
					<table class="sys-desc">
						<tr>
							<td>系统名称</td>
							<td>学生成绩管理系统</td>
						</tr>
						<tr>
							<td>系统版本</td>
							<td>V 1.0.2</td>
						</tr>
						<tr>
							<td>运行环境</td>
							<td>Apache Tomcat 9.0</td>
						</tr>
						<tr>
							<td>JDK版本</td>
							<td>11.0</td>
						</tr>
						<tr>
							<td>MySQL版本</td>
							<td>8.0</td>
						</tr>
					</table>
				</div>
			</div>

			<div class="panel">
				<div class="panel-title">
					 项目信息
				</div>
				<div class="panel-desc">
					<table class="sys-desc">
						<tr>
							<td>开发人员</td>
							<td>陈青羽，张翔宇</td>
						</tr>
						<tr>
							<td>开发时间</td>
							<td>2021.03 - 2021.05</td>
						</tr>
					</table>
				</div>
			</div>
			
		</div>
	</div>
	
	<jsp:include page="/templates/foot.jsp"></jsp:include>
	
	<script type="text/javascript" src="/stuscore/js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript">
		$(".top-user").on("click", function(){
			
			$(this).find("ul").toggleClass("open");
		})
	</script>
</body>
</html>