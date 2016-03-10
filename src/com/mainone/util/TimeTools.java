package com.mainone.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

//import com.hysh.util.Debuger;

/**
 * <p>Title: TimeTools.java</p>
 * <p>Description: �ṩ���ж����ڣ�ʱ������ķ���</p>
 * Copyright:    Copyright (c) 2004-07-17
 * Company: mainOne
 *
 * @author ������
 * @version 1.0
 */
public class TimeTools{
   public static void main(String[] args){
      try{
         /*String[] result = TimeTools.getSurplus("20070727000000");
         System.out.println(result[0]);
         System.out.println(result[1]);
         System.out.println(result[2]);
         System.out.println(result[3]);*/
    	  //System.out.println(diffDate("20110408","20110321"));
    	  /*System.out.println(cancelDelimiter("2011-03-21"));
    	  Date date2 = (Date) TimeTools.stringToDate3("20110321");
    	  
    	  System.out.println(date2);
    	  System.out.println(TimeTools.formatDate(date2));*/
    	  //System.out.println("2011-05-14".replaceAll("-", ""));
    	 // getTimeNameString("2013-1-8");
    	  //System.out.println(DateDiff("2011-05-14".replaceAll("-", ""),formatDate1(new Date())));
    	  //System.out.println("day=="+getFirstDayInMonth());
    	  
    	  System.out.println("����������===="+dateOfMonth(2016,2));
      }
      catch(Exception e){
         e.printStackTrace();
         System.exit(0);
      }
   }


   private static GregorianCalendar c = new GregorianCalendar();
   private static String year;
   private static String month;
   private static String day;
   private static String hour;
   /**
    * ��ʼ����ǰʱ�������(������,��,��,Сʱ)
    **/
   static{
      init();
   }

   public TimeTools(){
   }

   /**
    * ��õ�ǰʱ�������(������,��,��,Сʱ)
    *
    * @return String ���ڣ�xxxx.xx.xx
    */
   public static String getTime(){
      String tmp = "";

      tmp =
              year + "-" + month + "-" + day + " " + hour;

      return tmp;
   }

   /**
    * ��õ�ǰʱ�������(������,��,��)
    *
    * @return String ���ڣ�xxxx.xx.xx
    */
   public static String getDate(){
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	   String date = format.format(new Date());
	   return date;
   }


   /**
    * ��õ�ǰʱ�������(������,��,��,Сʱ)
    *
    * @param c GregorianCalendar ����
    * @return String ���ڣ�xxxx.xx.xx
    */
   public static String getTime(GregorianCalendar c){
      String tmp = "";

      tmp =
              year + "-" + month + "-" + day + " " + hour;

      return tmp;
   }

   /**
    * ��õ�ǰʱ�������·�
    *
    * @return String ���ص�ǰʱ�������·�
    */
   public static String getYM(){
      String tmp = "";

      tmp = year + "-" + month;

      return tmp;
   }

   /**
    * ��õ�ǰʱ������
    *
    * @return int ���ص�ǰʱ������
    */
   public static int getYear(){
      int year = c.get(Calendar.YEAR);

      return year;
   }

   /**
    * ��õ�ǰʱ����·�
    *
    * @return int ���ص�ǰʱ����·�
    */
   public static int getMonth(){
      int month = c.get(Calendar.MONTH) + 1;

      return month;
   }

   /**
    * ��õ�ǰʱ�����
    *
    * @return int ���ص�ǰʱ�����
    */
   public static int getDay(){

      int day = c.get(Calendar.DATE);

      return day;
   }

   //��õ�ǰʱ���Сʱ
   public static int getHour(){
      int hour = c.get(Calendar.HOUR);

      return hour;
   }

   //��õ�ǰʱ��ķ���
   public static int getMinute(){
      int minute = c.get(Calendar.MINUTE);

      return minute;

   }

   ////��õ�ǰʱ�����
   public static int getSecond(){
      int second = c.get(Calendar.SECOND);

      return second;
   }

   /**
    * ��õ�ǰ�ܵ����յ�����
    *
    * @return String ���ڣ�xxxx.xx.xx
    */
   public static String getWeekday(int index){
      int n = c.get(Calendar.DAY_OF_WEEK) - 1;

      c.add(Calendar.DAY_OF_YEAR, -1 * n);
      c.add(Calendar.DAY_OF_YEAR, 7 * index);

      String result =
              year + "-" + month + "-" + day;

      return result;
   }

