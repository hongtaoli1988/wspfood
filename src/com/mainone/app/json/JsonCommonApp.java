package com.mainone.app.json;

import java.util.List;


/**
 * @author：zl
 * 类说明：json接口
 */
public interface JsonCommonApp {
	//食品链业务层方法
	//产品推荐
	public List getproductRecommendService(int currentPage, int pageSize, String uid,String condition );
	
	//我的保护客户列表 -- 销售代表和销售主管
   public List getProtectCustomerList(int currentPage, int pageSize, String userId, String comId, String cdId, String flag, String condition ) throws Exception;
	public List getHisTalkCustomerList(String areaId,String comId, String cdId, String customerID)throws Exception;
	
}
