package com.mainone.app.admin;


import java.util.List;

import com.mainone.util.DBRecord;

/**
 * 创建日期 2012-12-4
 * 作者 zl
 * 功能描述 
 */
public interface LoginApp {
	//根据用户名密码更新
	public boolean updateTokenUser(String auth_token,String userName,String passWord);
	
	/**
	 * 用户验证 根据用户名和密码
	 * @param userName 用户名
	 * @param passWord 密码
	 * @return
	 */
	public DBRecord checkUser(String userName,String passWord);
	/**
	 * 验证此用户是否存在 用户名和密码
	 * @param userName 用户名
	 * @param passWord 密码
	 * @return true:存在 false:不存在
	 */
	public DBRecord checkUserIsExist(String userName,String passWord);
	/**
	 * 验证此用户是否存在 用户id和token
	 * @param userName 用户名
	 * @param passWord 密码
	 * @return true:存在 false:不存在
	 */
	public DBRecord checkUserIsLogin(String uid,String auth_key);
	
	/**
	 * 查询公司字典表
	 * @param CompanyID 公司名称
	 */
	public String searchCompanyInfo(String CompanyID);
	
	

}
