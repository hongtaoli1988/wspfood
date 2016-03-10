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
public class PosiInfo implements Serializable{
    
    private String Positionid;//岗位ID
	private String Name;//岗位名称
	private String Poscode;//岗位编号
	private String Note;//直属上级
	private String Sellflag;//是否销售岗位
	
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
