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
    * ����һ���ֶε�ֵ(���Ͳ���)�����field_valeΪ�գ���ɾ��field_name�ֶ�
    *
    * @param field_name  �ֶ���
    * @param field_value �ֶ�ֵ
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
    * ����һ���ֶε�ֵ(����Ϊint����)�����field_valeΪ�գ���ɾ��field_name�ֶ�
    *
    * @param field_name  �ֶ���
    * @param field_value �ֶ�ֵ
    */
   public void setField(String field_name, int field_value){
      this.setField(field_name, new Integer(field_value));
   }

   /**
    * ����һ���ֶε�ֵ(����Ϊfloat����)�����field_valeΪ�գ���ɾ��field_name�ֶ�
    *
    * @param field_name  �ֶ���
    * @param field_value �ֶ�ֵ
    */
   public void setField(String field_name, float field_value){
      this.setField(field_name, new Float(field_value));
   }

   /**
    * ȡһ���ֶ�ֵ
    *
    * @param field_name �ֶ���
    * @return �ֶ�ֵ
    */
   public Object getField(String field_name){

      return get(field_name);
   }


   /**
    * ȡһ���ֶ�ֵ
    * ע��������ֶ�ֵΪDate���͵ģ�
    * ����getDate������,
    * ��ת��Ϊ'year-month-day'��ʽ���ַ��� ;
    *
    * @param field_name �ֶ���
    * @return �ֶ�ֵ
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
    * ȡһ���ֶ�ֵ(int ����)
    *
    * @param field_name �ֶ���
    * @return �ֶ�ֵ
    */
   public int getInt(String field_name){
    

      Object ob = getField(field_name);

      int value = Integer.parseInt(getField(field_name).toString());             //((Integer)getField(field_name)).intValue();


      return value;
   }

   /**
    * ȡһ���ֶ�ֵ(float ����)
    *
    * @param field_name �ֶ���
    * @return �ֶ�ֵ
    */
   public float getFloat(String field_name){
      float value =
              ((Float) getField(field_name)).floatValue();

      return value;
   }

   /**
    * ȡһ���ֶ�ֵ(String ����)
    *
    * @param field_name �ֶ���
    * @return �ֶ�ֵ
    */
   public String getString(String field_name){
      String value =
              (String) getField(field_name);

      return value;
   }

   //2005-12-1���
   /**
    * ȡһ���ֶ�ֵ(float ����)
    *
    * @param field_name �ֶ���
    * @return �ֶ�ֵ
    */
   public float getfloat(String field_name){
      Object ob = getField(field_name);

      float value = Float.parseFloat(getField(field_name).toString());

      return value;
   }
}
