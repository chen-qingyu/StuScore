<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
					<span class="main-nav-active">
						<a href="/stuscore/">首页</a>
					</span>
					<span class="main-nav-sep">
						/
					</span>
					<span class="main-nav-this">
						学生信息管理
					</span>
				</div>
			</div>
			
			<div class="panel">
				<div class="panel-title">
					信息查询
				</div>
				<div class="panel-desc">
					<form action="" class="search">
						<div>
							<span class="search-lable">
								学生姓名:
							</span>
							<input type="text" class="serach-input"/>
						</div>
						<div class="search">
							<button type="button" class="btn btn-search">
								<span class="fa fa-search"></span>
							</button>
						</div>
					</form>
				</div>
			</div>
			
			<div class="panel">
				<div class="panel-title">
					<button class="btn btn-add" type="button">
						<span class="fa fa-plus"></span>
					</button>
				</div>
				<div class="panel-desc">
					<table class="table-data">
						<thead>
							<tr>
								<td>编号</td>
								<td>学生姓名</td>
								<td>学生性别</td>
								<td>学生年龄</td>
								<td>所在年级</td>
								<td>处理</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="data" items="${page.data }" varStatus="index">
								<tr>
									<td>${index.index  + 1 }</td>
									<td>${data.name }</td>
									<td>${data.sex == 0 ? '男' : '女' }</td>
									<td>${data.age }</td>
									<td>${data.gname }</td>
									<td>
										<button value="${data.id }" class="btn btn-edit" type="button">
											<span  class="fa fa-pencil"></span>
										</button>
										<button value="${data.id }"  class="btn btn-del" type="button">
											<span class="fa fa-trash-o"></span>
										</button>
									</td>
								</tr>
							</c:forEach>	
						</tbody>
					</table>
				</div>
			</div>
			
			<div class="page-panel">
				<div class="page-panel-desc">
					共 ${page.count } 条数据，每页 ${page.pageSize } 条， 第 ${page.pageIndex } 页
				</div>
				<ul>
					<c:forEach var="index"  begin="1" end="${page.pageTotal }" step="1">
						<c:choose>
							<c:when test="${index == page.pageIndex }">
								<li class="page-panel-this">
									<a href="/stuscore/student?action=page&pageIndex=${index }&pageSize=${page.pageSize }">${index }</a>
								</li>
							</c:when>
							<c:otherwise>
								<li>
									<a href="/stuscore/student?action=page&pageIndex=${index }&pageSize=${page.pageSize }">${index }</a>
								</li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
			</div>
		</div>	
	</div>
	
	<jsp:include page="/templates/foot.jsp"></jsp:include>
	
	<script type="text/javascript" src="/stuscore/js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript">
		$(function(){
			
			$(".top-user").on("click", function(){
				
				$(this).find("ul").toggleClass("open");
			})
			
			// 查询按钮绑定
			$(".btn-search").on("click", function(e){
				
				var qryText = $(".serach-input").val();
				
				window.location.href = "/stuscore/student?action=page&pageIndex=1&pageSize=10&name=" + qryText;
			});
			
			// 添加按钮绑定
			$(".btn-add").on("click", function(e){
				
				window.location.href = "/stuscore/student?action=addView";
			});
			
			// 修改按钮绑定
			$(".btn-edit").on("click", function(e){
				
				var id = $(this).attr("value");

				window.location.href = "/stuscore/student?action=updView&id=" + id;
			});
			
			// 删除按钮绑定
			$(".btn-del").on("click", function(){
				
				var id = $(this).prop("value");
				
				window.location.href = "/stuscore/student?action=del&id=" + id;
			});
		});
	</script>
</body>
</html>