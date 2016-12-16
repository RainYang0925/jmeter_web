<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>jmeter模板</title>
</head>
<body>
	<div>
		<form action="#" method="post">
			启动文件: <input type="text" name="filepath" value="${filePath }"><br>
			<input name="radio" type="radio" value="false" />本地 <input
				name="radio" type="radio" value="true" />远程<br> 添加远程机1：<input
				type="text" name="slaveip1" value="null"><br> 添加远程机2：<input
				type="text" name="slaveip2" value="null"><br> 添加远程机3：<input
				type="text" name="slaveip3" value="null"><br> 添加远程机4：<input
				type="text" name="slaveip4" value="null"><br> <input
				type="submit" value="运行">
		</form>
	</div>

</body>
</html>