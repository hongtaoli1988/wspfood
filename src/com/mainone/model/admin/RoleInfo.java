/*
 * �������� 2012-12-28
 *
 * TODO Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
package com.mainone.model.admin;
import java.io.Serializable;

/**
 * @author zhanglei
 *
 * TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class RoleInfo implements Serializable{
    
    private String ROLE_ID;//����ID

	private String ROLE_NAME;//��������

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
