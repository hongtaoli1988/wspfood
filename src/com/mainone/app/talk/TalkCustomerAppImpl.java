package com.mainone.app.talk;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import uk.ltd.getahead.dwr.WebContext;
import uk.ltd.getahead.dwr.WebContextFactory;

import com.mainone.app.admin.LoginInfo;
import com.mainone.dao.IBaseServiceDao;
import com.mainone.model.sfa.SfaCustomerInfo;
import com.mainone.model.sfa.SfaLinkManInfo;
import com.mainone.util.DBRecord;
import com.mainone.util.PageBean;
import com.mainone.util.PropertyFileUtil;

/**
 * @author：zl
 * 类说明：约见客户记录
 */
public class TalkCustomerAppImpl implements TalkCustomerApp {
	
	private PageBean pageBean;
	private IBaseServiceDao iBaseServiceDaoA;
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public void setIBaseServiceDaoA(IBaseServiceDao baseServiceDaoA) {
		iBaseServiceDaoA = baseServiceDaoA;
	}
	
	/**
	 * 获取约见客户信息
	 * @param customerName
	 * @param employeeID
	 * @return
	 * @throws Exception
	 */
	public DBRecord getTalkCustomerInfo(String customerName, String employeeID) throws Exception {
		
		DBRecord dbr = null;
		String sql = " select * from SFA_Customer_info where CompanyName = '" +customerName+ "' and EmployeeID = '" +employeeID+ "'";
		try {
			dbr = iBaseServiceDaoA.selectRow(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dbr;
	}
	
	/**
	 * 查询约见联系人信息
	 * @param customerID
	 * @return 
	 * @throws Exception
	 */
	public DBRecord getTalkCusLinkman(String customerID) throws Exception {
		StringBuffer buf = new StringBuffer();
		buf.append(" select CompanyName,CompanyAdd,CustomerTel,CustomerComeID,State,CompanyType, CustomerID, ");
		buf.append(" AreaID,DepartID,CompanyID, LinkManName,LinkManMobile,LinkManID,LinkManSex  ");
		buf.append(" from V_customer_linkman a  ");
		buf.append(" where not exists (select 1 from V_customer_linkman where CustomerID=a.CustomerID and LinkManID>a.LinkManID) ");
		buf.append(" and CustomerID='").append(customerID).append("'");
		String sql = buf.toString();
		System.out.println("sql=="+sql.toString());
		return this.iBaseServiceDaoA.selectRow(sql);
	}
	
	
	/**
	 * 添加约见记录
	 * @param talkId
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public boolean addTalkArea(SfaTalkAreaInfo talkArea) throws Exception{
		boolean flag = false;
		StringBuffer buf = new StringBuffer();
		buf.append(" insert into SFA_Talk_Area").append(talkArea.getAreaId());
		buf.append(" (CustomerID,EmployeeID,DepartID,CompanyID,AreaID,LinkManID,TalkContent,");
		buf.append("TalkCount,OutTime,BackTime,CreateTime,SalePhase,Intent,NextVisit,Remark,");
		buf.append("Item1,Item2,Item3,Item4,Assess,C_Time,intDate,ContractCode,IfChengjiao,ChengjiaoTime) ");
		buf.append(" values('").append(talkArea.getCustomerId()).append("',");
		buf.append("'").append(talkArea.getEmployeeId()).append("',");
		buf.append("'").append(talkArea.getDepartId()).append("',");
		buf.append("'").append(talkArea.getCompanyId()).append("',");
		buf.append("'").append(talkArea.getAreaId()).append("',");
		buf.append("'").append(talkArea.getLinkManId()).append("',");
		buf.append("'").append(talkArea.getTalkContent()).append("',");
		buf.append("'").append(talkArea.getTalkCount()).append("',");
		buf.append("'").append(talkArea.getOutTime()).append("',");
		buf.append("'").append(talkArea.getBackTime()).append("',");
		buf.append("'").append(talkArea.getCreateTime()).append("',");
		buf.append("'").append(talkArea.getSalePhase()).append("',");
		buf.append("'").append(talkArea.getIntent()).append("',");
		buf.append("'").append(talkArea.getNextVisit()).append("',");
		buf.append("'").append(talkArea.getRemark()).append("',");
		buf.append("'").append(talkArea.getItem1()).append("',");
		buf.append("'").append(talkArea.getItem2()).append("',");
		buf.append("'").append(talkArea.getItem3()).append("',");
		buf.append("'").append(talkArea.getItem4()).append("',");
		buf.append("'").append(talkArea.getAssess()).append("',");
		buf.append("'").append(talkArea.getCTime()).append("',");
		buf.append("'").append(talkArea.getIntDate()).append("',");
		buf.append("'").append(talkArea.getContractCode()).append("',");
		buf.append("'").append(talkArea.getIfChengjiao()).append("',");
		//成交
		if("3".equals(talkArea.getSalePhase())){
			buf.append("'").append(talkArea.getChengjiaoTime()).append("')");
		}
		else{
			buf.append("").append(talkArea.getChengjiaoTime()).append(")");
		}
		String sql = buf.toString();
		System.out.println("sql="+sql.toString());
		try {
			flag=this.iBaseServiceDaoA.execUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block  
			e.printStackTrace(); 
		}
		
	   return  flag;
	    
	}
	
	/**
	 * 获取客户信息
	 * @param useFlowId
	 * @return
	 * @throws Exception
	 */
	public SfaCustomerInfo getSingleCustomerInfo(int customerId) throws Exception{
		
		SfaCustomerInfo sci = null;
		try {
			 sci = (SfaCustomerInfo)iBaseServiceDaoA.getObject(SfaCustomerInfo.class, customerId);
			} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return sci;
	}
	
	/**
	 * 修改保护客户
	 * @param sci
	 * @param remark
	 * @param scrId
	 */
	public boolean edit(SfaCustomerInfo sci) throws Exception{
		
		boolean b = true;
		try {
			iBaseServiceDaoA.updateObject(sci);
			} catch (RuntimeException e) {
				b = false;
				e.printStackTrace();
		}
		return b;
	}
	
	/**
	 * 添加联系人
	 */
	public boolean save(SfaLinkManInfo sli) throws Exception{
		
		boolean flag = true;
		try {
			iBaseServiceDaoA.saveObject(sli);
			} catch (RuntimeException e) {
				flag = false;
				e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 查询上次约见时间
	 * @param customerId
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public String getTalkTime(String customerId,String areaId) throws Exception {
		StringBuffer buf = new StringBuffer();
		buf.append(" select max(CreateTime) as CreateTime from SFA_Talk_Area").append(areaId);
		buf.append(" where CustomerID='").append(customerId).append("'");
		String sql = buf.toString();
		//System.out.println("sql=="+sql.toString());
		return this.iBaseServiceDaoA.selectOne(sql);
	}
	
}
