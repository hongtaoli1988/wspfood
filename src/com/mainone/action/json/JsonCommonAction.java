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
	private String userName;//用户名
	private String passWord;//密码
	private String uid;//用户id
	private String auth_key;//唯一ID
	private int currentPage = 1;
	private int pageSize = 5;
	private String flag;
	private String condition = "";
	private List loginList = new ArrayList();
	private List productRecommendList = new ArrayList();
	private List protectList = new ArrayList();
	/**
     * 接口登陆方法验证
     */
	public String login(){
		HashMap map = new HashMap();
		//loginList = new ArrayList();
		userName = userName==null?"":userName;
		passWord = passWord==null?"":passWord;
		//用户名密码为空 返回登陆失败
		if(userName.equals("")&&passWord.equals("")){
			//-2表示用户名密码不能为空
			map.put("message", "-2");//调用传参错误
			map.put("flagLogin", "false");
		}
		else{
			//根据用户名和密码活得user对象
			DBRecord dbuser = loginApp.checkUserIsExist(userName, passWord);
			//用户名密码验证成功
			if(dbuser!=null && !dbuser.isEmpty()){
				//生成uuid 为用户授权token
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
					//生成token失败
					map.put("message", "-3");
					map.put("flagLogin", "false");
				}
				
			}
			//用户密码验证失败
			else{
				//-1 表示用户名密码错误
				map.put("message", "-1");//服务器验证失败错误
				map.put("flagLogin", "false");
			}
		}
		loginList.add(map);
		return "login_success";
	}
	//产品推荐
	public String productRecommend(){
		HashMap map = new HashMap();
		//验证用户是否登陆 根据uid和auth_key判定用户验证是否通过
		uid = uid==null?"":uid;
		auth_key = auth_key==null?"":auth_key;
		//用户id或token_auth为空 表示失效
		if(uid.equals("") || auth_key.equals("")){
			//-2表示token_auth为空、失效
			map.put("message", "-2");//调用传参错误
			map.put("flagLogin", "false");
		}
		else{
			//根据用户ID和auth_key活得user对象
			DBRecord dbuser = loginApp.checkUserIsLogin(uid, auth_key);
			//用户已登录
			if(dbuser!=null && !dbuser.isEmpty()){
				//根据用户ID、查询条件、查询产品推荐信息
				List listRsult = jsonCommonApp.getproductRecommendService(currentPage, pageSize, uid, condition);
				productRecommendList = listRsult;
				return "productRecommend_success";
			}
			//验证用户登陆失败
			else{
				map.put("message", "-1");//服务器验证失败错误
				map.put("flagLogin", "false");
			}
		}
		productRecommendList.add(map);
		return "productRecommend_success";
	}
	
	
	/* 返回单个值 */
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
