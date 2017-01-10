<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>JMeter Web化</title>
<style type="text/css">
body {
	text-align: center;
	margin-top: 10%;
}
</style>
<script type="text/javascript">
	function disabledBtn() {
		document.getElementById("startup").disabled = "true";
		document.getElementById("main").display = "false";
		document.getElementById("inneralert").innerHTML = "<span><font size='5'>正在执行脚本，请稍后！</font></span><br><br><img src='./images/locbanner.gif' /><br><br><br>";
		document.getElementById("main").submit();
	}
</script>
</head>
<body>
	<div id="inneralert" style="text-align:center;"></div>
	<div>
		<form id="main" action="${pageContext.request.contextPath }/startCmdServlet"
			method="post">
			启动文件: <input type="text" name="filepath" value="${filePath }"><br>
			<input name="radio" type="radio" value="false" />本地 <input
				name="radio" type="radio" value="true" />远程<br> 添加远程机1：<input
				type="text" name="slaveip1" value="null" /><br> 添加远程机2：<input
				type="text" name="slaveip2" value="null" /><br> 添加远程机3：<input
				type="text" name="slaveip3" value="null" /><br> 添加远程机4：<input
				type="text" name="slaveip4" value="null" /><br> <input
				type="button" value="运行" id="startup" onclick="disabledBtn()"/>
		</form>
	</div>

</body>
</html>