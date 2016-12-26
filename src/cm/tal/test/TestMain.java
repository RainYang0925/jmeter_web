package cm.tal.test;

import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class TestMain {
	public static void main(String[] args) throws Exception {
		// String[] cmd = { "./cmd.sh", "-c", "$PATH" };
		Process pcs = Runtime.getRuntime().exec("./cmd.sh '$PATH'");
		// 若不加这下面的代码也调不成功shell脚本
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
		int extValue = pcs.waitFor();// 返回码 0 表示正常退出 1表示异常退出
	}
}
