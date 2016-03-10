package com.mainone.common;


import java.util.ArrayList;
import java.util.List;
import com.mainone.dao.IBaseServiceDao;

import com.mainone.util.DBRecord;
import com.mainone.util.StringDist;
/**
 * @since 2012-8-8
 * @author zhanglei
 *
 */

public class ToolsApp {
	 private IBaseServiceDao iBaseServiceDaoA;
	 private IBaseServiceDao iBaseServiceDaoB;
	 public void setIBaseServiceDaoA(IBaseServiceDao iBaseServiceDaoA) {
			this.iBaseServiceDaoA = iBaseServiceDaoA;
	 }
	 public void setIBaseServiceDaoB(IBaseServiceDao baseServiceDaoB) {
			iBaseServiceDaoB = baseServiceDaoB;
		} 
		
	 /**
	 * ����һ����ҵ�����б�(����ģ��ͻ�������ҵ��)
	 * @return String <option></option>
	 */	
	 public String getI_IndustryList(){
				String sql = " select *  from CRM_INDUSTRY_INFO where INDUSTRY_LEVEL='1' and STATUS='1'";
				List list=null;
				String str="";
				DBRecord dbr=null;
				str+="<option  selected value=''>��ѡ��</option>";
				try {
					list=this.iBaseServiceDaoB.getSqlJdbc(sql);
					for(int i=0;i<list.size();i++){
						dbr = (DBRecord)list.get(i);
						String INDUSTRY_ID = dbr.getString("INDUSTRY_ID")==null?"":dbr.getString("INDUSTRY_ID");
						String INDUSTRY_NAME = dbr.getString("INDUSTRY_NAME")==null?"":dbr.getString("INDUSTRY_NAME");
						str+="<option  value="+INDUSTRY_ID+">"+INDUSTRY_NAME+"</option>";
						
					}	 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	     return str;
				
	}
	 
	 
	/**
	 * ���������б��������С�����ѡ��ѡ�	 
	 * @param  select    Ĭ��ѡ��valueֵ����Ĭ������select=""
	 * @param  str       "����"����"��ѡ��"
	 * @return String
	 */	
	public static String getIndustryList(String select,String str){
		return getList(industry,select,str);
	}
	/**
	 * ���������б����������С�����ѡ��ѡ�
	 * @param  select    Ĭ��ѡ��valueֵ
	 * @return String
	 */	
	public static String getIndustryList2(String select){
		return getList2(industry,select);
	}
	
	
	/**
	 * ���ɶ�ѡ���б�
	 * @param  select    Ĭ��ѡ��valueֵ
	 * @return String
	 */	
	public static String getIndustryCheckBox(String select){
		return getCheckBox(industry,select);
	}
	
	/**
	 * ����ָ�������б�
	 * @param  select    Ĭ��ѡ��valueֵ
	 * @return String
	 */	
	public static String getIndustryName(String select){
		return getName(industry,select);
	}
	
	/**
	 * ����ָ�������б�
	 * @param  select    Ĭ��ѡ��valueֵ
	 * @return String
	 */	
	public static String getMarketScope(String select){
		return getName(marketScope,select);
	}
	
	
	/**
	 * ���������б��������С�����ѡ��ѡ�	 
	 * @param  select    Ĭ��ѡ��valueֵ����Ĭ������select=""
	 * @param  str       "����"����"��ѡ��"
	 * @return String
	 */	
	public static String getList(String[][] input,String select,String str)
	{
		String strList = "";
		if(select.equals(""))
		{
			strList +="<option value=\"" + select + "\" selected >" + str + "</option><br>";
		}
		else
		{
			strList +="<option value=\"\">" + str + "</option><br>";
		}
		try
		{
			if(input.length>0)
			{
				for(int i=0;i<input.length;i++)
				{
					String[] a = input[i];
						if(a[0].equals(select))
						{
							strList +="<option value=\""+ select +  "\" selected >"
							+ a[1] +"</option><br>";
						}
						else
						{
							strList +="<option value=\"" + a[0] + "\">"
							+ a[1] +"</option><br>";
						}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return strList;		
	}
	/**
	 * ���������б����������С�����ѡ��ѡ�
	 * @param  select    Ĭ��ѡ��valueֵ
	 * @return String
	 */	
	public static String getList2(String[][] input,String select)
	{
		String strList = "";
		try
		{
			if(input.length>0)
			{
				for(int i=0;i<input.length;i++)
				{
					String[] a = input[i];
						if(a[0].equals(select))
						{
							strList +="<option value=\""+ select +  "\" selected >"
							+ a[1] +"</option><br>";
						}
						else
						{
							strList +="<option value=\"" + a[0] + "\">"
							+ a[1] +"</option><br>";
						}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return strList;		
	}
	
	
	/**
	 * ���ɶ�ѡ�б�
	 * @param  select    Ĭ��ѡ��valueֵ
	 * @return String
	 */	
	public static String getCheckBox(String[][] input,String value)
	{
		String strList = "";
		try
		{
			if(input.length>0)
			{
				for(int i=0;i<input.length;i++)
				{
					String[] a = input[i];
					
					if(value!=null&&!value.equals("")){
				    	String[] aa = StringDist.splitString(value,'|'); 
							
							String[] b = new String[input.length];
							for(int j=0;j<aa.length;j++){
								if(a[0].equals(aa[j])){
									b[i] = "checked";
								}
							}
							
							if(b[i]==null){
								b[i] = "";
							}
							
							strList +="<li><input name='INDUSTRY_ID' type='checkbox' " +
							"value=\""+ a[0] +  "\" "+b[i]+" >" + a[1] +"</li>";
				    	}
				    	else{
			
							strList +="<li><input name='INDUSTRY_ID' value=\""+ a[0] +  "\" type='checkbox'>" + a[1] +"</li>";
				
				    	}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return strList;		
	}
	
	
	
	/**
	 * ����ָ�������б�
	 * @param  select    Ĭ��ѡ��valueֵ
	 * @return String
	 */	
	public static String getName(String[][] input,String value)
	{
		String strList = "";
		try
		{
			if(input.length>0)
			{
				for(int i=0;i<input.length;i++)
				{
					String[] a = input[i];
					
					if(value!=null&&!value.equals("")){
				    	String[] aa = StringDist.splitString(value,'|'); 
						for(int j=0;j<aa.length;j++){
							if(a[0].equals(aa[j])){
								strList +=""+ a[1] +"��";
							}
						}
			    	}
			    	else{
						strList = "-";
			    	}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return strList;		
	}
	
	
	 
	 
	 /**
	     * ��ҵ�б�
	     * @param defaultValue
	     * @return
	     */
		static String industry[][] = {{"1","�����Ʒ������"},{"3","ũ������"},{"17","ʳƷ�����ϡ��̾�"},{"18","����"},{"19","��֯��Ƥ��"},
			{"20","��װ������"},{"21","�������豸"},{"22","�������Ǳ�"},{"23","���������ӡ��繤"},{"24","��Դ��ұ�𡢿��"},{"25","ͨѶ"},
			{"26","���ԡ�����"},{"27","��װ��Ʒ��ֽ��"},{"28","ӡˢ������"},{"29","�칫�Ľ�"},{"30","�Ҿ���Ʒ"},{"31","���ա���Ʒ"},
			{"32","���"},{"33","�Ļ����˶�������"},{"34","���ݡ�����������"},{"35","������װ�Ρ����ز�"},{"36","��ͨ���䡢�ִ�������"},
			{"37","���ա���Ʒ"},{"38","������ʩ������"},{"39","��ȫ����"},{"40","����"},{"41","��桢�߻�����ý"},{"42","����������"},
			{"43","��������������"},{"44","���������ѯ���н�"},{"45","����"}};
		
		
		/*
		 * ��ҵ���Ͽ�ͻ���Դ
		 */
		public static String getSource(String status){
			if(status.equals("1")){
				return "չ���ռ�";
			}
			else if(status.equals("2")){
				return "�绰����";
			}
			else if(status.equals("3")){
				return "ý������";
			}
			else if(status.equals("4")){
				return "���ѽ���";
			}
			else if(status.equals("5")){
				return "�����";
			}
			else if(status.equals("6")){
				return "����";
			}
			else if(status.equals("7")){
				return "�ʹ��ռ�";
			}
			else return "-";
			
		}
		
		/**
		 * SFA�ͻ���Դ
		 * @param status
		 * @return
		 */
		public static String getSfaSource(String status){
			if(status.equals("3")){
				return "�绰����";
			}
			else if(status.equals("4")){
				return "ת����";
			}
			else if(status.equals("5")){
				return "İ���ݷ�";
			}
			else if(status.equals("8")){
				return "�ֻ�¼��";
			}
			else if(status.equals("9")){
				return "չ���ռ�";
			}
			else if(status.equals("10")){
				return "����";
			}
			else return "-";
			
		}
		
		/**
		 * ��ȡsfa�ͻ���Դ������
		 * @param status
		 * @return
		 */
		public static String getSfaSourceSelect(String status, String name){
			
			String str = "";
			str+="<option selected value=''>" + name + "</option>";
			
			if(null == status || "".equals(status)){
				for (int i = 0; i < sfaSource.length; i++) {
					str += "<option value=" + sfaSource[i] + " >" + getSfaSource(sfaSource[i]) + "</option>";
				}
			}else{
				for (int i = 0; i < sfaSource.length; i++) {
					if(sfaSource[i].equals(status)){
						str += "<option value=" + sfaSource[i] + " selected >" + getSfaSource(sfaSource[i]) + "</option>";
					}else{
						str += "<option value=" + sfaSource[i] + " >" + getSfaSource(sfaSource[i]) + "</option>";
					}
				}
			}
			
			return str;
			
		}
		
		
		/**
		 * ��ȡsfa�ͻ���Դ������
		 * @param status
		 * @return
		 */
		public static String getSfaSourceSelect(String status){
			
			String str = "";
			
			if(null == status || "".equals(status)){
				for (int i = 0; i < sfaSource.length; i++) {
					str += "<option value=" + sfaSource[i] + " >" + getSfaSource(sfaSource[i]) + "</option>";
				}
			}else{
				for (int i = 0; i < sfaSource.length; i++) {
					if(sfaSource[i].equals(status)){
						str += "<option value=" + sfaSource[i] + " selected >" + getSfaSource(sfaSource[i]) + "</option>";
					}else{
						str += "<option value=" + sfaSource[i] + " >" + getSfaSource(sfaSource[i]) + "</option>";
					}
				}
			}
			
			return str;
			
		}
		
		
		static String sfaSource[] = {"3", "4", "5", "8", "9", "10"};
		
		
		 /**
	     * �г���Χ
	     * @param defaultValue
	     * @return
	     */
		static String marketScope[][] = {{"A","���ڱ�ʡ���У�"},{"B","����"},{"C","�۰�̨"},{"D","����"}};
	
}
