/*
 * 创建日期 2005-7-25
 *
 * TODO 要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package com.mainone.util;

/**
 * @author wangguan
 *
 * 调用形式:request.setAttribute("message",Message.back(info,page));
 * 
 */
public class Message {
	/**
	 * 显示信息及关闭按钮
	 * @param message
	 * @param reload 是否刷新父页面
	 * @return
	 */
	public static String close(String message,boolean reload){
		StringBuffer str=new StringBuffer();
		str.append("<font color=\"#FF0000\">").append(message).append("</font><br><br>\r\n");
		if(reload){
		    str.append("<input class=\"button\" type=button name=submit value=关闭 onClick=\"javascript:closewin();\">\r\n");
		}
		else{
		    str.append("<input class=\"button\" type=button name=submit value=关闭 onClick=\"javascript:window.close();\">\r\n");
		}
		return str.toString();
	}
	/**
	 * 显示信息及关闭按钮
	 * @param message
	 * @param 父页面转向页面
	 * @return
	 */
	public static String close(String message,String url){
		StringBuffer str=new StringBuffer();
		str.append("<font color=\"#FF0000\">").append(message).append("</font><br><br>\r\n");
		str.append("<input class=\"button\" type=button name=submit value=关闭 onClick=\"javascript:closewin2('").append(url).append("');\">\r\n");
		return str.toString();
	}
	/**
	 * 显示信息及返回按钮,返回至上一页
	 * @param message
	 * @return
	 */
	public static String back(String message){
		StringBuffer str=new StringBuffer();
		str.append("<font color=\"#FF0000\">").append(message).append("</font><br><br>\r\n");
		str.append("<input class=\"button\" type=button name=submit value=返回 onClick=history.back(-1)>\r\n");
		return str.toString();
	}
	/**
	 * 显示信息及返回按钮,返回至指定页面
	 * @param message
	 * @param page
	 * @return
	 */
	public static String back(String message,String page){
		StringBuffer str=new StringBuffer();
		str.append("<font color=\"#FF0000\">").append(message).append("</font><br><br>\r\n");
		str.append("<input class=\"button\" type=button name=submit value=返回 onClick=go('").append(page).append("')>\r\n");
		return str.toString();
	}
	/**
	 * 只显示信息
	 * @param message
	 * @return
	 */
	public static String none(String message){
		StringBuffer str=new StringBuffer();
		str.append("<font color=\"#FF0000\">").append(message).append("</font><br><br>\r\n");
		return str.toString();
	}
	
	/**
	 * 显示信息，后面提示链接信息
	 * @param message
	 * @return
	 */
	public static String noneaherf(String message,String returnMethod){
		StringBuffer str=new StringBuffer();
		str.append("<font color=\"#FF0000\">").append(message).append("<a href='").append(returnMethod).append("'>是</a></font><br><br>\r\n");
		str.append("<input class=\"button\" type=button name=submit value=返回 onClick=history.back(-1)>\r\n");
		return str.toString();
	}
}