   /**
    * ��õ�ǰ���ڵ�������Ϣ
    *
    * @return String ���ڣ�xxxx.xx.xx
    */
   public static String getDateInfo(){
      String date =
              year + "��" + month + "��" + day + "��";

      return date;
   }

   private static void init(){
      year =
              String.valueOf(c.get(Calendar.YEAR)).trim();

      month =
              String.valueOf(c.get(Calendar.MONTH) + 1).trim();

      day =
              String.valueOf(c.get(Calendar.DATE)).trim();

      hour =
              String.valueOf(c.get(Calendar.HOUR)).trim();

      if(hour.length() < 2){
         hour = "0" + hour;
      }
      if(day.length() < 2){
         day = "0" + day;
      }
      if(month.length() < 2){
         month = "0" + month;
      }
   }

   /**
    * �������������ת���ɺ��������
    *
    * @param year  ���
    * @param month �·�
    * @return String ����
    */
   public static String getTransMonth(int year, int month){
      //int iOver = month - 12 ;
      if(month < 0){
         year = year - 1;
         month = 12 + month;
      }
      else if(month > 12){
         year = year + 1;
         month = month - 12;
      }

      String sMonth =
              String.valueOf(month).trim();

      if(sMonth.length() < 2){
         sMonth = "0" + sMonth;
      }
      String ym = year + "-" + sMonth;

      return ym;
   }

   /**
    * �������������ת���ɺ��������
    *
    * @param year  ���
    * @param month �·�
    * @return String ����
    */
   public static String getTransMonth(String year,
                                      String month,
                                      String day){
      //int iOver = month - 12 ;
      int iYear = getYear();
      int iMonth = getMonth();
      int iDay = getDay();

      if(year != null && year.trim().length() > 0){
         iYear = Integer.parseInt(year);
      }
      if(month != null && month.trim().length() > 0){
         iMonth = Integer.parseInt(month);
      }
      if(day != null && day.trim().length() > 0){
         iDay = Integer.parseInt(day);
      }

      if(iMonth < 0){
         iYear = iYear - 1;
         iMonth = 12 + iMonth;
      }
      else if(iMonth > 12){
         int iLen =
                 new Integer(iMonth / 12).intValue();

         iYear = iYear + iLen;
         iMonth = iMonth % 12;
      }

      String sMonth =
              String.valueOf(iMonth).trim();

      String sDay =
              String.valueOf(iDay).trim();

      if(sMonth.length() < 2){
         sMonth = "0" + sMonth;
      }
      if(sDay.length() < 2){
         sDay = "0" + sDay;
      }
      String ym = iYear + "-" + sMonth + "-" + sDay;

      return ym;
   }

   /**
    * ��ָ����ʽ������������
    *
    * @param cal    java.util.Calendar
    * @param format Ҫ���صĸ�ʽ �磺"yyyy-mm-dd hh:mm:ss"
    * @return String ��ָ����ʽ������������
    */
   static public final String getFormatDate(String format){
      String sDate = null;
      int len = format.length();
      int i = 0;
      int year = getYear();
      int month = getMonth();
      int day = getDay();
      int hour = getHour();
      int minute = getMinute();
      int second = getSecond();
      String sYear, sMonth, sDay, sHour, sMinute, sSecond;
      for(; i < len; i++){
         char ch = format.charAt(i);
         if(Character.isSpaceChar(ch)){
            break;
         }
      }
      if(i < len){
         if(i == 8){
            sDate = "" + year + (month < 10 ? "0" : "") + month + (day < 10 ? "0" : "") + day + " " + hour + (minute < 10 ? "" + format.charAt(11) + "0" : "" + format.charAt(11)) + minute + (second < 10 ? "" + format.charAt(14) + "0" : "" + format.charAt(14)) + second;
         }
         if(i == 10){
            sDate = "" + year + format.charAt(4) + (month < 10 ? "0" : "") + month + format.charAt(7) + (day < 10 ? "0" : "") + day + " " + hour + (minute < 10 ? "" + format.charAt(13) + "0" : "" + format.charAt(13)) + minute + (second < 10 ? "" + format.charAt(16) + "0" : "" + format.charAt(16)) + second;
         }
         if(i > 10){

         }
      }
      if(i == len){
         if(i == 8){
            sDate = "" + year + (month < 10 ? "0" : "") + month + (day < 10 ? "0" : "") + day;
         }
         if(i == 10){
            sDate = "" + year + format.charAt(4) + (month < 10 ? "0" : "") + month + format.charAt(7) + (day < 10 ? "0" : "") + day;
         }
         if(i > 10){

         }
      }
      return sDate;
   }

