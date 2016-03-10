package com.mainone.app.json;

import java.util.List;


/**
 * @author��zl
 * ��˵����json�ӿ�
 */
public interface JsonCommonApp {
	
	/**
	  * �ҵı����ͻ��б� -- ���۴������������
	  * @param currentPage
	  * @param pageSize
	  * @param userId
	  * @param comId
	  * @param cdId
	  * @param flag
	  * @param condition
	  * @return
	  * @throws Exception
	  */
   public List getProtectCustomerList(int currentPage, int pageSize, String userId, String comId, String cdId, String flag, String condition ) throws Exception;

	/**
	 * �ͻ���ʷԼ��
	 * @param areaId
	 * @param comId
	 * @param cdId
	 * @param customerID
	 * @param talkId
	 * @return
	 * @throws Exception
	 */
	public List getHisTalkCustomerList(String areaId,String comId, String cdId, String customerID)throws Exception;
	
}
