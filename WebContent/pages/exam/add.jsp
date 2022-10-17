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
					<span class="main-nav-active">
						<a href="/stuscore/exam?action=page&pageIndex=1&pageSize=10">考试信息管理</a>
					</span>
					<span class="main-nav-sep">
						/
					</span>
					<span class="main-nav-this">
						添加考试信息
					</span>
				</div>
			</div>
			
			<div class="panel">
				<div class="panel-desc">
					<form class="form-edit" action="/stuscore/exam?action=add" method="post">
						<div class="form-input">
							<span>
								考试名称
							</span>
							<input type="text" name="name"/>
						</div>
						<div class="form-select">
							<span>
								考试班级
							</span>
							<select name="gId">
								<c:forEach var="grade" items="${grades }">
									<option value="${grade.id }">${grade.name }</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-select">
							<span>
								考试类型
							</span>
							<select name="etId">
								<c:forEach var="type" items="${types }">
									<option value="${type.id }">${type.name }</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-select">
							<span>
								考试科目
							</span>
							<select name="epId">
								<c:forEach var="project" items="${projects }">
									<option value="${project.id }">${project.name }</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-input">
							<span>
								考试时间
							</span>
							<input type="text" name="eTime"/>
						</div>
						<div class="form-input">
							<span>
								考试说明
							</span>
							<textarea name="intro" cols="90" rows="5"></textarea>
						</div>
						<div class="form-btn">
							<button class="form-btn-sub" type="submit">提交</button>
							<button class="form-btn-res" type="reset">重置</button>
						</div>
					</form>
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