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
						<a href="/stuscore/student?action=page&pageIndex=1&pageSize=10">学生信息管理</a>
					</span>
					<span class="main-nav-sep">
						/
					</span>
					<span class="main-nav-this">
						编辑学生信息
					</span>
				</div>
			</div>
			
			<div class="panel">
				<div class="panel-desc">
					<form class="form-edit" action="/stuscore/student?action=upd" method="post">
						<div class="form-input">
							<span>
								学生姓名
							</span>
							<input type="text" name="name" value="${student.name }"/>
						</div>
						<div class="form-select">
							<span>
								所属年级
							</span>
							<select name="gid">
								<c:forEach var="grade" items="${grades }">
									<c:choose>
										<c:when test="${student.gid == grade.id }">
											<option value="${grade.id }" selected="selected">${grade.name }</option>
										</c:when>
										<c:otherwise>
											<option value="${grade.id }">${grade.name }</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
						<div class="form-radio">
							<span>
								学生性别
							</span>
							<c:choose>
								<c:when test="${student.sex == 0 }">	
									<input type="radio" name="sex" value="0" checked="checked"/> 男
									<input type="radio" name="sex" value="1"/> 女
								</c:when>
								<c:otherwise>
									<input type="radio" name="sex" value="0"/> 男
									<input type="radio" name="sex" value="1" checked="checked"/> 女
								</c:otherwise>
							</c:choose>
						</div>
						<div class="form-input">
							<span>
								学生年龄
							</span>
							<input type="text" name="age"  value="${student.age }"/>
						</div>
						<div class="form-input">
							<span>
								备注说明
							</span>
							<textarea  name="comm" cols="90" rows="5">${student.comm }</textarea>
						</div>
						<input type="hidden" name="id" value="${student.id }"/>
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