package com.mainone.util;

import java.text.DateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: wangtao
 * Date: 2007-11-8
 * Time: 11:48:46
 * To change this template use File | Settings | File Templates.
 */
public class DBRecord extends Properties{
   public static void main(String[] args){
      Date d = new Date(System.currentTimeMillis());
      DBRecord r = new DBRecord();
      r.setField("date", d);

      //System.out.println(com.hysh.util.TimeTools.getDateInfo());
      System.out.println(r.getDate("date"));
   }

   public DBRecord(){
   }


   /**
    * 设置一个字段的值(类型不限)，如果field_vale为空，则删除field_name字段
    *
    * @param field_name  字段名
    * @param field_value 字段值
    */
   public void setField(String field_name, Object field_value){
      if(field_value != null){
         put(field_name, field_value);
      }
      else{
         remove(field_name);
      }
   }

   /**
    * 设置一个字段的值(类型为int类型)，如果field_vale为空，则删除field_name字段
    *
    * @param field_name  字段名
    * @param field_value 字段值
    */
   public void setField(String field_name, int field_value){
      this.setField(field_name, new Integer(field_value));
   }

   /**
    * 设置一个字段的值(类型为float类型)，如果field_vale为空，则删除field_name字段
    *
    * @param field_name  字段名
    * @param field_value 字段值
    */
   public void setField(String field_name, float field_value){
      this.setField(field_name, new Float(field_value));
   }

   /**
    * 取一个字段值
    *
    * @param field_name 字段名
    * @return 字段值
    */
   public Object getField(String field_name){

      return get(field_name);
   }


   /**
    * 取一个字段值
    * 注：如果该字段值为Date类型的，
    * 则在getDate方法后,
    * 将转换为'year-month-day'格式的字符串 ;
    *
    * @param field_name 字段名
    * @return 字段值
    */
   public String getDate(String field_name){
      String value = "";
      Object obj = (field_name);
      // System.out.println("dddddddddddddd") ;
      if(obj instanceof java.sql.Date){
         java.sql.Date date = (java.sql.Date) obj;

         DateFormat df = DateFormat.getDateInstance();

         value = df.format(date);
      }
      else if(obj instanceof java.util.Date){
         java.util.Date date = (java.util.Date) obj;

         DateFormat df = DateFormat.getDateInstance();

         value = df.format(date);
      }
      return value;
   }

   /**
    * 取一个字段值(int 类型)
    *
    * @param field_name 字段名
    * @return 字段值
    */
   public int getInt(String field_name){
    

      Object ob = getField(field_name);

      int value = Integer.parseInt(getField(field_name).toString());             //((Integer)getField(field_name)).intValue();


      return value;
   }

   /**
    * 取一个字段值(float 类型)
    *
    * @param field_name 字段名
    * @return 字段值
    */
   public float getFloat(String field_name){
      float value =
              ((Float) getField(field_name)).floatValue();

      return value;
   }

   /**
    * 取一个字段值(String 类型)
    *
    * @param field_name 字段名
    * @return 字段值
    */
   public String getString(String field_name){
      String value =
              (String) getField(field_name);

      return value;
   }

   //2005-12-1添加
   /**
    * 取一个字段值(float 类型)
    *
    * @param field_name 字段名
    * @return 字段值
    */
   public float getfloat(String field_name){
      Object ob = getField(field_name);

      float value = Float.parseFloat(getField(field_name).toString());

      return value;
   }
}
