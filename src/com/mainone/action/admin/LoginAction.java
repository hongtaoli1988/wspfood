package com.mainone.action.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import uk.ltd.getahead.dwr.WebContext;
import uk.ltd.getahead.dwr.WebContextFactory;

import com.mainone.app.admin.LoginApp;
import com.mainone.app.admin.LoginInfo;

import com.mainone.model.admin.ComInfo;
import com.mainone.model.admin.DepInfo;
import com.mainone.model.admin.PosiInfo;
import com.mainone.model.admin.RoleInfo;
import com.mainone.model.admin.UserInfo;
import com.mainone.util.BaseServletContext;
import com.mainone.util.DBRecord;

/**
 * �������� 2012-11-22
 * ���� zl
 * �������� 
 */
public class LoginAction extends BaseServletContext{
	private LoginApp loginApp;

	public void setLoginApp(LoginApp loginApp) {
		this.loginApp = loginApp;
	}  
	
	private String userName;
	private String password;

    /**
     * �����¼ҳ��
     */
	public String login()throws Exception{
		return "login";
	}
	
	
	/**
     * ������ҳ��
     */
	public String checkLogin()throws Exception{
		HttpSession session = request.getSession();
		List list = new ArrayList();
		if(userName==null){
			userName = "";
		}
		if(password==null){
			password = "";
		}
		if(userName.equals("")&&password.equals("")){
			return "login";
		}
		else{
			DBRecord dbr  = loginApp.checkUser(userName, password);
			String USER_ID = dbr.getString("USER_ID")==null?"-1":dbr.getString("USER_ID").toString();
			DBRecord dbr1 = loginApp.checkPosition(USER_ID);
			DBRecord dbr2 = loginApp.checkRole(dbr1.getString("Positionid")==null?"-1":dbr1.getString("Positionid").toString());
			this.getSession(dbr,dbr1,dbr2);
			session.setAttribute("userId", USER_ID);
			session.setAttribute("trueName", dbr.getString("TRUE_NAME")==null?"-1":dbr.getString("TRUE_NAME").toString());;
			String ROLE_NAME = dbr2.getString("ROLE_NAME")==null?"":dbr2.getString("ROLE_NAME").toString();
			if("���۴���".equals(ROLE_NAME)){
				return "main";
			}
			else if("���۾���".equals(ROLE_NAME)){
				return "main_jl";
			}
			else{
				String sygn = "1";
				String message = "�޷���Ȩ�ޣ�Ŀǰֻ��ͨ���۴�������Ȩ�ޣ�";
				request.setAttribute("message", message);
				request.setAttribute("sygn", sygn);
				return "info";
			}
		}
	}
	
