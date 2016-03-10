package com.mainone.app.admin;


import java.util.List;

import com.mainone.util.DBRecord;

/**
 * �������� 2012-12-4
 * ���� zl
 * �������� 
 */
public interface LoginApp {
	/**
	 * �õ���λ����Ӧ������ģ��
	 * @param userId �û�ID
	 * @return
	 */
	public List getAllModule(String positiionId);
	/**
	 * �û���֤
	 * @param userName �û���
	 * @param passWord ����
	 * @return
	 */
	public DBRecord checkUser(String userName);
	public DBRecord checkUser(String userName,String passWord);
	/**
	 * ��֤���û��Ƿ����
	 * @param userName �û���
	 * @param passWord ����
	 * @return true:���� false:������
	 */
	public boolean checkUserIsExict(String userName,String passWord);
	
	/**
	 * ��λ��֤
	 * @param userId �û�ID
	 * @return ��λ��Ϣ
	 */
	public DBRecord checkPosition(String userId);
	
	/**
	 * ��ɫ��֤
	 * @param POSITION_ID ��λid
	 * @return
	 */
	public DBRecord checkRole(String POSITION_ID);
	
	/**
	 * ��ѯ��˾�ֵ��
	 * @param CompanyID ��˾����
	 */
	public String searchCompanyInfo(String CompanyID);
	
	/**
	 * ���յ���ͳ��
	 * @return
	 */
	public String getGDT(String userId,String comId);
	
	/**
	 * �����ͻ���
	 * @return
	 */
	public String getProtectNum(String employeeID);
	

}
