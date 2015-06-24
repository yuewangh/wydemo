<!DOCTYPE html>
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
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="${ctx}bootstrap/css/bootstrap.css">
<script type="text/javascript" src="${ctx}/js/plupload/plupload.full.min.js"></script>
</head>
<body>
	<body style="font: 13px Verdana; background: #eee; color: #333">

<h1>Custom example</h1>

<p>Shows you how to use the core plupload API.</p>

<div id="filelist">Your browser doesn't have Flash, Silverlight or HTML5 support.</div>
<br />

<div id="container">
    <a id="pickfiles" href="javascript:;">[Select files]</a> 
    <a id="uploadfiles" href="javascript:;">[Upload files]</a>
</div>

<br />
<pre id="console"></pre>


<script type="text/javascript">
	var uploader_1 = new plupload.Uploader({
		runtimes : 'html5,flash,silverlight,browserplus,html4',
		browse_button : 'pickfiles',//上传按钮id
		container : document.getElementById('container'), // ... or DOM Element itself
		url : '${ctx}/upload/singleFile.action',
		flash_swf_url : '${ctx}/js/plupload/Moxie.swf',
		silverlight_xap_url : '${ctx}/js/plupload/plupload.silverlight.xap',
		filters : {
			max_file_size : '10mb',
			mime_types: [
				{title : "Image files", extensions : "jpg,gif,png"},
				{title : "Zip files", extensions : "zip"}
			]
		},
		init : {
			PostInit: function() {
				document.getElementById('filelist').innerHTML = '';
				document.getElementById('uploadfiles').onclick = function() {
					uploader_1.start();
					return false;
				};
			},

			FilesAdded: function(up, files) {
				plupload.each(files, function(file) {
					document.getElementById('filelist').innerHTML += '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>';
				});
			},

			UploadProgress: function(up, file) {
				document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
			},

			Error: function(up, err) {
				document.getElementById('console').innerHTML += "\nError #" + err.code + ": " + err.message;
			}
		}
	});
	uploader_1.init();
</script>
</body>
</html>