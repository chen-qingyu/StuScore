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
						用户信息管理
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
								用户名:
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
								<td>用户名</td>
								<td>用户类型</td>
								<td>创建时间</td>
								<td>处理</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="data" items="${page.data }" varStatus="index">
								<tr>
									<td>${index.index  + 1 }</td>
									<td>${data.userName }</td>
									<td>${data.type == 0 ? '系统管理' : '普通用户' }</td>
									<td>${data.createTime }</td>
									<td>
										<c:choose>
											<c:when test="${data.type == 0}">
												<button value="${data.id }" disabled="disabled"  class="btn btn-disabled" type="button">
													<span class="fa fa-trash-o"></span>
												</button>
											</c:when>
											<c:when test="${data.type == 1}">
												<button value="${data.id }"  class="btn btn-del" type="button">
													<span class="fa fa-trash-o"></span>
												</button>
											</c:when>
										</c:choose>
										
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
									<a href="/stuscore/user?action=page&pageIndex=${index }&pageSize=${page.pageSize }">${index }</a>
								</li>
							</c:when>
							<c:otherwise>
								<li>
									<a href="/stuscore/user?action=page&pageIndex=${index }&pageSize=${page.pageSize }">${index }</a>
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
				
				window.location.href = "/stuscore/user?action=page&pageIndex=1&pageSize=10&userName=" + qryText;
			});
			
			// 添加按钮绑定
			$(".btn-add").on("click", function(e){
				
				window.location.href = "/stuscore/pages/user/add.jsp";
			});
			
			// 删除按钮绑定
			$(".btn-del").on("click", function(){
				
				var id = $(this).prop("value");
				
				window.location.href = "/stuscore/user?action=del&id=" + id;
			});
		});
	</script>
</body>
</html>