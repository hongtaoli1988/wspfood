package com.mainone.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonHttpUtilsSend {
	public static String sendHttpJsonData(String urlPath, String jsonData) {
		StringBuilder info = new StringBuilder();
		try {
			// 创建连接
			URL url = new URL(urlPath);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			//connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.connect();
			// POST请求
			DataOutputStream out = new DataOutputStream(connection
					.getOutputStream());
			System.out.println(jsonData);
			out.writeBytes(jsonData);
			out.flush();
			out.close();
			// 读取响应
			String t = "";
			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "UTF-8"));
			t = in.readLine();
			while (t != null) {
				info.append(t);
				t = in.readLine();
			}
			//System.out.println(info.toString());
			in.close();
			// 断开连接
			connection.disconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info.toString();
	}

}
