package com.mainone.common;

import java.util.ArrayList;
import java.util.List;

import com.mainone.util.DBRecord;

/**
 * @author��zzf
 * ����ʱ�䣺Dec 27, 2012 5:03:20 PM
 * ��˵����ͨ�÷����ӿ�
 */
public interface CommonApp {
	
	/**
	 * ����positionId��ȡ�ͻ�����typeId
	 * typeId=7 --> �߼��ͻ�����
	 * typeId=8 --> �ͻ�����
	 * typeId=9 --> �ͻ�����
	 * typeId=10 --> ����ͻ�����
	 * @param positionId
	 * @return
	 * @throws Exception
	 */
	public String getCustomerProtectTypeId(String positionId)throws Exception;
	
	/**
	 * ����typeId��companyId��ȡ���۴�����������Ϣ
	 * @param typeId
	 * @param companyId
	 * @return
	 * @throws Exception
	 */
	public DBRecord getCustomerProtectInfo(String typeId, String companyId)throws Exception;
	
	/**
	 * ��ȡ������������Ϣ
	 * @param cdId
	 * @return
	 * @throws Exception
	 */
	public DBRecord getManagerCustomerProtectInfo(String cdId)throws Exception;
	
	/**
	 * ����depId��comId��ȡcdId
	 * @param depId
	 * @param comId
	 * @return
	 * @throws Exception
	 */
	public String getCdId(String depId, String comId)throws Exception;
	
	/**
	 * ����û���Ϣ
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public DBRecord getUserInfo(String userId)throws Exception;
	
	/**
	 * ��ȡ�ͻ��������б�
	 * @param comId
	 * @param depId
	 * @param ias 0����ְ		1����ְ		""������	
	 * @return
	 */
	public ArrayList<DBRecord> getEmployeeList(String comId, String cdId, String ias) throws Exception;
	
	/**
	 * ��ȡ�ͻ��������б���������ְԱ�� �� �Ѿ�ɾ�������µ�Ա�� -- for����ͻ�
	 * @param comId
	 * @param depId
	 * @return
	 */
	public ArrayList<DBRecord> getEmployeeListNormal(String comId, String cdId) throws Exception;
	
	/**
	 * ��ȡ�ͻ�������������
	 * @param comId
	 * @param depId
	 * @param employeeId
	 * @param ias	0����ְ	1����ְ
	 * @return
	 * @throws Exception
	 */
	public String getEmployeeSelect(String comId, String cdId, String employeeId, String ias) throws Exception;
	
	/**
	 * ��ȡ�ͻ������������� - ���а�����ְ����ְ
	 * @param comId
	 * @param depId
	 * @param employeeId
	 * @return
	 */
	public String getEmployeeSelect(String comId, String cdId, String employeeId) throws Exception;
	
	/**
	 * ��ȡ����������
	 * @param comId
	 * @param cdId
	 * @return
	 */
	public String getDepSelect(String comId, String cdId) throws Exception;
	
	/**
	 * ��ȡ���������򣬲�����ɾ���Ĳ��� -- for����ͻ�
	 * @param comId
	 * @param depId
	 * @return
	 */
	public String getDepSelectNormal(String comId, String cdId) throws Exception;
	
	/**
	 * ��ȡ����������
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public String getAreaSelect(String areaId) throws Exception;
	
	/**
	 * ��ȡ��˾������
	 * @param areaId
	 * @param comId
	 * @return
	 * @throws Exception
	 */
	public String getComSelect(String areaId, String comId) throws Exception;
	
	/**
	 * ��ȡ��˾������
	 * @param comId
	 * @return
	 * @throws Exception
	 */
	public String getComSelect(String comId) throws Exception;
	
	/**
	 * ����CD_ID��DEP_ID
	 * @param cdId
	 * @return
	 */
	public String getDepId(String cdId);
	
	public DBRecord getDeptInfo(String depId) throws Exception;
	
	
}
