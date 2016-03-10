/*
 * 创建日期 2012-12-28
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
public class RoleInfo implements Serializable{
    
    private String ROLE_ID;//部门ID

	private String ROLE_NAME;//部门名称

	public String getROLE_ID() {
		return ROLE_ID;
	}

	public void setROLE_ID(String role_id) {
		ROLE_ID = role_id;
	}

	public String getROLE_NAME() {
		return ROLE_NAME;
	}

	public void setROLE_NAME(String role_name) {
		ROLE_NAME = role_name;
	}
	
}
