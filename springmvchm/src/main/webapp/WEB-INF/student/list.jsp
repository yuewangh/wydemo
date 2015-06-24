<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setAttribute(
			"ctx",
			request.getScheme() + "://" + request.getServerName() + ':'
					+ request.getServerPort()
					+ request.getContextPath() + '/');
%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" type="text/css"
	href="${ctx}bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="${ctx}styles/common.css">
<script src="${ctx}js/jquery-1.9.1.min.js"></script>
<script src="${ctx}lhgdialog/lhgdialog.js"></script>
<style type="text/css">
#contable tr th {
	vertical-align: middle;
	text-align: center;
	font-weight: bold;
}

#contable tr td {
	vertical-align: middle;
}
</style>
</head>
<body style="background-color: #f0f0f0">
	<div id="bodydiv" class="panel panel-info"
		style="width: 1170px; margin: 0 auto; padding: 0px">
		<div class="panel panel-primary" style="margin-bottom: 2px">
			<div class="panel-heading">
				<div class="panel-title" style="text-align: center;">
					<h4>欢迎登陆学生报名登记系统</h4>
				</div>
			</div>
		</div>
		<label style="margin-left: 10px"><input type="button" class="btn btn-primary btn-xs" style="padding: 3px 15px;" name="Submit" value="报名学生信息录入" onclick="toedit('')" /></label>
		<label style="margin-left: 10px"><input type="button" class="btn btn-primary btn-xs" style="padding: 3px 15px;" name="Submit" value="上传" onclick="toupload('')" /></label>
		<table width="100%" cellpadding="0" cellspacing="0" class="searchTable" border="0" id="contable">
			<tr class="platformTasktitle">
				<th width="5%">序号</th>
				<th width="10%">学生姓名</th>
				<th width="5%">性别</th>
				<th width="10%">学历</th>
				<th width="10%">电话</th>
				<th width="10%">家长姓名</th>
				<th width="10%">家长电话</th>
				<th>住址</th>
				<th width="10%">报名日期</th>
				<th width="10%">操作</th>
			</tr>
			<c:forEach items="${studentlist}" var="student" varStatus="sta">
				<tr>
					<td class="text-center">${sta.count}</td>
					<td>${student.name}</td>
					<td class="text-center">${student.sex}</td>
					<td>${student.xueli}</td>
					<td>${student.phone}</td>
					<td>${student.father_name}</td>
					<td>${student.father_phone}</td>
					<td>${student.adress}</td>
					<td>${student.create_date}</td>
					<td class="text-center">
						<a href="javascript:;" onclick="toedit('${student.id}')">修改</a>
						<a href="javascript:;" style="margin-left: 10px" onclick="deletestu('${student.id}')">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<script type="text/javascript">
		function toedit(id){
			$.dialog({
				id : 'zhuce',
				title : '报名学生信息录入',
				content : "url:${ctx}student/edit.action?id="+id,
				cover : true,
				width : 960,
				min : false,
				max : false,
				height : 400,
				lock : true
			});
		}
		function toupload(){
			$.dialog({
				id : 'zhuce',
				title : '报名学生信息录入',
				content : "url:${ctx}upload/toupload.action",
				cover : true,
				width : 960,
				min : false,
				max : false,
				height : 400,
				lock : true
			});
		}
		
		function deletestu(id){
			if(confirm("确认要删除吗？")){
				 $.post("${ctx}student/delete.action", {
			    	 "id" : id
					}, function(data) {
						if(data.success){
							alert("删除成功");
							window.location.reload();
						} 
				 });
			}
		}
	</script>
</body>
</html>