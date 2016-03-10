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
public class UserInfo implements Serializable{
    
    private String USER_ID;//员工ID
    private String CD_ID;//公司部门组织信息对照主干表ID
	private String TRUE_NAME;//真实姓名
	private String Esn;//员工编号
	private String Ewn;//审核状态
	private String Ias;//在职类别
	private String Engage;//受聘状态
	private String Incomdate;//入职时间
	private String Regular;//转正时间
	
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
