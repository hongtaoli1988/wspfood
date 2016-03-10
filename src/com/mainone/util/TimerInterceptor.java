package com.mainone.util;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
/**
 * @desc Ê±¼äÀ¹½ØÆ÷
 * @author zhanglei
 * @date 2012-12-10
 */
public class TimerInterceptor  extends MethodFilterInterceptor implements Interceptor{
	 protected String doIntercept(ActionInvocation actionInvocation) throws Exception{
	      ActionContext ctx = actionInvocation.getInvocationContext();
	      Map session = ctx.getSession();
	      String userId = (String)session.get("userId");
	      if("".equals(userId) || null == userId){
	         return "timer";
	      }
	      actionInvocation.invoke();
	      return "";  
	   }
	}

