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
public class ComInfo implements Serializable{
    
	private String COM_ID;//��˾ID
	private String COM_CODE;//��˾���
	private String COM_NAME;//��˾����
	private String SUB_COM_ID;//���ӹ�˾��ʶ
	private String Rate;//��˾����
	private String Areakey;//����
	private String Builddata;//����ʱ��
	private String Kotwal;//����������
	private String Kotwalid;//�����ܼ�Id
	private String Template;//��˾ģ����
	private String Ispayoff;//�Ƿ�ӯ��
	private String Majordomo;//��˾�ܼ�
	private String Majordomoid;//��˾�ܼ�Id
	
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
