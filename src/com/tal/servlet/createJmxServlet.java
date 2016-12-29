package com.tal.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.json.JSONArray;
import org.json.JSONObject;

import com.tal.utils.PropertiesReadUtils;

/**
 * 
 * @author 吴海飞
 * @d2016年12月27日
 */
public class createJmxServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7804956990760251237L;

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置编码方式
		request.setCharacterEncoding("utf-8");
		response.setHeader("content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");

		String s1 = request.getParameter("ThreadGroup.num_threads");
		String s2 = request.getParameter("ThreadGroup.ramp_time");
		String s3 = request.getParameter("newfilename");
		String s29 = request.getParameter("newresultname");
		String s4 = request.getParameter("tpname");
		// String path = getServletContext().getRealPath("/");
		// String s5 = path + "\\WEB-INF\\jmx\\";
		String s5 = PropertiesReadUtils.getString("uploadFilePath");
		String s7 = ".jmx";
		String s8 = ".jtl";
		String s6 = s5 + s3;
		String s9 = s5 + s29 + s8;
		String s10 = request.getParameter("LoopController.loops");
		String s11 = request.getParameter("HTTPSampler.domain");
		String s12 = request.getParameter("radio");
		String s13 = request.getParameter("HTTPSampler.port");
		String s14 = request.getParameter("HTTPSampler.connect_timeout");
		String s15 = request.getParameter("HTTPSampler.response_timeout");
		String s16 = request.getParameter("implementation");
		String s17 = request.getParameter("HTTPSampler.protocol");
		String s18 = request.getParameter("HTTPSampler.method");
		String s19 = request.getParameter("HTTPSampler.contentEncoding");
		String s20 = request.getParameter("HTTPSampler.path");
		String s21 = request.getParameter("varname");
		String s22 = request.getParameter("varvalue");
		String s23 = request.getParameter("passname");
		String s24 = request.getParameter("passvalue");
		String s25 = request.getParameter("radio2");
		String s26 = request.getParameter("radio3");
		String s27 = request.getParameter("radio4");
		String s28 = request.getParameter("assertion1");
		String[] s30 = request.getParameterValues("checkbox");
		String s32 = request.getParameter("slave1");
		String s33 = request.getParameter("slave2");
		String s34 = request.getParameter("slave3");
		String s35 = request.getParameter("slave4");
		String s36 = request.getParameter("radio_assertion");

		String str = request.getParameter("param");
		request.setAttribute("filePath", s3);
		request.setAttribute("remote_ip1", s32);
		request.setAttribute("remote_ip2", s33);
		request.setAttribute("remote_ip3", s34);
		request.setAttribute("remote_ip4", s35);

		JSONObject obj;
		try {
			obj = new JSONObject(str);
			JSONArray data = obj.getJSONArray("param");
			String[] str_name = new String[data.length()];
			String[] str_value = new String[data.length()];
			for (int i = 0; i < data.length(); i++) {
				JSONObject data1 = data.getJSONObject(i);
				str_name[i] = data1.getString("name");
				str_value[i] = data1.getString("value");
			}

			String str1 = request.getParameter("param1");
			JSONObject obj1 = new JSONObject(str1);
			JSONArray data1 = obj1.getJSONArray("param");
			String[] str_assertion = new String[data1.length()];
			for (int i = 0; i < data1.length(); i++) {
				JSONObject data2 = data1.getJSONObject(i);
				str_assertion[i] = data2.getString("assertion");
			}

			if (s36.equals("true")) {
				if (s18.equals("POST")) {
					testchange(s1, s2, s6, s10, s11, s12, s13, s14, s15, s16,
							s17, s18, s19, s20, s21, s22, s23, s24, s25, s26,
							s27, s28, s29, s30, str_name, str_value,
							str_assertion);
				} else {
					testchange1(s1, s2, s6, s10, s11, s12, s13, s14, s15, s16,
							s17, s18, s19, s20, s21, s22, s23, s24, s25, s26,
							s27, s28, s29, s30, str_assertion);
				}
			} else {
				if (s18.equals("POST")) {
					testchange2(s1, s2, s6, s10, s11, s12, s13, s14, s15, s16,
							s17, s18, s19, s20, s21, s22, s23, s24, s25, s26,
							s27, s28, s29, s30, str_name, str_value);
				} else {
					testchange3(s1, s2, s6, s10, s11, s12, s13, s14, s15, s16,
							s17, s18, s19, s20, s21, s22, s23, s24, s25, s26,
							s27, s28, s29, s30);
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			// throw new RuntimeException();
		}

		request.getRequestDispatcher("/setParameter.jsp").forward(request,
				response);
	}

	public String testchange(String s1, String s2, String s6, String s10,
			String s11, String s12, String s13, String s14, String s15,
			String s16, String s17, String s18, String s19, String s20,
			String s21, String s22, String s23, String s24, String s25,
			String s26, String s27, String s28, String s29, String[] s30,
			String[] str_name, String[] str_value, String[] str_assertion)
			throws Exception {
		SAXReader sax = new SAXReader();
		String path = getServletContext().getRealPath("/");
		File xmlFile = new File(path + "WEB-INF/templateXML/newfortest.xml");
		Document document = sax.read(xmlFile);
		Element root = document.getRootElement();
		String xpath1 = "//stringProp[@name]";
		List<Element> list1 = document.selectNodes(xpath1);
		for (int i = 0; i < list1.size(); i++) {
			Element student = list1.get(i);
			switch (student.attributeValue("name")) {
			case "ThreadGroup.num_threads":
				student.setText(s1);
				break;
			case "ThreadGroup.ramp_time":
				student.setText(s2);
				break;
			case "LoopController.loops":
				student.setText(s10);
				break;
			case "HTTPSampler.domain":
				student.setText(s11);
				if (s16.isEmpty() == false) {
					Element stu = student.getParent().addElement("stringProp");
					stu.addAttribute("name", "HTTPSampler.implementation");
					stu.addText(s16);
				}
				break;
			case "ThreadGroup.on_sample_error":
				student.setText(s12);
				break;
			case "HTTPSampler.port":
				if (s13.equals(""))
					break;
				else {
					student.setText(s13);
					break;
				}
			case "HTTPSampler.connect_timeout":
				if (s14.equals(""))
					break;
				else {
					student.setText(s14);
					break;
				}
			case "HTTPSampler.response_timeout":
				if (s15.equals(""))
					break;
				else {
					student.setText(s15);
					break;
				}
			case "HTTPSampler.protocol":
				if (s17.equals(""))
					break;
				else {
					student.setText(s17);
					break;
				}
			case "HTTPSampler.method":
				if (s18.equals(""))
					break;
				else {
					student.setText(s18);
					break;
				}
			case "HTTPSampler.contentEncoding":
				if (s19.equals(""))
					break;
				else {
					student.setText(s19);
					break;
				}
			case "HTTPSampler.path":
				if (s20.equals(""))
					break;
				else {
					student.setText(s20);
					break;
				}
			case "Assertion.test_field":
				student.setText(s26);
				if (s25.equals("self"))
					break;
				else {
					Element stu1 = student.getParent().addElement("stringProp");
					stu1.addAttribute("name", "HTTPSampler.implementation");
					stu1.addText(s25);
					break;
				}
			default:
				break;
			}
		}
		String xpath2 = "//elementProp[@name]";
		List<Element> list2 = document.selectNodes(xpath2);
		for (int j = 0; j < list2.size(); j++) {
			Element student1 = list2.get(j);
			switch (student1.attributeValue("name")) {
			case "HTTPsampler.Arguments":
				student1.remove(student1.selectSingleNode("collectionProp"));
				Element stu2 = student1.addElement("collectionProp");
				stu2.addAttribute("name", "Arguments.arguments");
				for (int i = 0; i < str_name.length; i++) {
					Element stu21 = stu2.addElement("elementProp");
					stu21.addAttribute("name", str_name[i]);
					stu21.addAttribute("elementType", "HTTPArgument");
					Element stu211 = stu21.addElement("boolProp");
					stu211.addAttribute("name", "HTTPArgument.always_encode");
					stu211.addText("false");
					Element stu212 = stu21.addElement("stringProp");
					stu212.addAttribute("name", "Argument.value");
					stu212.addText(str_value[i]);
					Element stu213 = stu21.addElement("stringProp");
					stu213.addAttribute("name", "Argument.metadata");
					stu213.addText("=");
					Element stu214 = stu21.addElement("boolProp");
					stu214.addAttribute("name", "HTTPArgument.use_equals");
					stu214.addText("true");
					Element stu215 = stu21.addElement("stringProp");
					stu215.addAttribute("name", "Argument.name");
					stu215.addText(str_name[i]);
				}
				break;

			default:
				break;
			}
		}

		String xpath3 = "//boolProp[@name]";
		List<Element> list3 = document.selectNodes(xpath3);
		for (int k = 0; k < list3.size(); k++) {
			Element student2 = list3.get(k);
			switch (student2.attributeValue("name")) {
			case "LoopController.continue_forever":
				student2.setText("false");
				for (int l = 0; l < s30.length; l++) {
					if (s30[l].equals("che1"))
						student2.setText("true");
				}
				break;
			case "HTTPSampler.auto_redirects":
				student2.setText("false");
				for (int l = 0; l < s30.length; l++) {
					if (s30[l].equals("che2"))
						student2.setText("true");

				}
				break;
			case "HTTPSampler.follow_redirects":
				student2.setText("false");
				for (int l = 0; l < s30.length; l++) {
					if (s30[l].equals("che3"))
						student2.setText("true");

				}
				break;
			case "HTTPSampler.use_keepalive":
				student2.setText("false");
				for (int l = 0; l < s30.length; l++) {
					if (s30[l].equals("che4"))
						student2.setText("true");

				}
				break;
			case "HTTPSampler.DO_MULTIPART_POST":
				student2.setText("false");
				for (int l = 0; l < s30.length; l++) {
					if (s30[l].equals("che5"))
						student2.setText("true");

				}
				break;
			case "HTTPSampler.BROWSER_COMPATIBLE_MULTIPART":
				student2.setText("false");
				for (int l = 0; l < s30.length; l++) {
					if (s30[l].equals("che6"))
						student2.setText("true");

				}
				break;
			default:
				break;
			}

		}

		String xpath4 = "//ResponseAssertion[@testname]";
		List<Element> list4 = document.selectNodes(xpath4);
		for (int n = 0; n < list4.size(); n++) {
			Element student4 = list4.get(n);
			if (student4.attributeValue("testname")
					.equals("Response Assertion")) {
				Element stu4 = student4.getParent().addElement("HeaderManager");
				stu4.addAttribute("guiclass", "HeaderPanel");
				stu4.addAttribute("testclass", "HeaderManager");
				stu4.addAttribute("testname", "HTTP Header Manager");
				stu4.addAttribute("enabled", "true");
				Element stu41 = stu4.addElement("collectionProp");
				stu41.addAttribute("name", "HeaderManager.headers");
				Element stu411 = stu41.addElement("elementProp");
				stu411.addAttribute("name", "");
				stu411.addAttribute("elementType", "Header");
				Element stu4111 = stu411.addElement("stringProp");
				stu4111.addAttribute("name", "Header.name");
				stu4111.addText("name");
				Element stu4112 = stu411.addElement("stringProp");
				stu4112.addAttribute("name", "Header.value");
				stu4112.addText("Content-Type");
				Element stu412 = stu41.addElement("elementProp");
				stu412.addAttribute("name", "");
				stu412.addAttribute("elementType", "Header");
				Element stu4121 = stu412.addElement("stringProp");
				stu4121.addAttribute("name", "Header.name");
				stu4121.addText("value");
				Element stu4122 = stu412.addElement("stringProp");
				stu4122.addAttribute("name", "Header.value");
				stu4122.addText("application/json;charset=utf-8");
				Element stu5 = student4.getParent().addElement("hashTree");
			}
		}

		String xpath5 = "//ResponseAssertion[@testname]";
		List<Element> list5 = document.selectNodes(xpath5);
		for (int n = 0; n < list5.size(); n++) {
			Element student5 = list5.get(n);
			if (student5.attributeValue("testname")
					.equals("Response Assertion")) {
				student5.remove(student5.selectSingleNode("collectionProp"));
				Element stu52 = student5.addElement("collectionProp");
				stu52.addAttribute("name", "Asserion.test_strings");
				for (int i = 0; i < str_assertion.length; i++) {
					Element stu521 = stu52.addElement("stringProp");
					stu521.addAttribute("name",
							"" + str_assertion[i].hashCode());
					stu521.addText(str_assertion[i]);
				}

			}

		}

		Writer osWrite = new OutputStreamWriter(new FileOutputStream(s6),
				"UTF-8");
		OutputFormat format = OutputFormat.createPrettyPrint(); //
		format.setEncoding("UTF-8");//
		XMLWriter writer = new XMLWriter(osWrite, format);// XMLWriter

		writer.write(document);
		writer.flush();
		writer.close();
		return "��Ĳ����ѳɹ��ύ";
	}

	public String testchange1(String s1, String s2, String s6, String s10,
			String s11, String s12, String s13, String s14, String s15,
			String s16, String s17, String s18, String s19, String s20,
			String s21, String s22, String s23, String s24, String s25,
			String s26, String s27, String s28, String s29, String[] s30,
			String[] str_assertion) throws Exception {
		SAXReader sax = new SAXReader();
		String path = getServletContext().getRealPath("/");
		File xmlFile = new File(path + "WEB-INF/templateXML/newfortest.xml");
		Document document = sax.read(xmlFile);
		Element root = document.getRootElement();
		String xpath1 = "//stringProp[@name]";
		List<Element> list1 = document.selectNodes(xpath1);
		// return list.size();
		// ��ĳ���ڵ����һ���ֵܽڵ�
		/*
		 * Element stu=student.getParent().addElement("stringProp");
		 * stu.addAttribute("number", "ITCAST_0003"); stu.addText("java");
		 */
		// 2.�����
		for (int i = 0; i < list1.size(); i++) {
			// ����Ԫ�ض���
			Element student = list1.get(i);
			switch (student.attributeValue("name")) {
			case "ThreadGroup.num_threads":
				student.setText(s1);
				break;
			case "ThreadGroup.ramp_time":
				student.setText(s2);
				break;
			case "LoopController.loops":
				student.setText(s10);
				break;
			case "HTTPSampler.domain":
				student.setText(s11);
				if (s16.isEmpty() == false) {
					Element stu = student.getParent().addElement("stringProp");
					stu.addAttribute("name", "HTTPSampler.implementation");
					stu.addText(s16);
				}
				break;
			case "ThreadGroup.on_sample_error":
				student.setText(s12);
				break;
			case "HTTPSampler.port":
				if (s13.equals(""))
					break;
				else {
					student.setText(s13);
					break;
				}
			case "HTTPSampler.connect_timeout":
				if (s14.equals(""))
					break;
				else {
					student.setText(s14);
					break;
				}
			case "HTTPSampler.response_timeout":
				if (s15.equals(""))
					break;
				else {
					student.setText(s15);
					break;
				}
			case "HTTPSampler.protocol":
				if (s17.equals(""))
					break;
				else {
					student.setText(s17);
					break;
				}
			case "HTTPSampler.method":
				if (s18.equals(""))
					break;
				else {
					student.setText(s18);
					break;
				}
			case "HTTPSampler.contentEncoding":
				if (s19.equals(""))
					break;
				else {
					student.setText(s19);
					break;
				}
			case "HTTPSampler.path":
				if (s20.equals(""))
					break;
				else {
					student.setText(s20);
					break;
				}
			case "Assertion.test_field":
				student.setText(s26);
				if (s25.equals("self"))
					break;
				else {
					Element stu1 = student.getParent().addElement("stringProp");
					stu1.addAttribute("name", "HTTPSampler.implementation");
					stu1.addText(s25);
					break;
				}

			default:
				break;
			}

		}

		String xpath3 = "//boolProp[@name]";
		List<Element> list3 = document.selectNodes(xpath3);
		for (int k = 0; k < list3.size(); k++) {
			Element student2 = list3.get(k);
			switch (student2.attributeValue("name")) {
			case "LoopController.continue_forever":
				student2.setText("false");
				for (int l = 0; l < s30.length; l++) {
					if (s30[l].equals("che1"))
						student2.setText("true");
				}
				break;
			case "HTTPSampler.auto_redirects":
				student2.setText("false");
				for (int l = 0; l < s30.length; l++) {
					if (s30[l].equals("che2"))
						student2.setText("true");

				}
				break;
			case "HTTPSampler.follow_redirects":
				student2.setText("false");
				for (int l = 0; l < s30.length; l++) {
					if (s30[l].equals("che3"))
						student2.setText("true");

				}
				break;
			case "HTTPSampler.use_keepalive":
				student2.setText("false");
				for (int l = 0; l < s30.length; l++) {
					if (s30[l].equals("che4"))
						student2.setText("true");

				}
				break;
			case "HTTPSampler.DO_MULTIPART_POST":
				student2.setText("false");
				for (int l = 0; l < s30.length; l++) {
					if (s30[l].equals("che5"))
						student2.setText("true");

				}
				break;
			case "HTTPSampler.BROWSER_COMPATIBLE_MULTIPART":
				student2.setText("false");
				for (int l = 0; l < s30.length; l++) {
					if (s30[l].equals("che6"))
						student2.setText("true");

				}
				break;
			default:
				break;
			}

		}

		// ���Ӷ��Խڵ�

		String xpath4 = "//ResponseAssertion[@testname]";
		List<Element> list4 = document.selectNodes(xpath4);
		for (int n = 0; n < list4.size(); n++) {
			Element student4 = list4.get(n);
			if (student4.attributeValue("testname")
					.equals("Response Assertion")) {
				student4.remove(student4.selectSingleNode("collectionProp"));
				Element stu2 = student4.addElement("collectionProp");
				stu2.addAttribute("name", "Asserion.test_strings");
				for (int i = 0; i < str_assertion.length; i++) {
					Element stu21 = stu2.addElement("stringProp");
					stu21.addAttribute("name", "" + str_assertion[i].hashCode());
					stu21.addText(str_assertion[i]);
				}

			}

		}

		Writer osWrite = new OutputStreamWriter(new FileOutputStream(s6),
				"UTF-8");// ���������
		OutputFormat format = OutputFormat.createPrettyPrint(); // ��ȡ�����ָ����ʽ
		format.setEncoding("UTF-8");// ���ñ��� ��ȷ��������xmlΪUTF-8��ʽ
		XMLWriter writer = new XMLWriter(osWrite, format);// XMLWriter
															// ָ������ļ��Լ���ʽ
		writer.write(document);// ��documentд��xmlFileָ�����ļ�(����Ϊ���������ļ������´������ļ�)
		writer.flush();
		writer.close();
		return "��Ĳ����ѳɹ��ύ";
	}

	public String testchange2(String s1, String s2, String s6, String s10,
			String s11, String s12, String s13, String s14, String s15,
			String s16, String s17, String s18, String s19, String s20,
			String s21, String s22, String s23, String s24, String s25,
			String s26, String s27, String s28, String s29, String[] s30,
			String[] str_name, String[] str_value) throws Exception {
		SAXReader sax = new SAXReader();
		String path = getServletContext().getRealPath("/");
		File xmlFile = new File(path + "WEB-INF/templateXML/newfortest.xml");
		Document document = sax.read(xmlFile);
		Element root = document.getRootElement();
		String xpath1 = "//stringProp[@name]";
		List<Element> list1 = document.selectNodes(xpath1);
		// return list.size();
		// ��ĳ���ڵ����һ���ֵܽڵ�
		/*
		 * Element stu=student.getParent().addElement("stringProp");
		 * stu.addAttribute("number", "ITCAST_0003"); stu.addText("java");
		 */
		// 2.�����
		for (int i = 0; i < list1.size(); i++) {
			// ����Ԫ�ض���
			Element student = list1.get(i);
			switch (student.attributeValue("name")) {
			case "ThreadGroup.num_threads":
				student.setText(s1);
				break;
			case "ThreadGroup.ramp_time":
				student.setText(s2);
				break;
			case "LoopController.loops":
				student.setText(s10);
				break;
			case "HTTPSampler.domain":
				student.setText(s11);
				if (s16.isEmpty() == false) {
					Element stu = student.getParent().addElement("stringProp");
					stu.addAttribute("name", "HTTPSampler.implementation");
					stu.addText(s16);
				}
				break;
			case "ThreadGroup.on_sample_error":
				student.setText(s12);
				break;
			case "HTTPSampler.port":
				if (s13.equals(""))
					break;
				else {
					student.setText(s13);
					break;
				}
			case "HTTPSampler.connect_timeout":
				if (s14.equals(""))
					break;
				else {
					student.setText(s14);
					break;
				}
			case "HTTPSampler.response_timeout":
				if (s15.equals(""))
					break;
				else {
					student.setText(s15);
					break;
				}
			case "HTTPSampler.protocol":
				if (s17.equals(""))
					break;
				else {
					student.setText(s17);
					break;
				}
			case "HTTPSampler.method":
				if (s18.equals(""))
					break;
				else {
					student.setText(s18);
					break;
				}
			case "HTTPSampler.contentEncoding":
				if (s19.equals(""))
					break;
				else {
					student.setText(s19);
					break;
				}
			case "HTTPSampler.path":
				if (s20.equals(""))
					break;
				else {
					student.setText(s20);
					break;
				}
			case "Assertion.test_field":
				student.setText(s26);
				if (s25.equals("self"))
					break;
				else {
					Element stu1 = student.getParent().addElement("stringProp");
					stu1.addAttribute("name", "HTTPSampler.implementation");
					stu1.addText(s25);
					break;
				}
			default:
				break;
			}

		}
		String xpath2 = "//elementProp[@name]";
		List<Element> list2 = document.selectNodes(xpath2);
		for (int j = 0; j < list2.size(); j++) {
			Element student1 = list2.get(j);
			switch (student1.attributeValue("name")) {
			case "HTTPsampler.Arguments":
				student1.remove(student1.selectSingleNode("collectionProp"));
				Element stu2 = student1.addElement("collectionProp");
				stu2.addAttribute("name", "Arguments.arguments");
				for (int i = 0; i < str_name.length; i++) {
					Element stu21 = stu2.addElement("elementProp");
					stu21.addAttribute("name", str_name[i]);
					stu21.addAttribute("elementType", "HTTPArgument");
					Element stu211 = stu21.addElement("boolProp");
					stu211.addAttribute("name", "HTTPArgument.always_encode");
					stu211.addText("false");
					Element stu212 = stu21.addElement("stringProp");
					stu212.addAttribute("name", "Argument.value");
					stu212.addText(str_value[i]);
					Element stu213 = stu21.addElement("stringProp");
					stu213.addAttribute("name", "Argument.metadata");
					stu213.addText("=");
					Element stu214 = stu21.addElement("boolProp");
					stu214.addAttribute("name", "HTTPArgument.use_equals");
					stu214.addText("true");
					Element stu215 = stu21.addElement("stringProp");
					stu215.addAttribute("name", "Argument.name");
					stu215.addText(str_name[i]);
				}
				break;

			default:
				break;
			}

		}

		String xpath3 = "//boolProp[@name]";
		List<Element> list3 = document.selectNodes(xpath3);
		for (int k = 0; k < list3.size(); k++) {
			Element student2 = list3.get(k);
			switch (student2.attributeValue("name")) {
			case "LoopController.continue_forever":
				student2.setText("false");
				for (int l = 0; l < s30.length; l++) {
					if (s30[l].equals("che1"))
						student2.setText("true");
				}
				break;
			case "HTTPSampler.auto_redirects":
				student2.setText("false");
				for (int l = 0; l < s30.length; l++) {
					if (s30[l].equals("che2"))
						student2.setText("true");

				}
				break;
			case "HTTPSampler.follow_redirects":
				student2.setText("false");
				for (int l = 0; l < s30.length; l++) {
					if (s30[l].equals("che3"))
						student2.setText("true");

				}
				break;
			case "HTTPSampler.use_keepalive":
				student2.setText("false");
				for (int l = 0; l < s30.length; l++) {
					if (s30[l].equals("che4"))
						student2.setText("true");

				}
				break;
			case "HTTPSampler.DO_MULTIPART_POST":
				student2.setText("false");
				for (int l = 0; l < s30.length; l++) {
					if (s30[l].equals("che5"))
						student2.setText("true");

				}
				break;
			case "HTTPSampler.BROWSER_COMPATIBLE_MULTIPART":
				student2.setText("false");
				for (int l = 0; l < s30.length; l++) {
					if (s30[l].equals("che6"))
						student2.setText("true");

				}
				break;
			default:
				break;
			}

		}

		String xpath4 = "//ResponseAssertion[@testname]";
		List<Element> list4 = document.selectNodes(xpath4);
		for (int n = 0; n < list4.size(); n++) {
			Element student4 = list4.get(n);
			if (student4.attributeValue("testname")
					.equals("Response Assertion")) {
				Element stu4 = student4.getParent().addElement("HeaderManager");
				stu4.addAttribute("guiclass", "HeaderPanel");
				stu4.addAttribute("testclass", "HeaderManager");
				stu4.addAttribute("testname", "HTTP Header Manager");
				stu4.addAttribute("enabled", "true");
				Element stu41 = stu4.addElement("collectionProp");
				stu41.addAttribute("name", "HeaderManager.headers");
				Element stu411 = stu41.addElement("elementProp");
				stu411.addAttribute("name", "");
				stu411.addAttribute("elementType", "Header");
				Element stu4111 = stu411.addElement("stringProp");
				stu4111.addAttribute("name", "Header.name");
				stu4111.addText("name");
				Element stu4112 = stu411.addElement("stringProp");
				stu4112.addAttribute("name", "Header.value");
				stu4112.addText("Content-Type");
				Element stu412 = stu41.addElement("elementProp");
				stu412.addAttribute("name", "");
				stu412.addAttribute("elementType", "Header");
				Element stu4121 = stu412.addElement("stringProp");
				stu4121.addAttribute("name", "Header.name");
				stu4121.addText("value");
				Element stu4122 = stu412.addElement("stringProp");
				stu4122.addAttribute("name", "Header.value");
				stu4122.addText("application/json;charset=utf-8");
				Element stu5 = student4.getParent().addElement("hashTree");
			}
		}

		Writer osWrite = new OutputStreamWriter(new FileOutputStream(s6),
				"UTF-8");// ���������
		OutputFormat format = OutputFormat.createPrettyPrint(); // ��ȡ�����ָ����ʽ
		format.setEncoding("UTF-8");// ���ñ��� ��ȷ��������xmlΪUTF-8��ʽ
		XMLWriter writer = new XMLWriter(osWrite, format);// XMLWriter
															// ָ������ļ��Լ���ʽ
		writer.write(document);// ��documentд��xmlFileָ�����ļ�(����Ϊ���������ļ������´������ļ�)
		writer.flush();
		writer.close();
		return "��Ĳ����ѳɹ��ύ";
	}

	public String testchange3(String s1, String s2, String s6, String s10,
			String s11, String s12, String s13, String s14, String s15,
			String s16, String s17, String s18, String s19, String s20,
			String s21, String s22, String s23, String s24, String s25,
			String s26, String s27, String s28, String s29, String[] s30)
			throws Exception {
		SAXReader sax = new SAXReader();
		String path = getServletContext().getRealPath("/");
		File xmlFile = new File(path + "WEB-INF/templateXML/newfortest.xml");
		Document document = sax.read(xmlFile);
		Element root = document.getRootElement();
		String xpath1 = "//stringProp[@name]";
		List<Element> list1 = document.selectNodes(xpath1);
		// return list.size();
		// ��ĳ���ڵ����һ���ֵܽڵ�
		/*
		 * Element stu=student.getParent().addElement("stringProp");
		 * stu.addAttribute("number", "ITCAST_0003"); stu.addText("java");
		 */
		// 2.�����
		for (int i = 0; i < list1.size(); i++) {
			// ����Ԫ�ض���
			Element student = list1.get(i);
			switch (student.attributeValue("name")) {
			case "ThreadGroup.num_threads":
				student.setText(s1);
				break;
			case "ThreadGroup.ramp_time":
				student.setText(s2);
				break;
			case "LoopController.loops":
				student.setText(s10);
				break;
			case "HTTPSampler.domain":
				student.setText(s11);
				if (s16.isEmpty() == false) {
					Element stu = student.getParent().addElement("stringProp");
					stu.addAttribute("name", "HTTPSampler.implementation");
					stu.addText(s16);
				}
				break;
			case "ThreadGroup.on_sample_error":
				student.setText(s12);
				break;
			case "HTTPSampler.port":
				if (s13.equals(""))
					break;
				else {
					student.setText(s13);
					break;
				}
			case "HTTPSampler.connect_timeout":
				if (s14.equals(""))
					break;
				else {
					student.setText(s14);
					break;
				}
			case "HTTPSampler.response_timeout":
				if (s15.equals(""))
					break;
				else {
					student.setText(s15);
					break;
				}
			case "HTTPSampler.protocol":
				if (s17.equals(""))
					break;
				else {
					student.setText(s17);
					break;
				}
			case "HTTPSampler.method":
				if (s18.equals(""))
					break;
				else {
					student.setText(s18);
					break;
				}
			case "HTTPSampler.contentEncoding":
				if (s19.equals(""))
					break;
				else {
					student.setText(s19);
					break;
				}
			case "HTTPSampler.path":
				if (s20.equals(""))
					break;
				else {
					student.setText(s20);
					break;
				}
			case "Assertion.test_field":
				student.setText(s26);
				if (s25.equals("self"))
					break;
				else {
					Element stu1 = student.getParent().addElement("stringProp");
					stu1.addAttribute("name", "HTTPSampler.implementation");
					stu1.addText(s25);
					break;
				}

			default:
				break;
			}

		}

		String xpath3 = "//boolProp[@name]";
		List<Element> list3 = document.selectNodes(xpath3);
		for (int k = 0; k < list3.size(); k++) {
			Element student2 = list3.get(k);
			switch (student2.attributeValue("name")) {
			case "LoopController.continue_forever":
				student2.setText("false");
				for (int l = 0; l < s30.length; l++) {
					if (s30[l].equals("che1"))
						student2.setText("true");
				}
				break;
			case "HTTPSampler.auto_redirects":
				student2.setText("false");
				for (int l = 0; l < s30.length; l++) {
					if (s30[l].equals("che2"))
						student2.setText("true");

				}
				break;
			case "HTTPSampler.follow_redirects":
				student2.setText("false");
				for (int l = 0; l < s30.length; l++) {
					if (s30[l].equals("che3"))
						student2.setText("true");

				}
				break;
			case "HTTPSampler.use_keepalive":
				student2.setText("false");
				for (int l = 0; l < s30.length; l++) {
					if (s30[l].equals("che4"))
						student2.setText("true");

				}
				break;
			case "HTTPSampler.DO_MULTIPART_POST":
				student2.setText("false");
				for (int l = 0; l < s30.length; l++) {
					if (s30[l].equals("che5"))
						student2.setText("true");

				}
				break;
			case "HTTPSampler.BROWSER_COMPATIBLE_MULTIPART":
				student2.setText("false");
				for (int l = 0; l < s30.length; l++) {
					if (s30[l].equals("che6"))
						student2.setText("true");

				}
				break;
			default:
				break;
			}

		}

		Writer osWrite = new OutputStreamWriter(new FileOutputStream(s6),
				"UTF-8");// ���������
		OutputFormat format = OutputFormat.createPrettyPrint(); // ��ȡ�����ָ����ʽ
		format.setEncoding("UTF-8");// ���ñ��� ��ȷ��������xmlΪUTF-8��ʽ
		XMLWriter writer = new XMLWriter(osWrite, format);// XMLWriter
															// ָ������ļ��Լ���ʽ
		writer.write(document);// ��documentд��xmlFileָ�����ļ�(����Ϊ���������ļ������´������ļ�)
		writer.flush();
		writer.close();
		return "��Ĳ����ѳɹ��ύ";
	}

}
