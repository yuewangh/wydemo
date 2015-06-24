<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setAttribute("ctx",request.getScheme() + "://" + request.getServerName() + ':'
					+ request.getServerPort()+ request.getContextPath() + '/');
%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<html>
<head>
		<title>学生信息登记系统</title>
		<link href="${ctx}styles/login.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${ctx}bootstrap/css/bootstrap.css">
		<script src="${ctx}js/jquery-1.9.1.min.js"></script>
		<script src="${ctx}lhgdialog/lhgdialog.js"></script>
	</head>
	<body>
		<div class="top">
			<div class="topbk">
				<span>欢迎进入系统</span>
			</div>
		</div>
		<div style="height: 40px;"></div>
		<div class="content">
			<div class="login">
				<div class="login_list">
					<div class="notice">
						<div id="hiddenblock61" style="display: block;">
							<table width="274" border="0" align="center" cellpadding="0" cellspacing="0">
								<tr>
									<td colspan="2" style="font-size: 14px; line-height: 40px;">
										登陆号：
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<input class="login_input" title='用户名' id="user_name" maxlength="30" onfocus="this.select()" />
									</td>
								</tr>
								<tr>
									<td height="5" colspan="2"></td>
								</tr>
								<tr>
									<td colspan="2" style="font-size: 14px; line-height: 40px;">
										密&nbsp;&nbsp;码：
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<input tabindex="1" class="login_input" title='密码' id="password" type="password" maxlength="30" onfocus="this.select()" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										&nbsp;
									</td>
								</tr>
								<tr>
									<td align="center">
										<input type="button" id="loginbtn" class="btn btn-primary" style="padding: 3px 15px;" value="登陆" onclick="tologin()"/>
									</td>
									<td align="center">
										<input type="button" id="loginbtn" class="btn btn-info btn-xs" style="padding: 3px 15px;" value="注册" onclick="zhuce()"/>
									</td>
								</tr>
							</table>
						</div>

					</div>
				<div style="text-align:right;padding-right:30px"><a style="font-size:14px" href="${ctx}login!toFindPwdPage.action">忘记密码?</a></div>
				</div>
				<div class="login_bottom">
					*建议使用
					<a style="color: red" href="http://download.microsoft.com/download/1/6/1/16174D37-73C1-4F76-A305-902E9D32BAC9/IE8-WindowsXP-x86-CHS.exe">IE8</a>浏览器、电脑分辨率为1024*768以上
				</div>
			</div>
		</div>
		<script type="text/javascript">
			function zhuce(){
				$.dialog({
					id : 'zhuce',
					title : '新用户注册',
					content : "url:${ctx}register.jsp",
					cover : true,
					width : 500,
					min : false,
					max : false,
					height : 330,
					lock : true
				});
			}
			 function tologin() {
				 var user_name=$("#user_name").val();
			     var password=$("#password").val();
			     if($.trim(user_name) == ""){
			         alert("用户名不能为空");
			         $("#user_name").focus();
			         return;
			     }
			     if($.trim(password) == ""){
			         alert("密码不能为空");
			         $("#password").focus();
			         return;
			     }
			     $.post("${ctx}sysUser/login.action", {
			    	 "user_name" : user_name,
			    	 "password" : password
					}, function(data) {
						if(data.success){
							window.location.href="${ctx}student/list.action?create_user="+user_name;
						} else{
							alert(data.msg);
						} 
				 });
			 }
		</script>
	</body>
</html>