package com.mainone.app.json;

import java.util.List;


/**
 * @author：zl
 * 类说明：json接口
 */
public interface JsonCommonApp {
	
	/**
	  * 我的保护客户列表 -- 销售代表和销售主管
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
	 * 客户历史约见
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
