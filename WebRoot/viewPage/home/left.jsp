<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="../../css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="../../js/jquery.js"></script>

<script type="text/javascript">
	$(function() {
		//导航切换
		$(".menuson li").click(function() {
			$(".menuson li.active").removeClass("active");
			$(this).addClass("active");
		});

		$('.title').click(function() {
			var $ul = $(this).next('ul');
			$('dd').find('ul').slideUp();
			if ($ul.is(':visible')) {
				$(this).next('ul').slideUp();
			} else {
				$(this).next('ul').slideDown();
			}
		});
	});
</script>


</head>

<body style="background:#f0f9fd;">
	<div class="lefttop">
		<span></span>系统功能
	</div>

	<dl class="leftmenu">

		<dd>
			<div class="title">
				<span><img src="../../images/leftico01.png" /></span>测试计划
			</div>
			<ul class="menuson">
				<li><cite></cite><a href="index.jsp" target="rightFrame">HTTP模板</a><i></i></li>
				<li><cite></cite><a href="../textPlan/upload.jsp"
					target="rightFrame">自定义脚本上传</a><i></i></li>
				<li><cite></cite><a href="/result/test.html" target="rightFrame">测试报告响应结果</a><i></i></li>
			</ul>
		</dd>

		<dd>
			<div class="title">
				<span><img src="../../images/leftico02.png" /></span>其他设置
			</div>
			<ul class="menuson">
				<li><cite></cite><a href="#">编辑内容</a><i></i></li>
				<li><cite></cite><a href="#">发布信息</a><i></i></li>
				<li><cite></cite><a href="#">档案列表显示</a><i></i></li>
			</ul>
		</dd>


		<dd>
			<div class="title">
				<span><img src="../../images/leftico03.png" /></span>编辑器
			</div>
			<ul class="menuson">
				<li><cite></cite><a href="#">自定义</a><i></i></li>
				<li><cite></cite><a href="#">常用资料</a><i></i></li>
				<li><cite></cite><a href="#">信息列表</a><i></i></li>
				<li><cite></cite><a href="#">其他</a><i></i></li>
			</ul>
		</dd>

		<dd>
			<div class="title">
				<span><img src="../../images/leftico04.png" /></span>日期管理
			</div>
			<ul class="menuson">
				<li><cite></cite><a href="#">自定义</a><i></i></li>
				<li><cite></cite><a href="#">常用资料</a><i></i></li>
				<li><cite></cite><a href="#">信息列表</a><i></i></li>
				<li><cite></cite><a href="#">其他</a><i></i></li>
			</ul>
		</dd>
	</dl>
</body>
</html>