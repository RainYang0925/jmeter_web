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
		 * 获取网页参数
		 */
		String remoteIp_1 = request.getParameter("slaveip1");
		String remoteIp_2 = request.getParameter("slaveip2");
		String remoteIp_3 = request.getParameter("slaveip3");
		String remoteIp_4 = request.getParameter("slaveip4");
		String filePath = request.getParameter("filepath");

		String isRemote = request.getParameter("radio");

		System.out.println("开始执行脚本");

		// String path = this.getServletContext().getRealPath("/");
		// System.out.println("" + path);
		// "/bin/sh " +
		// String exePath = PropertiesReadUtils.getString("antExePath");
		String[] cmd = {
				"/bin/sh",
				"-c",
				"ant -buildfile /home/jmeter/script/build.xml " + "-DIPvalue1="
						+ remoteIp_1 + " -DIPvalue2=" + remoteIp_2
						+ " -DIPvalue3=" + remoteIp_3 + " -DIPvalue4="
						+ remoteIp_4 + " -Dfilename=" + filePath + " -Dremote="
						+ isRemote };
		try {
			int state = Runtime.getRuntime().exec(cmd).waitFor();
			if (state == 0) {
				System.out.println("脚本正常执行……");
				response.sendRedirect("/output/output/index.html");
			} else {
				System.out.println("脚本未执行……");
				request.getRequestDispatcher("/viewPage/errorPage/shError.jsp")
						.forward(request, response);
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
