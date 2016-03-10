package com.mainone.app.customer;

import com.mainone.model.sfa.SfaCustomerInfo;
import com.mainone.model.sfa.SfaLinkManInfo;
import com.mainone.util.DBRecord;

/**
 * @author：zzf
 * 创建时间：Jul 9, 2013 10:28:49 AM
 * 类说明：客户保护
 */
public interface CustomerProtectApp {
	
	public DBRecord getSingleCustomerInfo(String customerName, String comId) throws Exception;
	
	public DBRecord checkCustomerTel(String customerTel, String comId) throws Exception;
	
	public int save(SfaCustomerInfo sci, SfaLinkManInfo sli) throws Exception;
	public boolean yanZhengCustomer(String customername)  throws Exception;
}
