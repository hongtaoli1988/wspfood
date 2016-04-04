package com.mainone.app.admin;


import java.util.List;

import com.mainone.util.DBRecord;

/**
 * �������� 2012-12-4
 * ���� zl
 * �������� 
 */
public interface LoginApp {
	//�����û����������
	public boolean updateTokenUser(String auth_token,String userName,String passWord);
	
	/**
	 * �û���֤ �����û���������
	 * @param userName �û���
	 * @param passWord ����
	 * @return
	 */
	public DBRecord checkUser(String userName,String passWord);
	/**
	 * ��֤���û��Ƿ���� �û���������
	 * @param userName �û���
	 * @param passWord ����
	 * @return true:���� false:������
	 */
	public DBRecord checkUserIsExist(String userName,String passWord);
	/**
	 * ��֤���û��Ƿ���� �û�id��token
	 * @param userName �û���
	 * @param passWord ����
	 * @return true:���� false:������
	 */
	public DBRecord checkUserIsLogin(String uid,String auth_key);
	
	/**
	 * ��ѯ��˾�ֵ��
	 * @param CompanyID ��˾����
	 */
	public String searchCompanyInfo(String CompanyID);
	
	

}
