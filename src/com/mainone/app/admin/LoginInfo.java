/*
 * �������� 2012-12-11
 *
 * TODO Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
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
 * TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class LoginInfo implements Serializable{
    
	private ComInfo comInfo;//��˾
	private DepInfo depInfo;//����
	private PosiInfo posiInfo;//��λ
	private UserInfo userInfo;//�û�
	private RoleInfo RoleInfo;//��ɫ

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

	