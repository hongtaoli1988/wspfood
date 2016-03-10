package com.mainone.app.customer;

import com.mainone.model.sfa.SfaCustomerInfo;
import com.mainone.model.sfa.SfaLinkManInfo;
import com.mainone.util.DBRecord;

/**
 * @author��zzf
 * ����ʱ�䣺Jul 9, 2013 10:28:49 AM
 * ��˵�����ͻ�����
 */
public interface CustomerProtectApp {
	
	public DBRecord getSingleCustomerInfo(String customerName, String comId) throws Exception;
	
	public DBRecord checkCustomerTel(String customerTel, String comId) throws Exception;
	
	public int save(SfaCustomerInfo sci, SfaLinkManInfo sli) throws Exception;
	public boolean yanZhengCustomer(String customername)  throws Exception;
}
