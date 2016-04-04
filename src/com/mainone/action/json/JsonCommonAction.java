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
import com.mainone.app.json.JsonCommonApp;
import com.mainone.ehcache.MyCacheManager;
import com.mainone.util.BaseServletContext;
import com.mainone.util.DBRecord;
import com.opensymphony.xwork2.ActionContext;
public class JsonCommonAction extends BaseServletContext {
	private JsonCommonApp jsonCommonApp;
	private LoginApp loginApp;
	private String userName;//�û���
	private String passWord;//����
	private String uid;//�û�id
	private String auth_key;//ΨһID
	private int currentPage = 1;
	private int pageSize = 5;
	private String flag;
	private String condition = "";
	private List loginList = new ArrayList();
	private List productRecommendList = new ArrayList();
	private List protectList = new ArrayList();
	/**
     * �ӿڵ�½������֤
     */
	public String login(){
		HashMap map = new HashMap();
		//loginList = new ArrayList();
		userName = userName==null?"":userName;
		passWord = passWord==null?"":passWord;
		//�û�������Ϊ�� ���ص�½ʧ��
		if(userName.equals("")&&passWord.equals("")){
			//-2��ʾ�û������벻��Ϊ��
			map.put("message", "-2");//���ô��δ���
			map.put("flagLogin", "false");
		}
		else{
			//�����û�����������user����
			DBRecord dbuser = loginApp.checkUserIsExist(userName, passWord);
			//�û���������֤�ɹ�
			if(dbuser!=null && !dbuser.isEmpty()){
				//����uuid Ϊ�û���Ȩtoken
				String auth_token = UUID.randomUUID().toString().replaceAll("-", "");
				boolean b = loginApp.updateTokenUser(auth_token, userName, passWord);
				if(b){
					map.put("flagLogin", "true");
					map.put("message", "0");
					map.put("auth_key", auth_token);
					map.put("userId", dbuser.getString("uid"));
					map.put("role", dbuser.getString("role"));
				}
				else{
					//����tokenʧ��
					map.put("message", "-3");
					map.put("flagLogin", "false");
				}
				
			}
			//�û�������֤ʧ��
			else{
				//-1 ��ʾ�û����������
				map.put("message", "-1");//��������֤ʧ�ܴ���
				map.put("flagLogin", "false");
			}
		}
		loginList.add(map);
		return "login_success";
	}
	//��Ʒ�Ƽ�
	public String productRecommend(){
		HashMap map = new HashMap();
		//��֤�û��Ƿ��½ ����uid��auth_key�ж��û���֤�Ƿ�ͨ��
		uid = uid==null?"":uid;
		auth_key = auth_key==null?"":auth_key;
		//�û�id��token_authΪ�� ��ʾʧЧ
		if(uid.equals("") || auth_key.equals("")){
			//-2��ʾtoken_authΪ�ա�ʧЧ
			map.put("message", "-2");//���ô��δ���
			map.put("flagLogin", "false");
		}
		else{
			//�����û�ID��auth_key���user����
			DBRecord dbuser = loginApp.checkUserIsLogin(uid, auth_key);
			//�û��ѵ�¼
			if(dbuser!=null && !dbuser.isEmpty()){
				//�����û�ID����ѯ��������ѯ��Ʒ�Ƽ���Ϣ
				List listRsult = jsonCommonApp.getproductRecommendService(currentPage, pageSize, uid, condition);
				productRecommendList = listRsult;
				return "productRecommend_success";
			}
			//��֤�û���½ʧ��
			else{
				map.put("message", "-1");//��������֤ʧ�ܴ���
				map.put("flagLogin", "false");
			}
		}
		productRecommendList.add(map);
		return "productRecommend_success";
	}
	
	
	/* ���ص���ֵ */
	public String searchUuid() throws Exception{
		UUID uuid = UUID.randomUUID();  
		String s = uuid.toString();
		//String value1 = s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
		String value2 = s.replace("-", "");
		System.out.println("uuid2===="+value2);
		//this.tokenID = value2;
		
		//MyCacheManager.getInstance().put("wapsfa", userId, value2);
		//System.out.println("aaaa===="+MyCacheManager.getInstance().get("Test", "uuid"));
		return SUCCESS;
	}
	public JsonCommonApp getJsonCommonApp() {
		return jsonCommonApp;
	}
	public void setJsonCommonApp(JsonCommonApp jsonCommonApp) {
		this.jsonCommonApp = jsonCommonApp;
	}
	public LoginApp getLoginApp() {
		return loginApp;
	}
	public void setLoginApp(LoginApp loginApp) {
		this.loginApp = loginApp;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getAuth_key() {
		return auth_key;
	}
	public void setAuth_key(String auth_key) {
		this.auth_key = auth_key;
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
	public List getLoginList() {
		return loginList;
	}
	public void setLoginList(List loginList) {
		this.loginList = loginList;
	}
	public List getProductRecommendList() {
		return productRecommendList;
	}
	public void setProductRecommendList(List productRecommendList) {
		this.productRecommendList = productRecommendList;
	}
	public List getProtectList() {
		return protectList;
	}
	public void setProtectList(List protectList) {
		this.protectList = protectList;
	}
	
	
}
