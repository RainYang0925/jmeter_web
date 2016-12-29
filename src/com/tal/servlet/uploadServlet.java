package com.tal.servlet;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tal.utils.PropertiesReadUtils;

/**
 * 
 * @author 吴海飞
 * @d2016年12月27日
 */
public class uploadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6495305140683263217L;

	/**
	 * Constructor of the object.
	 */
	public uploadServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setHeader("content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");

		// request.setCharacterEncoding("gbk");
		// response.setHeader("content-type", "text/html;charset=gbk");
		// response.setCharacterEncoding("gbk");

		int MAX_SIZE = 102400 * 102400;
		String rootPath;
		// String filePath = null;
		DataInputStream in = null;
		FileOutputStream fileOut = null;
		// String remoteAddr = request.getRemoteAddr();
		// String serverName = request.getServerName();

		// String realPath = request.getRealPath(serverName);
		// realPath = realPath.substring(0, realPath.lastIndexOf("\\"));
		// rootPath = realPath + "\\WEB-INF\\jmx\\";
		rootPath = PropertiesReadUtils.getString("uploadFilePath");
		String contentType = request.getContentType();
		try {
			if (contentType.indexOf("multipart/form-data") >= 0) {
				in = new DataInputStream(request.getInputStream());
				int formDataLength = request.getContentLength();
				if (formDataLength > MAX_SIZE) {
					response.getWriter().println("<P>文件过大" + MAX_SIZE + "</p>");
					return;
				}
				byte dataBytes[] = new byte[formDataLength];
				int byteRead = 0;
				int totalBytesRead = 0;
				while (totalBytesRead < formDataLength) {
					byteRead = in.read(dataBytes, totalBytesRead,
							formDataLength);
					totalBytesRead += byteRead;
				}
				String file = new String(dataBytes);
				// out.println(file);
				String saveFile = file
						.substring(file.indexOf("filename=\"") + 10);
				saveFile = saveFile.substring(0, saveFile.indexOf("\n"));
				saveFile = saveFile.substring(saveFile.lastIndexOf("\\") + 1,
						saveFile.indexOf("\""));

				System.out.println("上传文件名：" + saveFile);
				request.setAttribute("filePath", saveFile);
				request.setAttribute("remote_ip1", null);
				request.setAttribute("remote_ip2", null);
				request.setAttribute("remote_ip3", null);
				request.setAttribute("remote_ip4", null);
				// filePath = saveFile;
				int lastIndex = contentType.lastIndexOf("=");
				String boundary = contentType.substring(lastIndex + 1,
						contentType.length());
				String fileName = rootPath + saveFile;
				System.out.println(fileName);
				// out.print(fileName);
				int pos;
				pos = file.indexOf("filename=\"");
				pos = file.indexOf("\n", pos) + 1;
				pos = file.indexOf("\n", pos) + 1;
				pos = file.indexOf("\n", pos) + 1;
				int boundaryLocation = file.indexOf(boundary, pos) - 4;
				int startPos = ((file.substring(0, pos)).getBytes()).length;
				int endPos = ((file.substring(0, boundaryLocation)).getBytes()).length;
				File fileDir = new File(rootPath);
				if (!fileDir.exists()) {
					fileDir.mkdirs();
				}
				fileOut = new FileOutputStream(fileName);
				fileOut.write(dataBytes, startPos, (endPos - startPos));
				fileOut.close();
			} else {
				// String content = request.getContentType();
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}

		request.getRequestDispatcher("/setParameter.jsp").forward(request,
				response);
	}
}
