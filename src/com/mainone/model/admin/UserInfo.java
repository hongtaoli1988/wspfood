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
public class UserInfo implements Serializable{
    
    private String USER_ID;//Ա��ID
    private String CD_ID;//��˾������֯��Ϣ�������ɱ�ID
	private String TRUE_NAME;//��ʵ����
	private String Esn;//Ա�����
	private String Ewn;//���״̬
	private String Ias;//��ְ���
	private String Engage;//��Ƹ״̬
	private String Incomdate;//��ְʱ��
	private String Regular;//ת��ʱ��
	
	public String getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(String user_id) {
		USER_ID = user_id;
	}
	public String getTRUE_NAME() {
		return TRUE_NAME;
	}
	public void setTRUE_NAME(String true_name) {
		TRUE_NAME = true_name;
	}
	public String getEsn() {
		return Esn;
	}
	public void setEsn(String esn) {
		Esn = esn;
	}
	public String getEwn() {
		return Ewn;
	}
	public void setEwn(String ewn) {
		Ewn = ewn;
	}
	public String getIas() {
		return Ias;
	}
	public void setIas(String ias) {
		Ias = ias;
	}
	public String getEngage() {
		return Engage;
	}
	public void setEngage(String engage) {
		Engage = engage;
	}
	public String getIncomdate() {
		return Incomdate;
	}
	public void setIncomdate(String incomdate) {
		Incomdate = incomdate;
	}
	public String getRegular() {
		return Regular;
	}
	public void setRegular(String regular) {
		Regular = regular;
	}
	public String getCD_ID() {
		return CD_ID;
	}
	public void setCD_ID(String cd_id) {
		CD_ID = cd_id;
	}

}
