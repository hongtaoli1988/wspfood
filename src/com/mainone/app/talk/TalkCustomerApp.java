package com.mainone.app.talk;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mainone.model.sfa.SfaCustomerInfo;
import com.mainone.model.sfa.SfaLinkManInfo;
import com.mainone.util.DBRecord;
import com.mainone.util.PageBean;

/**
 * @author��zl
 * ��˵����Լ���ͻ���¼
 */
public interface TalkCustomerApp {
	
	/**
	 * ��ȡԼ���ͻ���Ϣ
	 * @param customerName
	 * @param employeeID
	 * @return
	 * @throws Exception
	 */
	public DBRecord getTalkCustomerInfo(String customerName, String employeeID) throws Exception;
	
	/**
	 * ��ѯԼ����ϵ����Ϣ
	 * @param customerID
	 * @return
	 * @throws Exception
	 */
	public DBRecord getTalkCusLinkman(String customerID) throws Exception;
	
	/**
	 * ��ȡ�ͻ���Ϣ
	 * @param useFlowId
	 * @return
	 * @throws Exception
	 */
	public SfaCustomerInfo getSingleCustomerInfo(int customerId) throws Exception;
	
	/**
	 * �޸ı����ͻ�
	 * @param sci
	 * @param remark
	 * @param scrId
	 */
	public boolean edit(SfaCustomerInfo sci) throws Exception;
	
	/**
	 * �����ϵ��
	 */
	public boolean save(SfaLinkManInfo sli) throws Exception;
	
	/**
	 * ���Լ����¼
	 * @param talkId
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public boolean addTalkArea(SfaTalkAreaInfo talkArea) throws Exception;
	
	
	/**
	 * ��ѯ�ϴ�Լ��ʱ��
	 * @param customerId
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public String getTalkTime(String customerId,String areaId) throws Exception;
	



	
}
