package com.tal.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 执行监本文件的servlet
 * 
 * @author 吴海飞
 * @d2016年12月27日
 */
public class startCmdServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6670203831371945502L;

	/**
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

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

		String path = this.getServletContext().getRealPath("/");
		System.out.println("" + path);

		try {
			int state = Runtime
					.getRuntime()
					.exec(path + "/WEB-INF/data/ceshi.bat " + remoteIp_1 + " "
							+ remoteIp_2 + " " + remoteIp_3 + " " + remoteIp_4
							+ " " + filePath + " " + isRemote).waitFor();
			if (state == 0) {
				System.out.println("脚本正常执行……");
			} else {
				System.out.println("脚本未执行……");
			}
			response.sendRedirect("/output/output/index.html");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
