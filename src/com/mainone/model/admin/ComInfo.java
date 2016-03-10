/*
 * 创建日期 2012-12-11
 *
 * TODO 要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package com.mainone.model.admin;
import java.io.Serializable;

/**
 * @author zhanglei
 *
 * TODO 要更改此生成的类型注释的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class ComInfo implements Serializable{
    
	private String COM_ID;//公司ID
	private String COM_CODE;//公司编号
	private String COM_NAME;//公司名称
	private String SUB_COM_ID;//总子公司表识
	private String Rate;//公司级别
	private String Areakey;//大区
	private String Builddata;//成立时间
	private String Kotwal;//行政负责人
	private String Kotwalid;//行政总监Id
	private String Template;//公司模板标记
	private String Ispayoff;//是否盈利
	private String Majordomo;//公司总监
	private String Majordomoid;//公司总监Id
	
	public String getCOM_CODE() {
		return COM_CODE;
	}
	public void setCOM_CODE(String com_code) {
		COM_CODE = com_code;
	}
	public String getCOM_NAME() {
		return COM_NAME;
	}
	public void setCOM_NAME(String com_name) {
		COM_NAME = com_name;
	}
	public String getSUB_COM_ID() {
		return SUB_COM_ID;
	}
	public void setSUB_COM_ID(String sub_com_id) {
		SUB_COM_ID = sub_com_id;
	}
	public String getRate() {
		return Rate;
	}
	public void setRate(String rate) {
		Rate = rate;
	}
	public String getAreakey() {
		return Areakey;
	}
	public void setAreakey(String areakey) {
		Areakey = areakey;
	}
	public String getBuilddata() {
		return Builddata;
	}
	public void setBuilddata(String builddata) {
		Builddata = builddata;
	}
	public String getKotwal() {
		return Kotwal;
	}
	public void setKotwal(String kotwal) {
		Kotwal = kotwal;
	}
	public String getTemplate() {
		return Template;
	}
	public void setTemplate(String template) {
		Template = template;
	}
	public String getIspayoff() {
		return Ispayoff;
	}
	public void setIspayoff(String ispayoff) {
		Ispayoff = ispayoff;
	}
	public String getMajordomo() {
		return Majordomo;
	}
	public void setMajordomo(String majordomo) {
		Majordomo = majordomo;
	}
	public String getKotwalid() {
		return Kotwalid;
	}
	public void setKotwalid(String kotwalid) {
		Kotwalid = kotwalid;
	}
	public String getMajordomoid() {
		return Majordomoid;
	}
	public void setMajordomoid(String majordomoid) {
		Majordomoid = majordomoid;
	}
	public String getCOM_ID() {
		return COM_ID;
	}
	public void setCOM_ID(String com_id) {
		COM_ID = com_id;
	}
	

}
