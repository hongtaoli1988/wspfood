package com.mainone.util;

/**
 * Created by IntelliJ IDEA.
 * User: wangtao
 * Date: 2007-11-8
 * Time: 11:50:57
 * To change this template use File | Settings | File Templates.
 */

import javax.servlet.jsp.JspWriter;


public final class Debuger{

   //测试平台 ，debuggingOn = true 即在后头输出 数据
   public static final boolean debuggingOn = true;
   //正式平台 ，debuggingOn = false 即在后台不输出数据
   //public static final boolean debuggingOn = false;
   public static JspWriter out;

   public static void init(JspWriter jspOut){
      out = jspOut;
   }

   public static void printPage(String msg){
      if(out != null && debuggingOn){
         try{
            out.println(msg);
         }
         catch(Exception e){
         }
      }
   }

   public static void printPage(Object msg){
      if(out != null && debuggingOn){
         try{
            out.println(msg);
         }
         catch(Exception e){
         }

      }
   }

   /*
    public static void assert(boolean condition)
    {
       if (!condition)
       {
          println("Assert Failed: ");
          throw new IllegalArgumentException();
       }
    }
    */
   public static void print(Exception e){
      print(e, null);
   }

   public static void print(Exception e, String msg){
      print((Throwable) e, msg);
   }

   public static void print(String msg){
      if(debuggingOn){
         System.out.print(msg);
      }
   }

   public static void print(Throwable t){
      print(t, null);
   }

   public static void print(Throwable t, String msg){
      if(debuggingOn){
         System.out.println("Received throwable with Message: " + t.getMessage());
         if(msg != null){
            System.out.print(msg);
         }
         t.printStackTrace();
      }
   }

   public static void println(String msg){
      if(debuggingOn){
         System.out.println(msg);
      }
   }

   public static void println(Object msg){
      if(debuggingOn){
         System.out.println(msg);
      }
   }

   public static void println(int msg){
      if(debuggingOn){
         System.out.println(msg);
      }
   }

   public static void println(long msg){
      if(debuggingOn){
         System.out.println(msg);
      }
   }

   public static void println(float msg){
      if(debuggingOn){
         System.out.println(msg);
      }
   }

   public static void println(char x[]){
      if(debuggingOn){
         System.out.println(x);
      }
   }

   public static void println(boolean msg){
      if(debuggingOn){
         System.out.println(msg);
      }
   }
}