   /**
    * ����ʱ�����������
    * <p/>
    * dt,date1,date2��ʽ����Ϊ"yyyymmdd"
    */
   static public int diffDate(String date1, String date2){
      int arry1[] = new int[3];
      arry1 = datetoInt(date1);
      int arry2[] = new int[3];
      arry2 = datetoInt(date2);
      return (int) ((toLongTime(arry1[0], arry1[1], arry1[2]) - toLongTime(arry2[0], arry2[1], arry2[2])) / (24 * 60 * 60 * 1000));
   }

   /**
    * ��Ӻ��ʱ��.
    *
    * @param d     ���ӵ�ʱ��,yyyy-mm-dd
    * @param times Ҫ�ӵ�����,
    * @return flag,"y":��ʾ�꣬"d":��ʾ��
    */
   static public final String getAddDate(String d, int times, String flag){
      String sValue = null;
      if(flag.equals("y")){
         String y = d.substring(0, 4);
         int n = Integer.parseInt(y) + times;
         sValue = "" + n + d.substring(4);
      }
      if(flag.equals("d")){
         String one = cancelDelimiter(d);
         int[] arry = datetoInt(one);
         long l = toLongTime(arry[0], arry[1], arry[2]);
         long sum = l + (long) times * 24 * 60 * 60 * 1000;
         Calendar cal = getStaticCalendars(sum);
         String str = calToString(cal);
         sValue = addDelimiter(str, '-');
      }
      return sValue;
   }

   /**
    * ������ʱ��.
    *
    * @param d     ������ʱ��,yyyy-mm-dd
    * @param times Ҫ��������,
    * @return flag,"y":��ʾ�꣬"d":��ʾ��,"m":��ʾ��(timesС��12)
    */
   static public final String getSubtractDate(String d, int times, String flag){
      String sValue = null;
      if(flag.equals("y")){
         String y = d.substring(0, 4);
         int n = Integer.parseInt(y) - times;
         sValue = "" + n + d.substring(4);
      }
      if(flag.equals("m")){
         if(times > 12){
            return null;
         }
         String y = d.substring(0, 4);
         String m = d.substring(5, 7);
         int mm = Integer.parseInt(m) - times;
         int yy = Integer.parseInt(y);
         if(mm <= 0){
            m = Integer.toString(12 + mm);
            y = Integer.toString(yy - 1);
         }
         sValue = y + "-" + m + d.substring(7);
      }
      if(flag.equals("d")){
         String one = cancelDelimiter(d);
         int[] arry = datetoInt(one);
         long l = toLongTime(arry[0], arry[1], arry[2]);
         long sum = l - (long) times * 24 * 60 * 60 * 1000;
         Calendar cal = getStaticCalendars(sum);
         String str = calToString(cal);
         sValue = addDelimiter(str, '-');
      }
      return sValue;
   }

   public static java.util.Date toDate(int year, int month, int day){
      if(staticCal == null){
         staticCal = new GregorianCalendar();
      }
      staticCal.clear();
      staticCal.set(Calendar.YEAR, year);
      staticCal.set(Calendar.MONTH, month - 1);
      staticCal.set(Calendar.DAY_OF_MONTH, day);  // day-1??
      return staticCal.getTime();//.getTime();
   }

   public static long toLongTime(int year, int month, int day){
      return toDate(year, month, day).getTime();
   }

   /**
    * dt,date1,date2��ʽ����Ϊ"yyyymmdd"
    */
   static public int[] datetoInt(String dt){
      int arry[] = new int[3];
      int y = Integer.parseInt(dt.substring(0, 4));
      int m = Integer.parseInt(dt.charAt(4) == '0' ? "" + dt.charAt(5) : dt.substring(4, 6));
      int d = Integer.parseInt(dt.charAt(6) == '0' ? "" + dt.charAt(7) : dt.substring(6));
      arry[0] = y;
      arry[1] = m;
      arry[2] = d;
      return arry;
   }

   static public final String calToString(Calendar cal){
      int year = cal.get(Calendar.YEAR);//((java.sql.Timestamp)value).getYear() + 1900;
      int month = cal.get(Calendar.MONTH) + 1;//((java.sql.Timestamp)value).getMonth() + 1;
      int day = cal.get(Calendar.DAY_OF_MONTH);//((java.sql.Timestamp)value).getDate();
      return "" + year + (month < 10 ? "0" : "") + month + (day < 10 ? "0" : "") + day;
   }

   static public final String addDelimiter(String sdate, char delimiter){
      String hdate = sdate.substring(0, 4) + delimiter + sdate.substring(4, 6) + delimiter + sdate.substring(6);
      return hdate;
   }

