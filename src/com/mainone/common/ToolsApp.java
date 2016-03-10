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
	 * 生成一级行业下拉列表(完善模块客户所属行业用)
	 * @return String <option></option>
	 */	
	 public String getI_IndustryList(){
				String sql = " select *  from CRM_INDUSTRY_INFO where INDUSTRY_LEVEL='1' and STATUS='1'";
				List list=null;
				String str="";
				DBRecord dbr=null;
				str+="<option  selected value=''>请选择</option>";
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
	 * 生成下拉列表（带“所有”或“请选择”选项）	 
	 * @param  select    默认选项value值，若默认所有select=""
	 * @param  str       "所有"还是"请选择"
	 * @return String
	 */	
	public static String getIndustryList(String select,String str){
		return getList(industry,select,str);
	}
	/**
	 * 生成下拉列表（不带“所有”或“请选择”选项）
	 * @param  select    默认选项value值
	 * @return String
	 */	
	public static String getIndustryList2(String select){
		return getList2(industry,select);
	}
	
	
	/**
	 * 生成多选框列表
	 * @param  select    默认选项value值
	 * @return String
	 */	
	public static String getIndustryCheckBox(String select){
		return getCheckBox(industry,select);
	}
	
	/**
	 * 生成指定数据列表
	 * @param  select    默认选项value值
	 * @return String
	 */	
	public static String getIndustryName(String select){
		return getName(industry,select);
	}
	
	/**
	 * 生成指定数据列表
	 * @param  select    默认选项value值
	 * @return String
	 */	
	public static String getMarketScope(String select){
		return getName(marketScope,select);
	}
	
	
	/**
	 * 生成下拉列表（带“所有”或“请选择”选项）	 
	 * @param  select    默认选项value值，若默认所有select=""
	 * @param  str       "所有"还是"请选择"
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
	 * 生成下拉列表（不带“所有”或“请选择”选项）
	 * @param  select    默认选项value值
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
	 * 生成多选列表
	 * @param  select    默认选项value值
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
	 * 生成指定数据列表
	 * @param  select    默认选项value值
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
								strList +=""+ a[1] +"；";
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
	     * 行业列表
	     * @param defaultValue
	     * @return
	     */
		static String industry[][] = {{"1","五金制品、工具"},{"3","农林牧渔"},{"17","食品、饮料、烟酒"},{"18","化工"},{"19","纺织、皮革"},
			{"20","服装、服饰"},{"21","电器、设备"},{"22","仪器、仪表"},{"23","电力、电子、电工"},{"24","能源、冶金、矿产"},{"25","通讯"},
			{"26","电脑、网络"},{"27","包装用品、纸类"},{"28","印刷、出版"},{"29","办公文教"},{"30","家居用品"},{"31","工艺、礼品"},
			{"32","玩具"},{"33","文化、运动、娱乐"},{"34","宾馆、餐饮、旅游"},{"35","建筑、装饰、房地产"},{"36","交通运输、仓储、邮政"},
			{"37","工艺、礼品"},{"38","公共设施、环保"},{"39","安全防护"},{"40","金融"},{"41","广告、策划、传媒"},{"42","合作、招商"},
			{"43","代理、批发、零售"},{"44","商务服务、咨询、中介"},{"45","其它"}};
		
		
		/*
		 * 企业资料库客户来源
		 */
		public static String getSource(String status){
			if(status.equals("1")){
				return "展会收集";
			}
			else if(status.equals("2")){
				return "电话来访";
			}
			else if(status.equals("3")){
				return "媒体宣传";
			}
			else if(status.equals("4")){
				return "朋友介绍";
			}
			else if(status.equals("5")){
				return "促销活动";
			}
			else if(status.equals("6")){
				return "其他";
			}
			else if(status.equals("7")){
				return "客代收集";
			}
			else return "-";
			
		}
		
		/**
		 * SFA客户来源
		 * @param status
		 * @return
		 */
		public static String getSfaSource(String status){
			if(status.equals("3")){
				return "电话开发";
			}
			else if(status.equals("4")){
				return "转介绍";
			}
			else if(status.equals("5")){
				return "陌生拜访";
			}
			else if(status.equals("8")){
				return "手机录入";
			}
			else if(status.equals("9")){
				return "展会收集";
			}
			else if(status.equals("10")){
				return "其他";
			}
			else return "-";
			
		}
		
		/**
		 * 获取sfa客户来源下拉框
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
		 * 获取sfa客户来源下拉框
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
	     * 市场范围
	     * @param defaultValue
	     * @return
	     */
		static String marketScope[][] = {{"A","国内本省（市）"},{"B","国内"},{"C","港澳台"},{"D","海外"}};
	
}
