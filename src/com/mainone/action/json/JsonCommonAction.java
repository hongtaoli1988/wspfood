package com.mainone.action.json;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import uk.ltd.getahead.dwr.WebContext;
import uk.ltd.getahead.dwr.WebContextFactory;

import com.mainone.app.admin.LoginApp;
import com.mainone.app.admin.LoginInfo;
import com.mainone.app.json.JsonCommonApp;
import com.mainone.ehcache.MyCacheManager;
import com.mainone.model.admin.ComInfo;
import com.mainone.model.admin.DepInfo;
import com.mainone.model.admin.PosiInfo;
import com.mainone.model.admin.RoleInfo;
import com.mainone.model.admin.UserInfo;
import com.mainone.util.BaseServletContext;
import com.mainone.util.DBRecord;
import com.opensymphony.xwork2.ActionContext;
public class JsonCommonAction extends BaseServletContext {

	private JsonCommonApp jsonCommonApp;
	private LoginApp loginApp;
	
	private String userName;//用户名
	private String passWord;//密码
	private String userId;//用户id
	private String tokenID;//唯一ID
	private int currentPage;
	private int pageSize;
	private String flag;
	private String condition;
	private List loginList;
	private List talkList;
	private List protectList;

	
	
	/**
     * 进入主页面
     */
	public String login()throws Exception{
		HashMap map = new HashMap();
		loginList = new ArrayList();
		if(userName==null){
			userName = "";
		}
		if(passWord==null){
			passWord = "";
		}
		if(userName.equals("")&&passWord.equals("")){
			map.put("message", "-1");
			map.put("flagLogin", "false");
			loginList.add(map);
			return SUCCESS;
		}
		else{
			DBRecord dbr  = loginApp.checkUser(userName, passWord);
			if(dbr==null||dbr.size()==0){
				map.put("message", "-1");
				map.put("flagLogin", "false");
			}
			else{
				String USER_ID = dbr.getString("USER_ID")==null?"-1":dbr.getString("USER_ID").toString();
				String COM_ID = dbr.getString("COM_ID")==null?"-1":dbr.getString("COM_ID").toString();
				DBRecord dbr1 = loginApp.checkPosition(USER_ID);
				DBRecord dbr2 = loginApp.checkRole(dbr1.getString("Positionid")==null?"-1":dbr1.getString("Positionid").toString());
				this.setCache(dbr,dbr1,dbr2);
				String ROLE_NAME = dbr2.getString("ROLE_NAME")==null?"":dbr2.getString("ROLE_NAME").toString();
				if("销售代表".equals(ROLE_NAME)||"销售经理".equals(ROLE_NAME)){
					this.userId = USER_ID;
					this.searchUuid();
					map.put("userId", USER_ID);
					map.put("message", "0");
					map.put("flagLogin", "true");
					map.put("uname", dbr.getString("TRUE_NAME")==null?"-1":dbr.getString("TRUE_NAME").toString());
					map.put("currentyuemoney", loginApp.getGDT(USER_ID, COM_ID));
					map.put("customertotal", loginApp.getProtectNum(USER_ID));
					map.put("tokenID", this.tokenID);
				}
				else{
					map.put("message", "-2");
					map.put("flagLogin", "false");
				}
			}
			loginList.add(map);
			return SUCCESS;
		}
	}
	
	
	
	/* 返回单个值 */
	public String searchUuid() throws Exception{
		UUID uuid = UUID.randomUUID();  
		String s = uuid.toString();
		//String value1 = s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
		String value2 = s.replace("-", "");
		System.out.println("uuid2===="+value2);
		this.tokenID = value2;
		
		MyCacheManager.getInstance().put("wapsfa", userId, value2);
		//System.out.println("aaaa===="+MyCacheManager.getInstance().get("Test", "uuid"));
		return SUCCESS;
	}
	
	/**
	 * 查询保护客户
	 * @return
	 * @throws Exception
	 */
	public String searchProtectCustomer() throws Exception{
		
		if(userId==null){
			userId = "";
		}
		if(!this.checkUuid()){
			HashMap map = new HashMap();
			protectList = new ArrayList();
			map.put("message", "-1");
			map.put("flagLogin", "false");
			protectList.add(map);
			return SUCCESS;
		}
		
		LoginInfo loginInfo = (LoginInfo)MyCacheManager.getInstance().get("wapsfa", "loginInfo");
		System.out.println("loginInfo===="+loginInfo);
		if(loginInfo==null){
			HashMap map = new HashMap();
			protectList = new ArrayList();
			map.put("message", "-1");
			map.put("flagLogin", "false");
			protectList.add(map);
			return SUCCESS;
		}
	   
	    
	    String comId = loginInfo.getComInfo().getCOM_ID().trim();
	    String cdId = loginInfo.getUserInfo().getCD_ID().trim();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		protectList  = jsonCommonApp.getProtectCustomerList(currentPage, pageSize, userId, comId, cdId, flag, condition);
		System.out.println("protectList===="+protectList);
		return SUCCESS;
	}
	
	/**
	 * 编辑约见客户记录
	 * @return
	 * @throws Exception
	 */
	public String search() throws Exception{
		if(!this.checkUuid()){
			HashMap map = new HashMap();
			talkList = new ArrayList();
			map.put("message", "-1");
			map.put("flagLogin", "false");
			talkList.add(map);
			return SUCCESS;
		}
		
		talkList  = jsonCommonApp.getHisTalkCustomerList("7", "2","14","606548");
		System.out.println("talkList===="+talkList);
		return SUCCESS;
	}

	
	//登录信息
	public void setCache(DBRecord dbr,DBRecord dbr1,DBRecord dbr2){
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
		MyCacheManager.getInstance().put("wapsfa", "loginInfo", loginInfo);
	}
	
	 
	//检查用户是否登陆	
	public boolean checkUuid(){
		try{
			String token_ID = MyCacheManager.getInstance().get("wapsfa", userId)==null?"": MyCacheManager.getInstance().get("wapsfa", userId).toString();
			System.out.println("userId======"+userId);
			System.out.println("tokenID======"+tokenID);
			System.out.println("token_ID======"+token_ID);
			
			if(!token_ID.equals(tokenID)){
				return false;
			}
			else{
				return true;	
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public void setJsonCommonApp(JsonCommonApp jsonCommonApp) {
		this.jsonCommonApp = jsonCommonApp;
	}

	public List getTalkList() {
		return talkList;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setLoginApp(LoginApp loginApp) {
		this.loginApp = loginApp;
	}



	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String getTokenID() {
		return tokenID;
	}



	public void setTokenID(String tokenID) {
		this.tokenID = tokenID;
	}


	public String getPassWord() {
		return passWord;
	}



	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}



	public List getProtectList() {
		return protectList;
	}

	
	public String getFlag() {
		return flag;
	}



	public void setFlag(String flag) {
		this.flag = flag;
	}



	public String getCondition() {
		return condition;
	}



	public void setCondition(String condition) {
		this.condition = condition;
	}



	public int getCurrentPage() {
		return currentPage;
	}



	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}



	public int getPageSize() {
		return pageSize;
	}



	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public List getLoginList() {
		return loginList;
	}

}
