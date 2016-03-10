package com.mainone.util;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: wangtao
 * Date: 2007-11-29
 * Time: 9:08:43
 * To change this template use File | Settings | File Templates.
 */
public class BaseServletContext extends ActionSupport implements ServletContextAware, ServletRequestAware, ServletResponseAware, Runnable{

   public void start(String id, long time){
      this.userInfo = id;//模块+记录id
      this.time = time;//可设置操作时间
      Thread myThread = new Thread(this);
      myThread.start();
   }

   public void run(){
      try{
         ServletContext s = servletContext;
         Thread.sleep(time);
         s.removeAttribute(userInfo);
      }
      catch(Exception e){
         e.printStackTrace();
      }
   }

   public String removeInfo(){
      try{
         context = request.getParameter("context");
         String sign = request.getParameter("sign");
         ServletContext s = servletContext;
         s.removeAttribute(sign);
      }
      catch(Exception e){
         e.printStackTrace();
      }
      return "next";
   }

   public void aaaaa(String str){
      ActionContext stx =  ActionContext.getContext();
      stx.getApplication().remove(str);
   }

   public ServletContext getServletContext(){
      return servletContext;
   }

   public HttpServletRequest getHttpServletRequest(){
      return request;
   }

   public HttpServletResponse getHttpServletResponse(){
      return response;
   }

   public void setServletResponse(HttpServletResponse httpServletResponse){
      this.response = httpServletResponse;
   }

   public void setServletRequest(HttpServletRequest httpServletRequest){
      this.request = httpServletRequest;
   }

   public void setServletContext(ServletContext servletContext){
      this.servletContext = servletContext;
   }

   public String getUserInfo(){
      return userInfo;
   }

   public void setUserInfo(String userInfo){
      this.userInfo = userInfo;
   }

   public long getTime(){
      return time;
   }

   public void setTime(long time){
      this.time = time;
   }

   public String getContext(){
      return context;
   }

   public void setContext(String context){
      this.context = context;
   }
   
   ActionContext ctx = ActionContext.getContext();        
   
   private String context;
   private String userInfo;
   private long time;
   public ServletContext servletContext;
   public HttpServletRequest request ;
   public HttpServletResponse response;
}
