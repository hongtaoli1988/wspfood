package com.mainone.util;

import java.util.Properties;
import java.util.ResourceBundle;
/**
 * @desc ��ȡ.properties�ļ� ��-ֵ��
 */
public class PropertyFileUtil {
	public static String getProperty(String key) {
		Properties props = new Properties();
		String keyValue = null;
		try {
			
			ResourceBundle messages = ResourceBundle.getBundle("db");
			keyValue = messages.getString(key);//ȡ������Ҫ��ֵ
		} catch (Exception e) {
			e.printStackTrace();
		}
		return keyValue;
	}
	

	
}
