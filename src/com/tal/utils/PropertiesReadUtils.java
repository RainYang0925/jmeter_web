package com.tal.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件的工具类
 * 
 * @author wuhaifei
 * @d2016�?1�?8�?
 */
public class PropertiesReadUtils {
	/*
	 * 私有化构造方法
	 */
	private PropertiesReadUtils() {

	}

	// 私有化的内部类
	private static class SingletonInstance {
		static PropertiesReadUtils instance = new PropertiesReadUtils();
	}

	// 获取PropertiesReadUtils的单例对
	public static PropertiesReadUtils getInstance() {
		return SingletonInstance.instance;
	}

	/**
	 * 获取参数
	 * 
	 * @param param
	 *            参数的key
	 * @return 参数的value
	 */
	public static String getString(String param) {
		return getInstance().getPropertiesString("/recordConf.properties",
				param);
	}

	/**
	 * 获取Peoperties文件的参数的
	 * 
	 * @param path
	 *            路径
	 * @param param
	 *            参数的key
	 * @return 参数的value
	 */
	public String getPropertiesString(String path, String param) {
		Properties prop = new Properties();
		InputStream in = this.getClass().getResourceAsStream(path);
		try {
			prop.load(in);
		} catch (IOException e) {
			throw new RuntimeException();
		}
		return prop.getProperty(param);
	}
}