   static public final String cancelDelimiter(String sdate){
      String hdate = sdate.substring(0, 4) + sdate.substring(5, 7) + sdate.substring(8);
      return hdate;
   }

   private static Calendar staticCal;

   public static Calendar getStaticCalendars(java.util.Date date){
      if(staticCal == null){
         staticCal = new GregorianCalendar();
      }
      if(date != null){
         staticCal.setTime(date);
      }
      return staticCal;
      //utcCal = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
      //defaultCenturyStart = staticCal.get(Calendar.YEAR) - 80;
   }

   /*
         public static Calendar getStaticCalendars(java.util.Date date)
    {
       if(staticCal==null) staticCal = new GregorianCalendar();
       if( date!=null )
          staticCal.setTime(date);
       return staticCal;
       //utcCal = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
       //defaultCenturyStart = staticCal.get(Calendar.YEAR) - 80;
    }*/
   public static Calendar getStaticCalendars(long time){
      Calendar cal = getStaticCalendars(null);
      if(cal != null){
         cal.setTime(new java.util.Date(time));
      }
      return cal;
   }

   /**
    * ��һ��ʱ��ת��Ϊ�ַ���.
    *
    * @param day    ��ת����ʱ��,���day=null��ʾ��ǰʱ��
    * @param format ת���ĸ�ʽ,����format="yyyy-mm-dd hh:mm:ss"
    * @return ת������ַ���
    */
   static public final String dateToString(java.util.Date date, String format){
      String sDate = null;
      int len = format.length();
      int i = 0;
      if(date == null){
         date = new java.util.Date();
      }
      Calendar cal = getStaticCalendars(date);//(java.sql.Timestamp)value;
      int year = cal.get(Calendar.YEAR);//((java.sql.Timestamp)value).getYear() + 1900;
      int month = cal.get(Calendar.MONTH) + 1;//((java.sql.Timestamp)value).getMonth() + 1;
      int day = cal.get(Calendar.DAY_OF_MONTH);//((java.sql.Timestamp)value).getDate();
      int hour = cal.get(Calendar.HOUR_OF_DAY);
      int minute = cal.get(Calendar.MINUTE);
      int second = cal.get(Calendar.SECOND);
      String sYear, sMonth, sDay, sHour, sMinute, sSecond;
      for(; i < len; i++){
         char ch = format.charAt(i);
         if(Character.isSpaceChar(ch)){
            break;
         }
      }
      if(i < len){
         if(i == 8){
            sDate = "" + year + (month < 10 ? "0" : "") + month + (day < 10 ? "0" : "") + day + " " + hour + (minute < 10 ? "" + format.charAt(11) + "0" : "" + format.charAt(11)) + minute + (second < 10 ? "" + format.charAt(14) + "0" : "" + format.charAt(14)) + second;
         }
         if(i == 10){
            sDate = "" + year + format.charAt(4) + (month < 10 ? "0" : "") + month + format.charAt(7) + (day < 10 ? "0" : "") + day + " " + hour + (minute < 10 ? "" + format.charAt(13) + "0" : "" + format.charAt(13)) + minute + (second < 10 ? "" + format.charAt(16) + "0" : "" + format.charAt(16)) + second;
         }
         if(i > 10){

         }
      }
      if(i == len){
         if(i == 8){
            sDate = "" + year + (month < 10 ? "0" : "") + month + (day < 10 ? "0" : "") + day;
         }
         if(i == 10){
            sDate = "" + year + format.charAt(4) + (month < 10 ? "0" : "") + month + format.charAt(7) + (day < 10 ? "0" : "") + day;
         }
         if(i > 10){

         }
      }
      return sDate;
   }

   /**
    * �������ڼ�������0:������1:����һ,��������
    *
    * @param String ���ڸ�ʽyyyy-mm-dd
    * @return int ��ʽת������ʱ������Ϊ1000
    */
   public static int dayofweek(String date){
      String[] a1 = date.trim().split(" ");
      String[] b1 = a1[0].trim().split("-");
      int year, month, day;
      try{
         year = Integer.parseInt(b1[0]);
         month = Integer.parseInt(b1[1]);
         day = Integer.parseInt(b1[2]);
      }
      catch(Exception e){
         //Debuger.println(e);
         return 1000;
      }

      int a = (14 - month) / 12;
      int y = year - a;
      int m = month + 12 * a - 2;
      return (day + y + y / 4 - y / 100 + y / 400 + (31 * m) / 12) % 7;
   }

