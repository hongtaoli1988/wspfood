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
public class PosiInfo implements Serializable{
    
    private String Positionid;//��λID
	private String Name;//��λ����
	private String Poscode;//��λ���
	private String Note;//ֱ���ϼ�
	private String Sellflag;//�Ƿ����۸�λ
	
	public String getPositionid() {
		return Positionid;
	}
	public void setPositionid(String positionid) {
		Positionid = positionid;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPoscode() {
		return Poscode;
	}
	public void setPoscode(String poscode) {
		Poscode = poscode;
	}
	public String getNote() {
		return Note;
	}
	public void setNote(String note) {
		Note = note;
	}
	public String getSellflag() {
		return Sellflag;
	}
	public void setSellflag(String sellflag) {
		Sellflag = sellflag;
	}
	

}
