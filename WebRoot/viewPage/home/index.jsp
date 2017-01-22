<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>WEB测试平台</title>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css"/>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

<style type="text/css">
.inline {
	display: inline
}

b {
	font-size: larger;
	color: blue;
}

body {
	width:900px;
	margin: 0 auto;
	text-align: left;
}

</style>
</head>
<body>
	<div style="height:1500px;" id="d3">
		<form action="${pageContext.request.contextPath }/createJmxServlet"
			method="post">
			<div align="center" style="margin-top: 5%;color: blue;font-size: 25px;font-family: '微软雅黑';">
				<h3>HTTP模板</h3>
			</div>
			<div align="left" style="margin-top: 2%;">
				<b>线程组</b>
			</div>
			<div align="left" style="margin-top: 2%;">1、在取样器错误之后要执行的操作:</div>
			<div align="left">
				<div class="inline">
					<!-- radio--------------continue（改过的） -->
					<input name="radio" type="radio" value="continue" checked="checked" />继续
				</div>
				<div class="inline">
					<input name="radio" type="radio" value="startnextloop" />启动下一个循环
				</div>
				<div class="inline">
					<input name="radio" type="radio" value="stopthread" />停止线程
				</div>
				<div class="inline">
					<input name="radio" type="radio" value="stoptest" />停止测试
				</div>
				<div class="inline">
					<input name="radio" type="radio" value="stoptestnow" />立即停止测试
				</div>
			</div>
			<div align="left">2、线程组属性：</div>
			<div align="left">
				<div align="left">
					模板名称: <input type="text" name="tpname" value="HTTP" />
				</div>
				<div align="left">
					线程数目: <input type="text" name="ThreadGroup.num_threads" value="10" />
				</div>
				<div align="left">
					启动时间: <input type="text" name="ThreadGroup.ramp_time" value="1" />
				</div>
				<div align="left">
					<div class="inline">
						循环次数: <input type="text" name="LoopController.loops" value="1" />
					</div>
					<div class="inline">
						<input name="checkbox" type="checkbox" value="che1" />永远
					</div>
				</div>
			</div>

			<div align="left" style="margin-top:1%">
				<b>HTTP</b>
			</div>
			<div align="left"style="margin-top: 2%;">1、web服务器:</div>
			<div align="left">
				<div class="inline">
					服务器名称或IP: <input type="text" name="HTTPSampler.domain"
						value="www.baidu.com" />
				</div>
				<div class="inline">
					端口号: <input type="text" name="HTTPSampler.port" value="" />
				</div>
			</div>
			<div align="left">2、TIMEOUTS:</div>
			<div align="left">
				<div class="inline">
					connect: <input type="text" name="HTTPSampler.connect_timeout"
						value="" />
				</div>
				<div class="inline">
					response: <input type="text" name="HTTPSampler.response_timeout"
						value="" />
				</div>
			</div>
			<div align="left">3、http请求：</div>
			<div align="left">
				<div align="left">
					<div class="inline">
						implementation: <select name="implementation">
							<option value="HttpClient4">HttpClient4</option>
							<option value="HttpClient3.1">HttpClient3.1</option>
							<option value="java">Java</option>
							<option value="" selected="selected"></option>
						</select>
					</div>
					<div class="inline">
						协议:<input type="text" name="HTTPSampler.protocol" value="" />
					</div>
					<div class="inline">
						方法: <select name="HTTPSampler.method" onchange="check4()">
							<option value="GET" selected="selected">Get</option>
							<option value="POST" id="op1">Post</option>
							<option value="HEAD">Head</option>
						</select>
					</div>
					<div class="inline">
						content encoding: <input type="text"
							name="HTTPSampler.contentEncoding" value="" />
					</div>
				</div>
				<div align="left">
					路径：<input type="text" name="HTTPSampler.path" value=""
						style=" width:600px " />
				</div>
			</div>
			<div align="left">
				<div class="inline">
					<input name="checkbox" type="checkbox" value="che2" id="check1"
						onclick="check()" />自动重定向
				</div>
				<div class="inline">
					<input name="checkbox" type="checkbox" value="che3"
						checked="checked" id="check2" onclick="chec()" />跟随重定向
				</div>
				<div class="inline">
					<input name="checkbox" type="checkbox" value="che4"
						checked="checked" />use keepalive
				</div>
				<div class="inline">
					<input name="checkbox" type="checkbox" value="che5" id="check3"
						disabled />use multipart/form-data Post
				</div>
				<div class="inline">
					<input name="checkbox" type="checkbox" value="che6" />browser-compatible
					headers
				</div>
			</div>
			<div align="left">4、用户变量：</div>
			<div align="center">
				<div align="center">
					<table border="1" cellspacing="0" width="300px" id="postTable">
						<caption>同请求一起发送的参数</caption>
						<tr>
							<th>名称</th>
							<th>值</th>
						</tr>
						<tr>
							<td contentEditable="true">j_usrname</td>
							<td contentEditable="true">hhhhhh</td>
						</tr>
						<tr>
							<td contentEditable="true">j_password</td>
							<td contentEditable="true">123123</td>
						</tr>
					</table>
				</div>
				<div class="inline">
					<button type="button" onclick="addrow_post('postTable')">添加</button>
				</div>
				<div class="inline">
					<button type="button" onclick="deleterow('postTable')">删除</button>
				</div>
				<div class="inline">
					<button type="button" onclick="TableToJson_post('postTable')">保存</button>
				</div>
			</div>

			<div align="left" style="margin-top:1%">
				<b>HTTP响应断言</b>
			</div>
			<div align="left" style="margin-top: 2%;">1、apply to:</div>
			<div align="left">
				<div class="inline">
					<input name="radio2" type="radio" value="all" />Main sample and
					sub-samples
				</div>
				<div class="inline">
					<input name="radio2" type="radio" value="self" checked="checked" />Main
					sample only
				</div>
				<div class="inline">
					<input name="radio2" type="radio" value="children" />Sub-samoles
					only
				</div>
			</div>
			<div align="left">2、要测试的响应字段:</div>
			<div align="left">
				<div class="inline">
					<input name="radio3" type="radio" value="Assertion.response_data"
						checked="checked" />响应文本
				</div>
				<div class="inline">
					<input name="radio3" type="radio"
						value="Assertion.response_data_as_document" />Document(text)
				</div>
				<div class="inline">
					<input name="radio3" type="radio" value="Assertion.sample_label" />URL样本
				</div>
				<div class="inline">
					<input name="radio3" type="radio" value="Assertion.response_code" />响应代码
				</div>
				<div class="inline">
					<input name="radio3" type="radio"
						value="Assertion.response_message" />响应信息
				</div>
				<div class="inline">
					<input name="radio3" type="radio"
						value="Assertion.response_headers" />Response Headers
				</div>
			</div>
			<div align="left">3、模式匹配规则:</div>
			<div align="left">
				<div class="inline">
					<input name="radio4" type="radio" value="2" />包括
				</div>
				<div class="inline">
					<input name="radio4" type="radio" value="1" />匹配
				</div>
				<div class="inline">
					<input name="radio4" type="radio" value="8" />Equals
				</div>
				<div class="inline">
					<input name="radio4" type="radio" value="16" checked="checked" />Substring
				</div>
			</div>
			<div align="left">4、要测试的模式：</div>
			<div align="center">
				<div align="center">
					<table border="1" cellspacing="0" width="300px" id="assertionTable">
						<caption>要测试的字段</caption>
						<tr>
							<th>字段</th>
						</tr>
						<tr>
							<td contentEditable="true">success</td>
						</tr>
					</table>
				</div>
				<div class="inline">
					<button type="button" onclick="addrow_assertion('assertionTable')">添加</button>
				</div>
				<div class="inline">
					<button type="button" onclick="deleterow('assertionTable')">删除</button>
				</div>
				<div class="inline">
					<button type="button"
						onclick="TableToJson_assertion('assertionTable')">保存</button>
				</div>
			</div>
			<div align="left">5、是否使用测试字段：</div>
			<div align="left">
				<div class="inline">
					<input name="radio_assertion" type="radio" value="true"
						checked="checked" />是
				</div>
				<div class="inline">
					<input name="radio_assertion" type="radio" value="false" />否
				</div>
			</div>

			<div align="left" style="margin-top:1%">
				<b>测试数据写入新文件</b>
			</div>
			<div align="left" style="margin-top: 2%;">
				新文件名：<input type="text" name="newfilename" value="" />
			</div>
			<div align="left" style="margin-top:1%">
				<b>添加远程执行机IP地址</b>
			</div>
			<div align="left" style="margin-top: 2%;">
				<div align="left">
					远程执行机1<input type="text" name="slave1" value="null" />
				</div>
				<div align="left">
					远程执行机2<input type="text" name="slave2" value="null" />
				</div>
				<div align="left">
					远程执行机3<input type="text" name="slave3" value="null" />
				</div>
				<div align="left">
					远程执行机4<input type="text" name="slave4" value="null" />
				</div>
			</div>
			<div align="left">以下两个参数请不要自行修改</div>
			<div align="left">
				<div class="inline">
					Post参数：<input type="text" name="param"
						value='{"param":[{"name":"j_usrname","value":"hhhhhh"}]}'
						id="para" />
				</div>
				<div class="inline">
					Assertion参数：<input type="text" name="param1"
						value='{"param":[{"assertion":"success"}]}' id="para1" />
				</div>
			</div>

			<div align="left">
				<input type="submit" value="提交" />
			</div>

		</form>
	</div>




	<script>
		function displaytemplate() {
			var myselect = document.getElementById("test");
			var index = myselect.selectedIndex;
			document.getElementById("p1").innerHTML = "你选择了"
					+ myselect.options[index].text + "模板";
			if (myselect.options[index].text == "JDBC") {
				document.getElementById("d4").style.display = "block";
				document.getElementById("d3").style.display = "none";
			} else {
				document.getElementById("d3").style.display = "block";
				document.getElementById("d4").style.display = "none";
			}
		}

		function check() {
			document.getElementById("check2").checked = true;
			if (document.getElementById("check1").checked)
				document.getElementById("check2").checked = false;
		}
		function chec() {
			if (document.getElementById("check2").checked) {
				document.getElementById("check1").checked = false;
			}
		}
		function check4() {
			document.getElementById("check3").disabled = true;
			if (document.getElementById("op1").selected)
				document.getElementById("check3").disabled = false;
		}
		function addrow_post(tableid) {
			var table = document.getElementById(tableid);
			var row = table.insertRow(table.rows.length);
			var cell1 = row.insertCell(0);
			var cell2 = row.insertCell(1);
			cell1.innerHTML = "New";
			cell2.innerHTML = "New";
			cell1.setAttribute('contentEditable', 'true');
			cell2.setAttribute('contentEditable', 'true');
		}
		function addrow_assertion(tableid) {
			var table = document.getElementById(tableid);
			var row = table.insertRow(table.rows.length);
			var cell1 = row.insertCell(0);
			cell1.innerHTML = "New";
			cell1.setAttribute('contentEditable', 'true');
		}
		function deleterow(tableid) {
			var table = document.getElementById(tableid);
			var i = table.rows.length - 1;
			if (i != 0)
				table.deleteRow(i);
		}

		function TableToJson_post(tableid) { //tableid是你要转化的表的表名，是一个字符串，如"example" 
			var rows = document.getElementById(tableid).rows.length; //获得行数(包括thead) 
			var json = '{"param":[';
			for (var i = 1; i < rows; i++) { //每行 
				json += '{';
				json += '"name"'; //JSON数据的键 
				json += ':';
				json += '"';
				json += document.getElementById(tableid).rows[i].cells[0].innerHTML;
				json += '"';
				json += ',';
				json += '"value"'; //JSON数据的值
				json += ':';
				json += '"';
				json += document.getElementById(tableid).rows[i].cells[1].innerHTML;
				json += '"';
				json += '}';
				json += ',';
			}
			json = json.substring(0, json.length - 1);
			json += ']}';
			document.getElementById("para").value = '' + json + '';
		}
		function TableToJson_assertion(tableid) { //tableid是你要转化的表的表名，是一个字符串，如"example" 
			var rows = document.getElementById(tableid).rows.length; //获得行数(包括thead) 
			var json = '{"param":[';
			for (var i = 1; i < rows; i++) { //每行 
				json += '{';
				json += '"assertion"'; //JSON数据的键 
				json += ':';
				json += '"';
				json += document.getElementById(tableid).rows[i].cells[0].innerHTML;
				json += '"';
				json += '}';
				json += ',';
			}
			json = json.substring(0, json.length - 1);
			json += ']}';
			document.getElementById("para1").value = '' + json + '';
		}
	</script>
</body>
</html>
