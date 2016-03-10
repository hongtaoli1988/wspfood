package com.mainone.util;

import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by IntelliJ IDEA.
 * User: zhanglei
 * Date: 2012-11-29
 * Time: 9:02:34
 * To change this template use File | Settings | File Templates.
 */
public class SysContext{

   //直接获得request
   public static HttpServletRequest getRequest(){
      return ServletActionContext.getRequest();
   }

   //直接获得session
   public static HttpSession getSession(){
      return getRequest().getSession();
   }

   //直接获得response
   public static HttpServletResponse getResponse(){
      return ServletActionContext.getResponse();
   }

   //直接获得Application
   public static ServletContext getApplication(){
      return ServletActionContext.getServletContext();
   }

   /**
    * 从session中得到验证码
    *
    * @param request
    * @return
    */
   public static String getImgSession(HttpServletRequest request){
      HttpSession s = request.getSession();
      String rand = (String) s.getAttribute("rand");
      return rand;
	}
}
