package com.mainone.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author：zzf
 * 创建时间：Feb 8, 2012 3:35:14 PM
 * 类说明：读取.properties文件 键-值对
 */
public class LoadConfig {
	
	public static ArrayList<String> loadConfig() {
		
		ArrayList<String> list = new ArrayList<String>();
		
			System.out.println("load.....");
			
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line = null;
			try {
				while ((line = reader.readLine()) != null) {
					if(!list.contains(line)){
						list.add(line);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					reader.close();
					is.close();
				} catch (IOException e) {
				}
			}
		return list;
	}
}
