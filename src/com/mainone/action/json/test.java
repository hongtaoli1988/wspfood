package com.mainone.action.json;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.apache.struts2.util.TokenHelper;
import org.junit.Test;

import com.mainone.util.JsonHttpUtilsSend;




public class test {
	//测试是否登陆方法
	@Test
	public void test1(){
//		    String Parameter = "userName=bjwxs' or 1 = 1  --'&passWord=0000";
		    String Parameter1 = "userName=lihongtao&passWord=lihongtao";
		    String Parameter2 = "userName=111&passWord=323";
	  	    String path = "http://120.25.156.253:8080/wspfood/loginCommonAction_login.html";
	  	    String sendHttpJsonData = JsonHttpUtilsSend.sendHttpJsonData(path, Parameter1);
	  	    System.out.println(sendHttpJsonData);
	  	   //[{"auth_key":"f4d9158be42249ffb1a4e159b5bf4c46","flagLogin":"true","userId":"3","message":"0","role":"0"}]

		
	}
	//产品推荐
	@Test
	public void test2(){
//		    String Parameter = "userName=bjwxs' or 1 = 1  --'&passWord=0000";
		    String Parameter1 = "uid=2&auth_key=f4d9158be42249ffb1a4e159b5bf4c46&currentPage=1&pageSize=10";
	  	    String path = "http://120.25.156.253:8080/wspfood/loginCommonAction_productRecommend.html";
	  	    String sendHttpJsonData = JsonHttpUtilsSend.sendHttpJsonData(path, Parameter1);
	  	    System.out.println(sendHttpJsonData);
		
	}
	
	
	
	 
//	public static void main(String[] args) {  
//  	    String Parameter = "userName=bjwxs&passWord=0000";
//  	    String path = "http://localhost:90/wapsfa/jsonCommonAction!search.html";
//  	    JsonHttpUtilsSend.sendHttpJsonData(, jsonData)
//  	    
//  	    
////  	    
////	   //String Parameter = "uid=7917372053584d5ca9c03f717ac7492e";
////
////	    
////
////		URLConnection conn = null;
////
////			
////			//System.out.println(Parameter);
////			//System.out.println("循环第"+i+"次");
////	        try{
////	            URL url = new URL("http://localhost:8080/wspfood/loginCommonAction_login.html");
////	            //URL url = new URL("http://localhost:90/wapsfa/jsonCommonAction!search.html");
////	            do{
////	             conn = url.openConnection();
////	            }while(conn == null);
////	
////	            conn.setDoInput(true);
////	            conn.setDoOutput(true);
////	             //Set mime type for POST
////	            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
////	            // Now Write Data
////	            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());     
////	            dos.writeBytes(Parameter);  
////	            dos.flush();
////	            dos.close();
////	             //Now Read Data
////	            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
////	            String t;
////	            while ((t = in.readLine()) != null) 
////	            {
////	             
////	            	 System.out.println(t);
////	              
////	            }
////	            
////	            in.close();
////	            //System.out.println("休眠一分钟");
////	        }
////	        catch(Exception e){
////	            e.printStackTrace();
////	        }  
//		
//
//	}
	
	
	

}
