package com.mainone.common;

import java.util.ArrayList;
import java.util.List;

import com.mainone.util.DBRecord;

/**
 * @author：zzf
 * 创建时间：Dec 27, 2012 5:03:20 PM
 * 类说明：通用方法接口
 */
public interface CommonApp {
	
	/**
	 * 根据positionId获取客户保护typeId
	 * typeId=7 --> 高级客户主任
	 * typeId=8 --> 客户主任
	 * typeId=9 --> 客户代表
	 * typeId=10 --> 助理客户代表
	 * @param positionId
	 * @return
	 * @throws Exception
	 */
	public String getCustomerProtectTypeId(String positionId)throws Exception;
	
	/**
	 * 根据typeId、companyId获取销售代表保护设置信息
	 * @param typeId
	 * @param companyId
	 * @return
	 * @throws Exception
	 */
	public DBRecord getCustomerProtectInfo(String typeId, String companyId)throws Exception;
	
	/**
	 * 获取经理保护设置信息
	 * @param cdId
	 * @return
	 * @throws Exception
	 */
	public DBRecord getManagerCustomerProtectInfo(String cdId)throws Exception;
	
	/**
	 * 根据depId、comId获取cdId
	 * @param depId
	 * @param comId
	 * @return
	 * @throws Exception
	 */
	public String getCdId(String depId, String comId)throws Exception;
	
	/**
	 * 获得用户信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public DBRecord getUserInfo(String userId)throws Exception;
	
	/**
	 * 获取客户负责人列表
	 * @param comId
	 * @param depId
	 * @param ias 0：在职		1：离职		""：所有	
	 * @return
	 */
	public ArrayList<DBRecord> getEmployeeList(String comId, String cdId, String ias) throws Exception;
	
	/**
	 * 获取客户负责人列表，不包含离职员工 和 已经删除部门下的员工 -- for分配客户
	 * @param comId
	 * @param depId
	 * @return
	 */
	public ArrayList<DBRecord> getEmployeeListNormal(String comId, String cdId) throws Exception;
	
	/**
	 * 获取客户负责人下拉框
	 * @param comId
	 * @param depId
	 * @param employeeId
	 * @param ias	0：在职	1：离职
	 * @return
	 * @throws Exception
	 */
	public String getEmployeeSelect(String comId, String cdId, String employeeId, String ias) throws Exception;
	
	/**
	 * 获取客户负责人下拉框 - 所有包括在职、离职
	 * @param comId
	 * @param depId
	 * @param employeeId
	 * @return
	 */
	public String getEmployeeSelect(String comId, String cdId, String employeeId) throws Exception;
	
	/**
	 * 获取部门下拉框
	 * @param comId
	 * @param cdId
	 * @return
	 */
	public String getDepSelect(String comId, String cdId) throws Exception;
	
	/**
	 * 获取部门下拉框，不包含删除的部门 -- for分配客户
	 * @param comId
	 * @param depId
	 * @return
	 */
	public String getDepSelectNormal(String comId, String cdId) throws Exception;
	
	/**
	 * 获取大区下拉框
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public String getAreaSelect(String areaId) throws Exception;
	
	/**
	 * 获取公司下拉框
	 * @param areaId
	 * @param comId
	 * @return
	 * @throws Exception
	 */
	public String getComSelect(String areaId, String comId) throws Exception;
	
	/**
	 * 获取公司下拉框
	 * @param comId
	 * @return
	 * @throws Exception
	 */
	public String getComSelect(String comId) throws Exception;
	
	/**
	 * 根据CD_ID得DEP_ID
	 * @param cdId
	 * @return
	 */
	public String getDepId(String cdId);
	
	public DBRecord getDeptInfo(String depId) throws Exception;
	
	
}
