/*
 * �������� 2005-7-25
 *
 * TODO Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
package com.mainone.util;

/**
 * @author wangguan
 *
 * ������ʽ:request.setAttribute("message",Message.back(info,page));
 * 
 */
public class Message {
	/**
	 * ��ʾ��Ϣ���رհ�ť
	 * @param message
	 * @param reload �Ƿ�ˢ�¸�ҳ��
	 * @return
	 */
	public static String close(String message,boolean reload){
		StringBuffer str=new StringBuffer();
		str.append("<font color=\"#FF0000\">").append(message).append("</font><br><br>\r\n");
		if(reload){
		    str.append("<input class=\"button\" type=button name=submit value=�ر� onClick=\"javascript:closewin();\">\r\n");
		}
		else{
		    str.append("<input class=\"button\" type=button name=submit value=�ر� onClick=\"javascript:window.close();\">\r\n");
		}
		return str.toString();
	}
	/**
	 * ��ʾ��Ϣ���رհ�ť
	 * @param message
	 * @param ��ҳ��ת��ҳ��
	 * @return
	 */
	public static String close(String message,String url){
		StringBuffer str=new StringBuffer();
		str.append("<font color=\"#FF0000\">").append(message).append("</font><br><br>\r\n");
		str.append("<input class=\"button\" type=button name=submit value=�ر� onClick=\"javascript:closewin2('").append(url).append("');\">\r\n");
		return str.toString();
	}
	/**
	 * ��ʾ��Ϣ�����ذ�ť,��������һҳ
	 * @param message
	 * @return
	 */
	public static String back(String message){
		StringBuffer str=new StringBuffer();
		str.append("<font color=\"#FF0000\">").append(message).append("</font><br><br>\r\n");
		str.append("<input class=\"button\" type=button name=submit value=���� onClick=history.back(-1)>\r\n");
		return str.toString();
	}
	/**
	 * ��ʾ��Ϣ�����ذ�ť,������ָ��ҳ��
	 * @param message
	 * @param page
	 * @return
	 */
	public static String back(String message,String page){
		StringBuffer str=new StringBuffer();
		str.append("<font color=\"#FF0000\">").append(message).append("</font><br><br>\r\n");
		str.append("<input class=\"button\" type=button name=submit value=���� onClick=go('").append(page).append("')>\r\n");
		return str.toString();
	}
	/**
	 * ֻ��ʾ��Ϣ
	 * @param message
	 * @return
	 */
	public static String none(String message){
		StringBuffer str=new StringBuffer();
		str.append("<font color=\"#FF0000\">").append(message).append("</font><br><br>\r\n");
		return str.toString();
	}
	
	/**
	 * ��ʾ��Ϣ��������ʾ������Ϣ
	 * @param message
	 * @return
	 */
	public static String noneaherf(String message,String returnMethod){
		StringBuffer str=new StringBuffer();
		str.append("<font color=\"#FF0000\">").append(message).append("<a href='").append(returnMethod).append("'>��</a></font><br><br>\r\n");
		str.append("<input class=\"button\" type=button name=submit value=���� onClick=history.back(-1)>\r\n");
		return str.toString();
	}
}
