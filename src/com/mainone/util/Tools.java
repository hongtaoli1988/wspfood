package com.mainone.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Tools{
	public static void main(String[] args){
		Tools.getDay("2013", "3");
	}
	/**
	 * 取num四舍五入到小数点后两位
	 * @param num
	 * @return
	 */
	public static String getNum(float num){
		BigDecimal b  =  new BigDecimal(num);  
	    float f1 = b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
		
	    return ""+f1;

	}
	/**
	 * 去掉num小数点后的位数
	 * @param num
	 * @return
	 */
	public static String getInt(float num){
	    DecimalFormat   fnum   =   new   DecimalFormat("##0");  
	    String f1=fnum.format(num);      
	    return f1; 

	}
	public static String getDay(String year,String month){
		Calendar   cal   =   Calendar.getInstance();   
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		  cal.set(Calendar.YEAR,Integer.parseInt(year));   
		  cal.set(Calendar.MONTH,Integer.parseInt(month)-1); 
		  System.out.println("cal.getTime()=="+df.format(cal.getTime()));
		  df.format(cal.getTime());
		  int   maxDate   =   cal.getActualMaximum(Calendar.DATE);
		  System.out.println("maxDate=="+maxDate);
		  String s="";
		  s+="<option selected value=''>日</option>";
		  for(int i=1;i<=maxDate;i++){
			  if(i==1){
				  s=""+i;
			  }else{
				  s=s+"-"+i;
			  }
			  s+="<option value=" +i + " selected>" + i + "</option>";
		  }
		  System.out.println("s=="+s);
		return s;
		  
	 }
  }
  