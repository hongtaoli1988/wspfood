/*
 * �������� 2012-12-11
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
public class DepInfo implements Serializable{
    
    private String DEP_ID;//����ID
	private String DEP_CODE;//���ű��
	private String DEP_NAME;//��������
	private String Sn;//�������к�
	
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
