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
<script src="${ctx}js/my97DatePicker/WdatePicker.js"></script>
<script src="${ctx}lhgdialog/lhgdialog.js"></script>
</head>
<body style="background-color: #f0f0f0">
	<div>
		<form id="registerForm" action="" method="post" autocomplete="off">
		<input type="hidden" id="id" name="id" value="${student.id}"/>
		<table width="99%" style="margin-top: 10px;margin-bottom:0px;text-align: center;">
			<tr>
				<td width="60px" class="text-right"><label class="control-label">姓名：</label></td>
				<td width="15%"><input class="form-control input-sm" type="text" id="name" name="name" maxlength="20" value="${student.name}"/></td>
				<td width="80px" class="text-right"><label class="control-label">身份证号：</label></td>
				<td width="40%"><input class="form-control input-sm" type="text" id="id_code" name="id_code" maxlength="18" value="${student.id_code}"/></td>
				<td width="60px" class="text-right"><label class="control-label">性别：</label></td>
				<td width="15%"><select class="form-control input-sm" id="sex" name="sex">
					<c:choose>
						<c:when test="${student.sex == '男'}">
							<option value="男" selected="selected">男</option><option value="女">女</option>
						</c:when>
						<c:otherwise>
							<option value="男">男</option><option value="女"  selected="selected">女</option>
						</c:otherwise>
					</c:choose>
				</select></td>
			</tr>
			<tr>
				<td class="text-right"><label class="control-label">学历：</label></td>
				<td><select class="form-control input-sm" id="xueli" name="xueli">
					<c:choose>
						<c:when test="${student.xueli == '初中'}">
							<option value="初中" selected="selected">初中</option><option value="高中">高中</option><option value="大专">大专</option><option value="本科">本科</option>
						</c:when>
						<c:when test="${student.xueli == '高中'}">
							<option value="初中">初中</option><option value="高中" selected="selected">高中</option><option value="大专">大专</option><option value="本科">本科</option>
						</c:when>
						<c:when test="${student.xueli == '大专'}">
							<option value="初中">初中</option><option value="高中">高中</option><option value="大专" selected="selected">大专</option><option value="本科">本科</option>
						</c:when>
						<c:otherwise>
							<option value="初中">初中</option><option value="高中">高中</option><option value="大专">大专</option><option value="本科" selected="selected">本科</option>
						</c:otherwise>
					</c:choose>
				</select></td>
				<td class="text-right"><label class="control-label">学校：</label></td>
				<td><input class="form-control input-sm" type="text" id="gaozhong" name="gaozhong" maxlength="100" value="${student.gaozhong}"/></td>
				<td class="text-right"><label class="control-label">出生日期：</label></td>
				<td><input class="form-control input-sm" type="text" id="born_time" name="born_time" class="Wdate"
											onClick="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})"
												value="${student.born_time}" style="background-color: white;"/></td>
			</tr>
			<tr>
				<td class="text-right"><label class="control-label">电话：</label></td>
				<td><input class="form-control input-sm" type="text" id="phone" name="phone" maxlength="50" value="${student.phone}"/></td>
				<td class="text-right"><label class="control-label">专业：</label></td>
				<td><input class="form-control input-sm" type="text" id="daxue" name="daxue" maxlength="100" value="${student.daxue}"/></td>
				<td class="text-right"><label class="control-label">QQ：</label></td>
				<td><input class="form-control input-sm" type="text" id="qq" name="qq" maxlength="20" value="${student.qq}"/></td>
				
			</tr>
			<tr>
				<td class="text-right"><label class="control-label">家长姓名：</label></td>
				<td><input class="form-control input-sm" type="text" id="father_name" name="father_name" maxlength="20" value="${student.father_name}"/></td>
				<td class="text-right"><label class="control-label">家长电话：</label></td>
				<td><input class="form-control input-sm" type="text" id="father_phone" name="father_phone" maxlength="50" value="${student.father_phone}"/></td>
				<td class="text-right"><label class="control-label">咨询老师：</label></td>
				<td><input class="form-control input-sm" type="text" id="zxls" name="zxls" maxlength="20" value="${student.zxls}"/></td>
			</tr>
			<tr>
				<td class="text-right"><label class="control-label">住址：</label></td>
				<td colspan="5"><input class="form-control input-sm" type="text" id="adress" name="adress" maxlength="500" value="${student.adress}"/></td>
			</tr>
			<tr>
				<td class="text-right"><label class="control-label">意向专业：</label></td>
				<td colspan="5">
					<div class="panel panel-info" style="background-color: white;height: 35px;margin-top: 10px;">
					    <div style="float: left;"><input type="checkbox" name="yixzy" value="传媒艺术" style="margin-left: 10px">传媒艺术</div>
					    <div style="float: left;"><input type="checkbox" name="yixzy" value="软件工程" style="margin-left: 10px">软件工程</div>
					    <div style="float: left;"><input type="checkbox" name="yixzy" value="动画艺术" style="margin-left: 10px">动画艺术</div>
					    <div style="float: left;"><input type="checkbox" name="yixzy" value="建筑设计" style="margin-left: 10px">建筑设计</div>
					    <div style="float: left;"><input type="checkbox" name="yixzy" value="移动通信" style="margin-left: 10px">移动通信</div>
					    <div style="float: left;"><input type="checkbox" name="yixzy" value="网络工程" style="margin-left: 10px">网络工程</div>
					    <div style="float: left;"><input type="checkbox" name="yixzy" value="高级翻译" style="margin-left: 10px">高级翻译</div>
					    <div style="float: left;"><input type="checkbox" name="yixzy" value="俄语" style="margin-left: 10px">俄语</div>
					    <div style="float: left;"><input type="checkbox" name="yixzy" value="云计算" style="margin-left: 10px">云计算</div>
				    </div>
				</td>
			</tr>
			<tr>
				<td class="text-right"><label class="control-label">备注：</label></td>
				<td colspan="5"><textarea class="form-control" id="beizhu" name="beizhu" rows="4" maxlength="1000" style="resize: none;">${student.beizhu}</textarea></td>
			</tr>
		</table>
		</form>
		<div class="panel panel-info" style="margin-top: 10px;margin-bottom: 0px">
			<div class="panel-heading">
				<div class="panel-title" style="text-align: right;">
					<input id="savebutton" class="btn btn-info btn-sm" type='button' onClick='save()' value='保存'> 
					<input type="button" class="btn btn-info btn-sm" style="margin-left: 5px" value="关闭"
					onclick="frameElement.api.close()" />
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function save() {
			var db = $('#registerForm').serialize();
			$("#savebutton").attr("disabled", "disabled");
			var api = frameElement.api, W = api.opener;
			 $.ajax({
				   type: "POST",
				   url: "${ctx}student/saveStudent.action",
				   data: db,
				   success: function(data){
						if(data.success){
							alert("操作成功。");
							$("#id").val(data.id);
							api.reload();
						}else{
							alert(data.errmsg);
							$("#savebutton").removeAttr("disabled");
						}
				   },
				   complete : function(XMLHttpRequest, textStatus) {
						$("#savebutton").removeAttr("disabled");
					}
				});
		}
	</script>
</body>
</html>