   /**
    * �ַ���ת������������,��ʽΪ"yyyy-mm-dd"
    *
    * @param date
    * @return
    */
   public static Date stringToDate(String date){
      String[] a = date.split(" ");
      String[] d = a[0].split("-");
      int year = Integer.parseInt(d[0]);
      int month = Integer.parseInt(d[1]);
      int day = Integer.parseInt(d[2]);
      if(a.length == 2){
         String[] e = a[1].split(":");
         int hour = Integer.parseInt(e[0]);
         int minute = Integer.parseInt(e[1]);
         int second = Integer.parseInt(e[2]);
         return toDate(year, month, day, hour, minute, second);
      }
      return toDate(year, month, day);
   }

   public static Date stringToDate1(String date){
      try{
         SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
         return df.parse(date);
      }
      catch(ParseException e){
         e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
      }
      return null;
   }
   
   public static Date stringToDate3(String date){
	      try{
	         SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
	         return df.parse(date);
	      }
	      catch(ParseException e){
	         e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
	      }
	      return null;
	}
   
   public static Date toDate(int year, int month, int day, int hour, int minute, int second){
      if(staticCal == null){
         staticCal = new GregorianCalendar();
      }
      staticCal.clear();
      staticCal.set(Calendar.YEAR, year);
      staticCal.set(Calendar.MONTH, month - 1);
      staticCal.set(Calendar.DAY_OF_MONTH, day);
      staticCal.set(Calendar.HOUR_OF_DAY, hour);
      staticCal.set(Calendar.MINUTE, minute);
      staticCal.set(Calendar.SECOND, second);
      return staticCal.getTime();
   }

   /**
    * �����������������
    *
    * @param date1
    * @param date2
    * @return
    */
   public static int getYear(String date1, String date2){
      int year1 = Integer.parseInt(date1.substring(0, 4));
      int year2 = Integer.parseInt(date2.substring(0, 4));
      int year = year1 - year2;
      if(year > 0){
         return year;
      }
      return (-1) * year;
   }

   /**
    * ȡ��ָ���·ݵ����һ��
    *
    * @param strdate String
    * @return String
    */
   public static String getMonthEnd(String year, String month){
      java.util.Date date = TimeTools.stringToDate(year + "-" + month + "-01");
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      calendar.add(Calendar.MONTH, 1);
      calendar.add(Calendar.DAY_OF_YEAR, -1);
      return TimeTools.dateToString(calendar.getTime(), "yyyy-mm-dd");
   }

   /**
    * �ָ�ʱ���ַ�
    *
    * @param dt ��ʽΪ"yyyymmddhhmmss"
    * @return
    */
   public static int[] datetoInts(String dt){
      int arry[] = new int[6];
      int y = Integer.parseInt(dt.substring(0, 4));
      int m = Integer.parseInt(dt.substring(4, 6));
      int d = Integer.parseInt(dt.substring(6, 8));
      int h = Integer.parseInt(dt.substring(8, 10));
      int c = Integer.parseInt(dt.substring(10, 12));
      int s = Integer.parseInt(dt.substring(12, 14));
      arry[0] = y;
      arry[1] = m;
      arry[2] = d;
      arry[3] = h;
      arry[4] = c;
      arry[5] = s;
      return arry;
   }

   /**
    * ��ʱ���������ʱ����
    *
    * @param date
    * @return xx��xxСʱxx��xx��
    */
   public static String getye(String date){
      //SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
      int array[] = new int[6];
      array = datetoInts(date);
      Date BirthDay = new Date(array[0] - 1900, array[1] - 1, array[2], array[3], array[4], array[5]);//�ĳ���ļ�ʱ����
      Date today = new Date();
      long timeold = (BirthDay.getTime() - today.getTime());
      int ms = (int) (timeold % 1000);
      timeold /= 1000;
      int sc = (int) (timeold % 60);
      timeold /= 60;
      int mn = (int) (timeold % 60);
      timeold /= 60;
      int hrs = (int) (timeold % 24);
      long dy = timeold / 24;
      return dy + "��" + hrs + "Сʱ" + mn + "��" + sc + "��";
   }

   public static Date getdate(String date){
      int array[] = new int[6];
      array = datetoInts(date);
      Date BirthDay = new Date(array[0] - 1900, array[1] - 1, array[2], array[3], array[4], array[5]);//�ĳ���ļ�ʱ����
      return BirthDay;
   }

