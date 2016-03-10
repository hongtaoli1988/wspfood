package com.mainone.app.talk;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mainone.model.sfa.SfaCustomerInfo;
import com.mainone.model.sfa.SfaLinkManInfo;
import com.mainone.util.DBRecord;
import com.mainone.util.PageBean;

/**
 * @author：zl
 * 类说明：约见客户记录
 */
public interface TalkCustomerApp {
	
	/**
	 * 获取约见客户信息
	 * @param customerName
	 * @param employeeID
	 * @return
	 * @throws Exception
	 */
	public DBRecord getTalkCustomerInfo(String customerName, String employeeID) throws Exception;
	
	/**
	 * 查询约见联系人信息
	 * @param customerID
	 * @return
	 * @throws Exception
	 */
	public DBRecord getTalkCusLinkman(String customerID) throws Exception;
	
	/**
	 * 获取客户信息
	 * @param useFlowId
	 * @return
	 * @throws Exception
	 */
	public SfaCustomerInfo getSingleCustomerInfo(int customerId) throws Exception;
	
	/**
	 * 修改保护客户
	 * @param sci
	 * @param remark
	 * @param scrId
	 */
	public boolean edit(SfaCustomerInfo sci) throws Exception;
	
	/**
	 * 添加联系人
	 */
	public boolean save(SfaLinkManInfo sli) throws Exception;
	
	/**
	 * 添加约见记录
	 * @param talkId
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public boolean addTalkArea(SfaTalkAreaInfo talkArea) throws Exception;
	
	
	/**
	 * 查询上次约见时间
	 * @param customerId
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public String getTalkTime(String customerId,String areaId) throws Exception;
	



	
}
