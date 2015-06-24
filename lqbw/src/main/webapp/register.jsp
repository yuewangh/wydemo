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
<link rel="stylesheet" type="text/css" href="${ctx}bootstrap/css/bootstrap.css">
<script src="${ctx}js/jquery-1.9.1.min.js"></script>
<script src="${ctx}lhgdialog/lhgdialog.js"></script>
</head>
<body style="background-color: #f0f0f0">
	<div>
		<table width="90%" align="center" style="margin-top: 10px;">
			<tr height="55px">
				<td width="120px" class="text-right"><label
					class="control-label">姓名：</label></td>
				<td><input class="form-control input-sm" type="text" id="name"
					name="name" maxlength="20" /></td>
			</tr>
			<tr height="55px">
				<td class="text-right"><label class="control-label">电话：</label></td>
				<td><input class="form-control input-sm" type="text"
					id="phone" name="phone" maxlength="50" /></td>
			</tr>
			<tr height="55px">
				<td class="text-right"><label class="control-label">登陆用户名：</label></td>
				<td><input class="form-control input-sm" type="text"
					id="user_name" name="user_name" maxlength="30" /></td>
			</tr>
			<tr height="55px">
				<td class="text-right"><label class="control-label">登陆密码：</label></td>
				<td><input class="form-control input-sm" type="password"
					id="password1" name="password1" maxlength="15" /></td>
			</tr>
			<tr height="55px">
				<td class="text-right"><label class="control-label">重复登陆密码：</label></td>
				<td><input class="form-control input-sm" type="password"
					id="password2" name="password2" maxlength="15" /></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: right;"><input
					id="savebutton" class="btn btn-info btn-sm" type='button'
					onClick='save()' value='保存' style="margin-top: 5px"> <input
					type="button" class="btn btn-info btn-sm"
					style="margin-top: 5px; margin-left: 5px" value="关闭"
					onclick="frameElement.api.close()" /></td>
			</tr>
		</table>
	</div>
	<script type="text/javascript">
		function save() {
			var name = $("#name").val();
			var phone = $("#phone").val();
			var user_name = $("#user_name").val();
			var password1 = $("#password1").val();
			var password2 = $("#password2").val();
			 /* if($.trim(name) == ""){
		         alert("姓名不能为空");
		         $("#name").focus();
		         return;
		     }
		     if($.trim(phone) == ""){
		         alert("电话不能为空");
		         $("#phone").focus();
		         return;
		     }
			 if($.trim(user_name) == ""){
		         alert("用户名不能为空");
		         $("#user_name").focus();
		         return;
		     }
		     if($.trim(password1) == ""){
		         alert("密码不能为空");
		         $("#password").focus();
		         return;
		     }
		     if(password2 != password1){
		         alert("两次密码不一致，请重新输入！");
		         $("#password1").val("");
		         $("#password2").val("");
		         $("#password1").focus();
		         return;
		     } */
			//$("#savebutton").attr("disabled", "disabled");
			var api = frameElement.api, W = api.opener;
				$.post("${ctx}/sysUser/insert.action", {
					"name" : name,
					"phone" : phone,
					"user_name" : user_name,
					"password" : password2
				}, function(data) {
					if (data.success) {
						alert("注册成功");
						api.close();
					}else{
						alert(data.errormsg);
						$("#savebutton").removeAttr("disabled");
					}
				});
		}
	</script>
</body>
</html>