   /**
    * ����ʣ��ʱ��
    *
    * @param date ��ʽ:yyyymmddhhmmss
    * @return xx��xxСʱxx��xx��
    */
   public static String[] getSurplus(String date){
      String[] t = new String[4];
      //SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
      int array[] = new int[6];
      array = datetoInts(date);
      Date BirthDay = new Date(array[0] - 1900, array[1] - 1, array[2], array[3], array[4], array[5]);//�ĳ���ļ�ʱ����
      Date today = new Date();
      long timeold = (BirthDay.getTime() - today.getTime());
      int ms = (int) (timeold % 1000);
      timeold /= 1000;
      int sc = (int) (timeold % 60);
      timeold /= 60;
      int mn = (int) (timeold % 60);
      timeold /= 60;
      int hrs = (int) (timeold % 24);
      long dy = timeold / 24;
      t[0] = String.valueOf(sc);
      t[1] = String.valueOf(mn);
      t[2] = String.valueOf(hrs);
      t[3] = String.valueOf(dy);
      return t;
   }

   /**
    * ʱ����Ӻ������
    *
    * @param dates
    * @param d
    * @return
    */
   public static Date getDate(Date dates, int d){
      String date = TimeTools.dateToString(dates, "yyyy-mm-dd");
      String time = TimeTools.dateToString(dates, "yyyymmdd hh:mm:ss").substring(9);
      String datetime = TimeTools.getAddDate(date, d, "d").concat(" " + time);
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      try{
         return format.parse(datetime);
      }
      catch(ParseException e){
         // TODO �Զ����� catch ��
         e.printStackTrace();
         return null;
      }

   }

   /**
    * ʱ������������
    *
    * @param dates
    * @param d
    * @return
    */
   public static Date getSubtractDate(Date dates, int d){
      String date = TimeTools.dateToString(dates, "yyyy-mm-dd");
      String time = TimeTools.dateToString(dates, "yyyymmdd hh:mm:ss").substring(9);
      String datetime = TimeTools.getSubtractDate(date, d, "d").concat(" " + time);
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      try{
         return format.parse(datetime);
      }
      catch(ParseException e){
         // TODO �Զ����� catch ��
         e.printStackTrace();
         return null;
      }

   }

   /**
    * ת��ʱ���ʽ
    *
    * @param time
    * @return
    */
   public static String getTimeString(String time){
      //1���˿ո�
      String[] a = time.split(" ");
      //2��ǰ�벿�ֹ���-
      String[] b = a[0].split("-");
      //3�Ѻ�벿�ֹ��ˣ�
      String[] c = a[1].split(":");
      String date = b[0] + b[1] + b[2] + c[0] + c[1] + c[2];
      System.out.println("cccccccccccccc" + date);
      return date;

   }

   //	��ʽ������
   public static String formatDate(Date date){
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
      return format.format(date);
   }
   public static String formatDate1(Date date){
	      SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	      return format.format(date);
   }
   
   
   public static String format_Date(Date date){
	      SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
	      return format.format(date);
	   }
   public static long DateDiff(String sDate1, String sDate2){
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
      try{
         Date d0 = format.parse(sDate1);
         Date d1 = format.parse(sDate2);        
         long time0 = d0.getTime();
         long time1 = d1.getTime();
         return (time1 - time0) / (1000 * 60 * 60 * 24);
      }
      catch(ParseException e){
         e.printStackTrace();
      }
      return 0;
   }

   public static long DateDiff1(Date sDate1, Date sDate2){
      SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
      try{
         Date d0 = format.parse(format.format(sDate1));
         Date d1 = format.parse(format.format(sDate2));
         long time0 = d0.getTime();
         long time1 = d1.getTime();
         return (time1 - time0) / (1000 * 60 * 60 * 24);
      }
      catch(ParseException e){
         e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
      }
      return 0;
   }

   public static int DateDiff3(Date sDate1, Date sDate2, Integer day){
	   int b = 0;//������	   
	   //1:��ɫ��������ɫ
	   if(sDate1 != null && day != null && sDate2 != null){//����Ժ�
		   //sDate2 ���������ʱ��
		   long i = DateDiff1(sDate1, sDate2);   
		   long l = day+15;//ָ�������գ�������
		   if(i > l){
			   b = 1;
		   }
		   else if(i > day){
			   b = 2;
		   }
	   }
	   else if(sDate1 != null && day != null){//�����Ժ�
		   long i = DateDiff1(sDate1, new Date());
		   long l = day+15;//ָ�������գ�������
		   if(i > l){
			   b = 1;
		   }
		   else if(i > day){
			   b = 2;
		   }	   
	   }	      
	   return b;    
  }
   
