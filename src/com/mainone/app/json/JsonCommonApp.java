package com.mainone.app.json;

import java.util.List;


/**
 * @author��zl
 * ��˵����json�ӿ�
 */
public interface JsonCommonApp {
	//ʳƷ��ҵ��㷽��
	//��Ʒ�Ƽ�
	public List getproductRecommendService(int currentPage, int pageSize, String uid,String condition );
	
	//�ҵı����ͻ��б� -- ���۴������������
   public List getProtectCustomerList(int currentPage, int pageSize, String userId, String comId, String cdId, String flag, String condition ) throws Exception;
	public List getHisTalkCustomerList(String areaId,String comId, String cdId, String customerID)throws Exception;
	
}
