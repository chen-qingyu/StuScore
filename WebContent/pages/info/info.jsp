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
						个人信息
					</span>
				</div>
			</div>
			
			<div class="panel">
				<div class="panel-desc">
					<form class="form-edit" action="/stuscore/info?action=updInfo" method="post">
						<div class="form-input">
							<span>
								用户名
							</span>
							<input type="text" name="userName" value="${user.userName }"/>
						</div>
						<div class="form-input">
							<span>
								备注说明
							</span>
							<textarea name="comm" cols="90" rows="5">${user.comm }</textarea>
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