   public static boolean betweenDate(String sDate1, String sDate2){
      if("no".equals(sDate1) || "no".equals(sDate2)){
         return false;
      }
      SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
      try{
         Date d0 = format.parse(format.format(stringToDate1(sDate1)));
         Date d1 = format.parse(format.format(new Date()));
         Date d2 = format.parse(format.format(stringToDate1(sDate2)));
         if(d1.compareTo(d0) >= 0 && d2.compareTo(d1) >= 0){
            return true;
         }
      }
      catch(ParseException e){
         e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
      }
      return false;
   }
   
   public static String stringToDate2(String date){
	      try{
	    	 if("".equals(date) || date == null){
	    		 return "";
	    	 }
	         return date.substring(0, 10);
	      }
	      catch(Exception e){
	         e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
	      }
	      return null;
	   }
   
   
   /**
    * ת��ʱ���ʽ
    * �ַ���ת������������,��ʽΪ"yyyy-mm-dd"
    * @param time
    * @return yyyy��mm��dd��
    */
   public static String getTimeNameString(String time){
      //1���˿ո�
      String[] a = time.split("-");
      String date = a[0]+"��"+a[1]+"��"+a[2]+"��";
      return date;

   }
   
   
   /**
    * ת��ʱ���ʽ
    * �ַ���ת������������,��ʽΪ"yyyy-mm-dd hh:mm:ss"
    * @param time
    * @return yyyy��mm��dd��
    */
   public static String[] getTimeAll(String time){
	  String date[] = new String[6];
      //1���˿ո�
      String[] a = time.split(" ");
      //2��ǰ�벿�ֹ���-
      String[] b = a[0].split("-");
      //3�Ѻ�벿�ֹ��ˣ�
      String[] c = a[1].split(":");
      date[0] = b[0];//��
      date[1] = b[1];//��
      date[2] = b[2];//��
      date[3] = c[0];//ʱ
      date[4] = c[1];//��
      date[5] = c[2];//��
      return date;

   }
   /**
    * �õ�ǰʱ�������µĵ�һ��
    * @param date
    * @return
    */
   public static String getFirstDayInMonth(){
	   Calendar cal = Calendar.getInstance();  
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	   cal.set(Calendar.DAY_OF_MONTH, 1);  
	   Date lastDate = cal.getTime();    //��������һ��
	   cal.set(Calendar.DAY_OF_MONTH, 1);  
	   Date firstDate = cal.getTime();
	   String firstdate = format.format(firstDate);
	   return firstdate;
   }
   /**
    * �õ��µ�һ��
    * @return
    */
   public static String getStartDayByMonth(){
   	 java.text.SimpleDateFormat df =  new  java.text.SimpleDateFormat("yyyy-MM-dd");       
        
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();    
        gc.set(Calendar.DAY_OF_MONTH, 1);   
        String day_first = df.format(gc.getTime());
        return day_first;


   }
   /**
    * �õ������һ��
    * @return
    */
   public static String getEndDayByMonth(){
   	java.text.SimpleDateFormat df =  new  java.text.SimpleDateFormat("yyyy-MM-dd");       
       
       GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance(); 
       
   	Calendar calendar = Calendar.getInstance();    
   	calendar.setTime(new Date());    
   	calendar.add(Calendar.MONTH, 1);    
   	Date  theDate = calendar.getTime();     
       //	���µĵ�һ��    
   	gc.setTime(theDate);    
   	gc.set(Calendar.DAY_OF_MONTH, 0);           
   	String day_first_nextM = df.format(gc.getTime());    
        return day_first_nextM;
   }

   	/**
   	 * �ж�ʱ���Ƿ�Ϊ���ꡢ����
   	 * TimeTools��ȡyear��month���������⣬ÿ����ĩ�����ڲ����£�����ʹ�����淽����ȡyear��month
   	 * @param year
   	 * @param month
   	 * @return
   	 */
   	public static boolean judgeYearMonth(int yr, int mo){
   		
   		boolean b = false;
   		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
   		String date = format.format(new Date());
   		String[] s = date.split("-");
   		String year = s[0];
   		String month = s[1];
   		if(Integer.parseInt(year) == yr && Integer.parseInt(month) == mo){
   			b = true;
   		}
   		
   		return b;
   	}
   	
   	
   	
