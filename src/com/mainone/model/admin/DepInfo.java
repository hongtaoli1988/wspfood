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
public class DepInfo implements Serializable{
    
    private String DEP_ID;//部门ID
	private String DEP_CODE;//部门编号
	private String DEP_NAME;//部门名称
	private String Sn;//排序序列号
	
	public String getDEP_ID() {
		return DEP_ID;
	}
	public void setDEP_ID(String dep_id) {
		DEP_ID = dep_id;
	}
	public String getDEP_CODE() {
		return DEP_CODE;
	}
	public void setDEP_CODE(String dep_code) {
		DEP_CODE = dep_code;
	}
	public String getDEP_NAME() {
		return DEP_NAME;
	}
	public void setDEP_NAME(String dep_name) {
		DEP_NAME = dep_name;
	}
	public String getSn() {
		return Sn;
	}
	public void setSn(String sn) {
		Sn = sn;
	}

}
