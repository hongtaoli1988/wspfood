package com.mainone.util;

import java.util.Properties;
import java.util.ResourceBundle;
/**
 * @desc 读取.properties文件 键-值对
 */
public class PropertyFileUtil {
	public static String getProperty(String key) {
		Properties props = new Properties();
		String keyValue = null;
		try {
			
			ResourceBundle messages = ResourceBundle.getBundle("db");
			keyValue = messages.getString(key);//取得所需要的值
		} catch (Exception e) {
			e.printStackTrace();
		}
		return keyValue;
	}
	

	
}
