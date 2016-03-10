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




public class test {
	 
	public static void main(String[] args) {  
  	    String Parameter = "userName=bjwxs&passWord=0000";
	   //String Parameter = "uid=7917372053584d5ca9c03f717ac7492e";

	    

		URLConnection conn = null;

			
			//System.out.println(Parameter);
			//System.out.println("循环第"+i+"次");
	        try{
	            URL url = new URL("http://localhost:90/wapsfa/loginCommonAction!login.html");
	            //URL url = new URL("http://localhost:90/wapsfa/jsonCommonAction!search.html");
	            do{
	             conn = url.openConnection();
	            }while(conn == null);
	
	            conn.setDoInput(true);
	            conn.setDoOutput(true);
	             //Set mime type for POST
	            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	            // Now Write Data
	            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());     
	            dos.writeBytes(Parameter);  
	            dos.flush();
	            dos.close();
	             //Now Read Data
	            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String t;
	            while ((t = in.readLine()) != null) 
	            {
	             
	            	 System.out.println(t);
	              
	            }
	            
	            in.close();
	            //System.out.println("休眠一分钟");
	        }
	        catch(Exception e){
	            e.printStackTrace();
	        }  
		

	}
	
	
	

}