	/**
	 * �˳�
	 * @return
	 */
	public String exit(){
		HttpSession session = this.request.getSession();
	    session.invalidate();
	    return "login";
	}
	public boolean checkUserIsExict(String userName,String passWord){
		boolean b  = loginApp.checkUserIsExict(userName,passWord);
		return b;
	
	}
	public void setUserName(String userName){
		this.userName = userName;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public String getUserName(){
		return userName;
	}
	public String getPassword(){
		return password;
	}
	
	//��¼��Ϣ
	public void getSession(DBRecord dbr,DBRecord dbr1,DBRecord dbr2){
	    UserInfo userInfo = new UserInfo();
		userInfo.setUSER_ID(dbr.getString("USER_ID")==null?"-1":dbr.getString("USER_ID").toString()); 
		userInfo.setCD_ID(dbr.getString("CD_ID")==null?"-1":dbr.getString("CD_ID").toString());
		userInfo.setTRUE_NAME(dbr.getString("TRUE_NAME")==null?"":dbr.getString("TRUE_NAME").toString());
		userInfo.setEngage(dbr.getString("Engage")==null?"":dbr.getString("Engage").toString());
		userInfo.setEsn(dbr.getString("Esn")==null?"":dbr.getString("Esn").toString());
		userInfo.setEwn(dbr.getString("Ewn")==null?"":dbr.getString("Ewn").toString());
		userInfo.setIas(dbr.getString("Ias")==null?"":dbr.getString("Ias").toString());
		userInfo.setIncomdate(dbr.getString("Incomdate")==null?"":dbr.getString("Incomdate").toString());
		userInfo.setRegular(dbr.getString("Regular")==null?"":dbr.getString("Regular").toString());
		
		ComInfo comInfo = new ComInfo();
		comInfo.setAreakey(loginApp.searchCompanyInfo(dbr.getString("COM_ID")==null?"-1":dbr.getString("COM_ID").toString()));
		comInfo.setBuilddata(dbr.getString("Builddata")==null?"":dbr.getString("Builddata").toString());
		comInfo.setCOM_CODE(dbr.getString("COM_CODE")==null?"":dbr.getString("COM_CODE").toString());
		comInfo.setCOM_ID(dbr.getString("COM_ID")==null?"-1":dbr.getString("COM_ID").toString());
		comInfo.setCOM_NAME(dbr.getString("COM_NAME")==null?"":dbr.getString("COM_NAME").toString());
		comInfo.setIspayoff(dbr.getString("Ispayoff")==null?"":dbr.getString("Ispayoff").toString());
		comInfo.setKotwal(dbr.getString("Kotwal")==null?"":dbr.getString("Kotwal").toString());
		comInfo.setKotwalid(dbr.getString("Kotwalid")==null?"-1":dbr.getString("Kotwalid").toString());
		comInfo.setMajordomo(dbr.getString("Majordomo")==null?"":dbr.getString("Majordomo").toString());
		comInfo.setMajordomoid(dbr.getString("Majordomoid")==null?"-1":dbr.getString("Majordomoid").toString());
		comInfo.setRate(dbr.getString("Rate")==null?"":dbr.getString("Rate").toString());
		comInfo.setSUB_COM_ID(dbr.getString("SUB_COM_ID")==null?"-1":dbr.getString("SUB_COM_ID").toString());
		comInfo.setTemplate(dbr.getString("Template")==null?"":dbr.getString("Template").toString());
		
		DepInfo depInfo = new DepInfo();
		depInfo.setDEP_CODE(dbr.getString("DEP_CODE")==null?"":dbr.getString("DEP_CODE").toString());
		depInfo.setDEP_ID(dbr.getString("DEP_ID")==null?"-1":dbr.getString("DEP_ID").toString());
		depInfo.setDEP_NAME(dbr.getString("DEP_NAME")==null?"":dbr.getString("DEP_NAME").toString());
		depInfo.setSn(dbr.getString("Sn")==null?"":dbr.getString("Sn").toString());
		
		PosiInfo posiInfo = new PosiInfo();
		posiInfo.setName(dbr1.getString("Name")==null?"":dbr1.getString("Name").toString());
		posiInfo.setNote(dbr1.getString("Note")==null?"":dbr1.getString("Note").toString());
		posiInfo.setPoscode(dbr1.getString("Poscode")==null?"":dbr1.getString("Poscode").toString());
		posiInfo.setPositionid(dbr1.getString("Positionid")==null?"-1":dbr1.getString("Positionid").toString());
		posiInfo.setSellflag(dbr1.getString("Sellflag")==null?"":dbr1.getString("Sellflag").toString());
		
		RoleInfo roleInfo = new RoleInfo();
		roleInfo.setROLE_ID(dbr2.getString("ROLE_ID")==null?"":dbr2.getString("ROLE_ID").toString());
		roleInfo.setROLE_NAME(dbr2.getString("ROLE_NAME")==null?"":dbr2.getString("ROLE_NAME").toString());
		
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.setUserInfo(userInfo);
		loginInfo.setComInfo(comInfo);
		loginInfo.setDepInfo(depInfo);
		loginInfo.setPosiInfo(posiInfo);
		loginInfo.setRoleInfo(roleInfo);
		HttpSession session = this.request.getSession();
		session.setAttribute("loginInfo", loginInfo);
	}
	
	
	/**
	 * �õ�ģ��ı���
	 * @param request HttpServletRequest����
	 * @param String ģ��ĳ�������Servlet����
	 * @return String  �磺 ��ǰλ�ã� Ȩ�޹��� >> �����û�
	 * ҳ�����ʱ <%=LoginApp.getModuleName(request,"UserSetServlet")%> 
	 */	
	public String getModuleName(String proName){
		String strRe="";
		//���û�������Ȩ��ģ����Ϣ
		WebContext webContext = WebContextFactory.get();
	    HttpSession session = webContext.getSession();
		ArrayList alist =(ArrayList)session.getAttribute("list");
		//�����ģ�� ��������ServletName��
		String programName; 
		if(proName==null)
			programName = webContext.getHttpServletRequest().getServletPath().substring(1);
		else
			programName = proName;
		//Debuger.println("�����ģ�� ������=="+programName);
		for(int i=0;i<alist.size();i++){
						Vector ve = (Vector)alist.get(i);
						DBRecord pRe = (DBRecord)ve.get(0);
						ArrayList childAlist = (ArrayList)ve.get(1);
						for(int j=0;j<childAlist.size();j++){
							DBRecord childRe = (DBRecord)childAlist.get(j);
							String M_PROGRAM = childRe.getString("PROGRAM_NAME").toLowerCase();
							//Debuger.println("ϵͳ��ģ�� ������=="+M_PROGRAM);
							if(M_PROGRAM.indexOf(programName.toLowerCase()) !=-1){
								strRe = "��ǰλ�ã� "+pRe.getString("MODULE_NAME").trim()+" >> "+childRe.getString("MODULE_NAME").trim();
								//Debuger.println(strRe);
								return strRe;
							}	
							else
							continue;
						}
		}
		return strRe;
		
	}
	
	
	public String geMName(String p_name){
		String m_name = "";
		try{
			m_name = this.getModuleName(p_name);
		}
		catch(Exception e){
			//e.printStackTrace();
		}
		return m_name;
	}
	 

	/**
	 * ��֤��
	 * 
	 * @param rand
	 * @return
	 */
	public String rand(String rand) {
		String message = "0";
		WebContext webContext = WebContextFactory.get();
		HttpSession session = webContext.getSession();
		String random = (String) session.getAttribute("random");
		if (!rand.equals(random)) {
			message = "1";// ��֤�����
			return message;
		}
		System.out.println("action random=" + random);
		return message;
	}
	
	 /**
     * ���յ���ͳ��
     */
	public String getGDT()throws Exception{
		WebContext webContext = WebContextFactory.get();
		HttpSession session = webContext.getSession();
		LoginInfo loginInfo = (LoginInfo)session.getAttribute("loginInfo");
		String userId = loginInfo.getUserInfo().getUSER_ID().trim();
		String comId = loginInfo.getComInfo().getCOM_ID().trim();
		String gdt = loginApp.getGDT(userId, comId);
		return gdt;
	}
	
	/**
     * �ͻ���ͳ��
     */
	public String getCusNum()throws Exception{
		WebContext webContext = WebContextFactory.get();
		HttpSession session = webContext.getSession();
		LoginInfo loginInfo = (LoginInfo)session.getAttribute("loginInfo");
		String userId = loginInfo.getUserInfo().getUSER_ID().trim();
		String cus = loginApp.getProtectNum(userId);
		return cus;
	}
	
	
	
	

}
