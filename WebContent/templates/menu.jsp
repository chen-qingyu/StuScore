<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="main-left">
	<ul>
		
		<c:choose>
			<c:when test="${user.type == 0 }">
				<li>
					<span class="fa fa-user-o"></span>
					<a href="/stuscore/user?action=page&pageIndex=1&pageSize=10">用户信息管理</a>
				</li>
				<li>
					<span class="fa fa-book"></span>
					<a href="/stuscore/project?action=page&pageIndex=1&pageSize=10">考试科目管理</a>
				</li>
				<li>
					<span class="fa fa-cogs"></span>
					<a href="/stuscore/type?action=page&pageIndex=1&pageSize=10">考试类别管理</a>
				</li>
			</c:when>
			<c:when test="${user.type == 1 }">
				<li>
					<span class="fa fa-building-o"></span>
					<a href="/stuscore/grade?action=page&pageIndex=1&pageSize=10">年级信息管理</a>
				</li>
				<li>
					<span class="fa fa-child"></span>
					<a href="/stuscore/student?action=page&pageIndex=1&pageSize=10">学生信息管理</a>
				</li>
				<li>
					<span class="fa fa-edit"></span>
					<a href="/stuscore/exam?action=page&pageIndex=1&pageSize=10">考试信息管理</a>
				</li>
			</c:when>
		</c:choose>
	</ul>
</div>