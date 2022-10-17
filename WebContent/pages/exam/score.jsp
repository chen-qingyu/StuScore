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
						编辑考试成绩
					</span>
				</div>
			</div>
			
			<div class="panel">
				<div class="panel-title">
					成绩查询
				</div>
				<div class="panel-desc">
					<form action="" class="search">
						<div>
							<span class="search-lable">
								学生姓名:
							</span>
							<input type="text" class="serach-input"/>
							<input type="hidden" name="gid" value="${exam.gId  }"/>
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
				<div class="panel-desc">
					<form class="form-edit" action="/stuscore/exam?action=add" method="post">
						<table class="table-data">
							<thead>
								<tr>
									<td>编号</td>
									<td>学生姓名</td>
									<td>学生性别</td>
									<td>学生年龄</td>
									<td>所在年级</td>
									<td>考试成绩</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="student" items="${students }" varStatus="index">
									<tr>
										<td>${index.index  + 1 }</td>
										<td>${student.name }</td>
										<td>${student.sex == 0 ? '男' : '女' }</td>
										<td>${student.age }</td>
										<td>${student.gname }</td>
										<td>
											<input type="text" name="score"  value="${student.score == null ? '未录入' : student.score }"/>
											<input type="hidden" name="sid" value="${student.id }"/>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<input type="hidden" name="eid" value="${exam.id }"/>
					</form>
				</div>
				
				<div class="panel-title">
					<button class="editscore" style="width: 200px;height: 40px;padding: 10px; line-height: 20px;border: none; background-color: #6699CC; color: #fff;cursor: pointer;" type="button">
						确认成绩
					</button>
				</div>
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
				
				var qryText = $(".serach-input").val(),
				    gId = $("input[name=gid]").val(),
				    eId = $("input[name=eid]").val();
				
				window.location.href =  "/stuscore/exam?action=scoreView&gId=" + gId + "&eId=" + eId + "&name=" + qryText;
			});
			
			$("table td input[name=score]").on("focus", function(){
				
				var val = $(this).val();
				
				if(val == "未录入"){
					$(this).val("");
				}
			});
			
			$("table td input[name=score]").on("blur", function(){
				
				var val = $(this).val();
				
				if(val == ""){
					$(this).val("未录入");
				}
			});
			
			$(".editscore").on("click", function(){
				
				var sIds = new Array(),
					scores = new Array(),
					eId = $("input[name=eid]").val();
				
				$("table tr").each(function(i, item){
					
					var sid = $(item).find("input[name=sid]").val(),
						score = $(item).find("input[name=score]").val();
					
					if(!isNaN(score)){
						sIds.push(sid);
						scores.push(score);
					}
				});
				
				$.ajax({
					url: "http://localhost:8080/stuscore/score",
					data: {
						action: "edit",
						sIds: sIds,
						scores: scores,
						eId: eId
					},
					success: function(data){
						
						window.location.href="/stuscore/exam?action=page&pageIndex=1&pageSize=10";
					}
				});
			});
		});
	</script>
</body>
</html>