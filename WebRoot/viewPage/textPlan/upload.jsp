<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传</title>

<style type="text/css">
body {
	width: 900px;
	margin: 0 auto;
	text-align: left;
}

b {
	font-size: larger;
	color: blue;
}
</style>
</head>
<body>
	<div align="center"
		style="margin-top: 5%;color: blue;font-size: 25px;font-family: '微软雅黑';">
		<h3>文件上传</h3>
	</div>
	<div align="left" style="margin-top: 5%;">
		<b>HTTP/模板文件上传</b>
	</div>
	<div align="left" style="margin-top: 2%;">
		<form action="${pageContext.request.contextPath }/uploadServlet"
			method="post" enctype="multipart/form-data">
			上传文件： <input type="file" name="file" id="file" /><br> <input
				type="submit" name="submit" value="提交" />
		</form>
	</div>
</body>
</html>