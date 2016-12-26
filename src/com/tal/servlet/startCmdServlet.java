package com.tal.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 调用shell文件，执行测试的servlet
 * 
 * @author 吴海飞
 * @d2016年12月22日
 */
public class startCmdServlet extends HttpServlet {

	/**
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	/**
	 * 执行脚本文件，生成测试报告，在页面显示测试报告
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setHeader("content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");

		/*
		 * $path1=$_POST["slaveip1"]; $path2=$_POST["slaveip2"];
		 * $path3=$_POST["slaveip3"]; $path4=$_POST["slaveip4"];
		 * $path6=$_POST["filepath"]; $path7=$_POST["radio"];
		 */

		/*
		 * 获取网页参数
		 */
		String remoteIp_1 = request.getParameter("slaveip1");
		String remoteIp_2 = request.getParameter("slaveip2");
		String remoteIp_3 = request.getParameter("slaveip3");
		String remoteIp_4 = request.getParameter("slaveip4");
		String filePath = request.getParameter("filepath");

		String isRemote = request.getParameter("radio");

		System.out.println("是否进行远程测试：" + isRemote);

		try {
			int state = Runtime
					.getRuntime()
					.exec("./WEB-INF/data/ceshi.bat " + remoteIp_1 + " "
							+ remoteIp_2 + " " + remoteIp_3 + " " + remoteIp_4
							+ " " + filePath + " " + isRemote).waitFor();// 执行脚本文件
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (isRemote.equals("true")) {
			response.getWriter().write("执行cmd命令，生成测试报告！");
		}

	}
}
