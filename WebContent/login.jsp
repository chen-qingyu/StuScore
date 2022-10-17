<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
	<meta charset="UTF-8">
	<title>登录 - 学生成绩管理系统</title>
	<link rel="stylesheet" href="/stuscore/css/login.css"/>
<body>
	<div class="login-win">
		<h2>学生成绩管理系统</h2>
		<div class="login-form">
			<form action="/stuscore/login" method="post">
				<div class="form-input">
					<span>
						用户名
					</span>
					<input type="text" name="userName"/>
				</div>
				<div class="form-input">
					<span class="form-input-label">
						用户密码
					</span>
					<input type="password" name="passWord"/>
				</div>
				<div class="form-btn">
					<button type="submit">提交</button>
					<button type="reset">重置</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>