   	/**
   	 * ��ȡ��ǰ���ڵ��ַ���
   	 * @return
   	 */
   	public static String getCurrentDateString(){
   		
   		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
   		String date = format.format(new Date());
   		String[] s = date.split("-");
   		String month = s[1];
   		String day = s[2];
   		if(month.indexOf("0") == 0){
   			month = month.replaceFirst("0", "");
   		}
   		if(day.indexOf("0") == 0){
   			day = day.replaceFirst("0", "");
   		}
   		String str = month + "." + day;
   		
   		return str;
   	}
   	
   	/**
   	 * ��ȡָ�����ڵ�ǰһ��
   	 * @param date
   	 * @return
   	 */
   	public static String getPreviousDay(Date date) {
   		
   		Calendar calendar = Calendar.getInstance();
   		calendar.setTime(date);
   		calendar.add(Calendar.DAY_OF_MONTH, -1);
   		date = calendar.getTime();
   		String rDate = dateToString(date, "yyyy-mm-dd");
   		
   		return rDate;
   		
   	}
   	/**
   	 * ת�����ڸ�ʽ 8.2 --> 08-02
   	 * @param year
   	 * @param date
   	 * @return
   	 */
   	public static String convertDate(String year, String date){
   		
   		String str = "";
   		String[] s = date.split("\\.");
   		String month = s[0];
   		String day = s[1];
   		if(month.length() == 1){
   			month = "0" + month;
   		}
   		if(day.length() == 1){
   			day = "0" + day;
   		}
   		str = year + "-" + month + "-" + day;
   		
   		return str;
   		
   	}
   	
   	/**
	 * ת�����ڸ�ʽ 08-02 --> 8.2
	 * @param date
	 * @return
	 */
	public static String convertDate(String date){
		
		String str = "";
		String[] s = date.split("-");
		String month = s[1];
		String day = s[2];
		
		if(month.startsWith("0")){
			month = month.replaceFirst("0", "");
		}
		
		if(day.startsWith("0")){
			day = day.replaceFirst("0", "");
		}
		str = month + "." + day;
		
		return str;
		
	}
   	
   	/**
   	 * �ж������Ƿ�Ϊ����
   	 * @param date
   	 * @return
   	 */
   	public static boolean judgeIfSunday(String date){
   		
   		boolean flag = false;
   		
   		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
   		Calendar calendar = Calendar.getInstance(); 
   		try {
   			calendar.setTime(sdf.parse(date));
   		} catch (ParseException e) {
   			e.printStackTrace();
   		} 
   		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); 
   		
   		if(dayOfWeek == 1){
   			flag = true;
   		}
   		
   		return flag;
   	}
   	/**
   	 * ���ش������ݺ��·ݵ�ǰ12���µļ�¼
   	 * @param years
   	 * @param months
   	 * @return
   	 */
   	public static String getlast12months(String years,String months){
   		String s = "";
   		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
   	       Calendar calendar = Calendar.getInstance();
   		      for(int i=12;i>=1;i--){
   		    	  calendar = Calendar.getInstance();
   		    	  try {
   		  			calendar.setTime(sdf.parse(years+"-"+months+"-01"));
   		  		} catch (ParseException e) {
   		  			e.printStackTrace();
   		  		}
   		    	  calendar.add(calendar.MONTH, -(i));
   		          int year = calendar.get(calendar.YEAR);
   		          int month = calendar.get(calendar.MONTH)+1; 
   		    	 if(i==12){
   		    		 s = year+"��"+month+"��";
   		    	 }else{
   		    		 s+=","+year+"��"+month+"��";
   		    	 }
   		      }
   		     //System.out.println("s=="+s);
   		       return s;      
   	}
   	
   	/**
   	 * ��õ�ǰ�����ж�����
   	 * @return day 
   	 */
   	public static int dateOfMonth(int year,int month){
   		Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.YEAR,year);
	   	cal.set(Calendar.MONTH, month - 1);//Java�·ݴ�0��ʼ��
	   	int dateOfMonth = cal.getActualMaximum(Calendar.DATE); 
   		return dateOfMonth;
   	}
   	
	/**
   	 * ��õ�ǰ������
   	 * @return day 
   	 */
   	public static int dateOfYMD(String type){
   		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(new Date());
   		Calendar cal = Calendar.getInstance();
   		try { 
			cal.setTime(format.parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int time=0;
		if(type==null){
			type = "";
		}
		if("y".equalsIgnoreCase(type)){
			time = cal.get(cal.YEAR);
		}
		else if("m".equalsIgnoreCase(type)){
			time = cal.get(cal.MONTH)+1; 
		}
		else if("d".equalsIgnoreCase(type)){
			time = cal.get(cal.DATE); 
		}

   		return time;
   	}
}