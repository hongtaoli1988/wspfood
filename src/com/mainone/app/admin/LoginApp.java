package com.mainone.app.admin;


import java.util.List;

import com.mainone.util.DBRecord;

/**
 * 创建日期 2012-12-4
 * 作者 zl
 * 功能描述 
 */
public interface LoginApp {
	/**
	 * 得到岗位所对应的所有模块
	 * @param userId 用户ID
	 * @return
	 */
	public List getAllModule(String positiionId);
	/**
	 * 用户验证
	 * @param userName 用户名
	 * @param passWord 密码
	 * @return
	 */
	public DBRecord checkUser(String userName);
	public DBRecord checkUser(String userName,String passWord);
	/**
	 * 验证此用户是否存在
	 * @param userName 用户名
	 * @param passWord 密码
	 * @return true:存在 false:不存在
	 */
	public boolean checkUserIsExict(String userName,String passWord);
	
	/**
	 * 岗位验证
	 * @param userId 用户ID
	 * @return 岗位信息
	 */
	public DBRecord checkPosition(String userId);
	
	/**
	 * 角色验证
	 * @param POSITION_ID 岗位id
	 * @return
	 */
	public DBRecord checkRole(String POSITION_ID);
	
	/**
	 * 查询公司字典表
	 * @param CompanyID 公司名称
	 */
	public String searchCompanyInfo(String CompanyID);
	
	/**
	 * 今日到账统计
	 * @return
	 */
	public String getGDT(String userId,String comId);
	
	/**
	 * 保护客户数
	 * @return
	 */
	public String getProtectNum(String employeeID);
	

}
