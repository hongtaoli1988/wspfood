/*
 * 创建日期 2012-12-11
 *
 * TODO 要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package com.mainone.app.admin;
import java.io.Serializable;

import com.mainone.model.admin.ComInfo;
import com.mainone.model.admin.DepInfo;
import com.mainone.model.admin.PosiInfo;
import com.mainone.model.admin.RoleInfo;
import com.mainone.model.admin.UserInfo;

/**
 * @author zhanglei
 *
 * TODO 要更改此生成的类型注释的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class LoginInfo implements Serializable{
    
	private ComInfo comInfo;//公司
	private DepInfo depInfo;//部门
	private PosiInfo posiInfo;//岗位
	private UserInfo userInfo;//用户
	private RoleInfo RoleInfo;//角色

	public ComInfo getComInfo() {
		return comInfo;
	}

	public void setComInfo(ComInfo comInfo) {
		this.comInfo = comInfo;
	}

	public DepInfo getDepInfo() {
		return depInfo;
	}

	public void setDepInfo(DepInfo depInfo) {
		this.depInfo = depInfo;
	}

	public PosiInfo getPosiInfo() {
		return posiInfo;
	}

	public void setPosiInfo(PosiInfo posiInfo) {
		this.posiInfo = posiInfo;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public RoleInfo getRoleInfo() {
		return RoleInfo;
	}

	public void setRoleInfo(RoleInfo roleInfo) {
		RoleInfo = roleInfo;
	}
}

	