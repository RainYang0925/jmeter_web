package cm.tal.test;

import java.io.InputStreamReader;
import java.io.LineNumberReader;

import org.junit.Test;

import com.tal.utils.PropertiesReadUtils;

public class TestMain {
	public static void main(String[] args) throws Exception {
		String[] cmd = { "./cmd.sh", "-c", "$PATH" };
		Process pcs = Runtime.getRuntime().exec("./cmd.sh '$PATH'");
		InputStreamReader ir = new InputStreamReader(pcs.getInputStream());
		LineNumberReader input = new LineNumberReader(ir);
		String line = null;
		while ((line = input.readLine()) != null) {
			System.out.println(line);
		}
		if (null != input) {
			input.close();
		}
		if (null != ir) {
			ir.close();
		}
		int extValue = pcs.waitFor();//
		System.out.println("上传文件路径："
				+ PropertiesReadUtils.getString("uploadFilePath"));
	}

	@Test
	public void test01() {
		String str = "{" + "\"param\":" + "]" + "}";
		StringBuilder sb = new StringBuilder(str);
		sb.insert(9, "[");
		System.out.println("json字符串：" + sb.toString());
	}
}
