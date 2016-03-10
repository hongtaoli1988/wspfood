package com.mainone.app.talk;

import javax.servlet.http.HttpServletRequest;

import com.mainone.dao.IBaseServiceDao;
import com.mainone.util.DBRecord;
import com.mainone.util.PageBean;
import com.mainone.util.PropertyFileUtil;

/**
 * @author：ly
 * 类说明：拜访客户点评
 */
public class VisitReviewAppImpl implements VisitReviewApp {
	
	private PageBean pageBean;
	private IBaseServiceDao iBaseServiceDaoA;
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public void setIBaseServiceDaoA(IBaseServiceDao baseServiceDaoA) {
		iBaseServiceDaoA = baseServiceDaoA;
	}
	
	public PageBean searchVisit(int currentPage, int pageSize,String areaId,String comId,String cdId,String customerName,
			String startTime, String endTime,String flagState, HttpServletRequest request) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		StringBuffer sql1 = new StringBuffer();
		
		sql.append(" select talk.*,sci.CompanyName,sci.State,mui.TRUE_NAME,datediff(dd,getdate(),sci.PortectEndTime) as remainPortectDays,sci.EXTEND_TIMES,sci.EmployeeID as Cus_EmployeeID from SFA_Talk_Area").append(areaId).append(" talk inner join SFA_Customer_info sci ");
		sql.append(" on talk.CustomerID=sci.CustomerID  join ").append(PropertyFileUtil.getProperty("dbName1")).append(" MO_USER_INFO mui on talk.EmployeeID = mui.user_id where 1=1 ");
		
		sql1.append(" select count(*) from SFA_Talk_Area").append(areaId).append(" talk inner join SFA_Customer_info sci ");
		sql1.append(" on talk.CustomerID=sci.CustomerID  join ").append(PropertyFileUtil.getProperty("dbName1")).append(" MO_USER_INFO mui on talk.EmployeeID = mui.user_id where 1=1 ");
		
		sql.append(" and talk.CompanyID = "+comId);
		sql1.append(" and talk.CompanyID = "+comId);
		sql.append(" and talk.DepartID = "+cdId);
		sql1.append(" and talk.DepartID = "+cdId);
	
		if(null != customerName && !"".equals(customerName)){
			sql.append(" and sci.CompanyName like '%" + customerName.trim().replaceAll("'", "''") + "%'");
			sql1.append(" and sci.CompanyName like '%" + customerName.trim().replaceAll("'", "''") + "%'");
		}
	  
		if(null != startTime && !"".equals(startTime)){
	        sql.append(" and talk.CreateTime >= '" + startTime.trim().replaceAll("'", "''") + "'");
	        sql1.append(" and talk.CreateTime >= '" + startTime.trim().replaceAll("'", "''") + "'");
	    }
	    if(null != endTime && !"".equals(endTime)){
	    	sql.append(" and talk.CreateTime <= '" + endTime.trim().replaceAll("'", "''") + " 23:59:59' ");
	    	sql1.append(" and talk.CreateTime <= '" + endTime.trim().replaceAll("'", "''") + " 23:59:59' ");
	    }
		
	    if(flagState.equals("0")){
	    	sql.append(" and talk.Assess = '-2'");
	    	sql1.append(" and talk.Assess = '-2'");
	    }
	    else if(flagState.equals("1")){
	    	sql.append(" and talk.Assess <> '-2'");
	    	sql1.append(" and talk.Assess <> '-2'");
	    }
	    
	    sql.append(" order by talk.CreateTime desc");
	    System.out.println("sql=="+sql.toString());
	    System.out.println("sql1=="+sql1.toString());
	    
		try {
		    pageBean.setPageBeanForSql(currentPage, pageSize, sql.toString(), sql1.toString());
		    
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		
		return pageBean;
	}
	
	
	/**
	 * 查询约见信息
	 * @param areaId
	 * @param talkId
	 * @return
	 * @throws Exception
	 */
	public DBRecord getTalkArea(String areaId,String talkId) throws Exception {
		StringBuffer buf = new StringBuffer();
		buf.append(" select talk.*,sci.CompanyName,sci.CompanyAdd,sci.CustomerTel,sci.CustomerComeID,sci.State,sci.CompanyType,sci.MisCustomerID,sci.Mis2CustomerID,sci.Mis3CustomerID, ");
		buf.append(" sli.LinkManName,sli.LinkManTel,sli.LinkManMobile,sli.LinkManType,sli.Item1 as postname,sli.LinkManSex,sli.LinkManEmail ");
		buf.append(" from SFA_Talk_Area").append(areaId).append(" talk");
		buf.append(" inner join SFA_Customer_info sci on talk.CustomerID=sci.CustomerID ");
		buf.append(" inner join SFA_LinkMan_info sli on talk.LinkManID=sli.LinkManID ");
		buf.append(" where talk.ID='").append(talkId).append("'");
		String sql = buf.toString();
		System.out.println("sql=="+sql.toString());
		return this.iBaseServiceDaoA.selectRow(sql);
	}

	/**
	 * 修改点评约见记录
	 * @param talkId
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public boolean updateDpTalkArea(SfaTalkAreaInfo talkArea) throws Exception{
		boolean flag = false;
		StringBuffer buf = new StringBuffer();
		buf.append(" update SFA_Talk_Area").append(talkArea.getAreaId());
		buf.append(" set Assess='").append(talkArea.getAssess()).append("',");
		buf.append("Item1='").append(talkArea.getItem1()).append("',");
		buf.append("Item2='").append(talkArea.getItem2()).append("',");
		buf.append("Item3='").append(talkArea.getItem3()).append("',");
		buf.append("OperatorName='").append(talkArea.getOperatorName()).append("',");
		buf.append("OperateTime='").append(talkArea.getOperateTime()).append("'");
		buf.append(" where ID='").append(talkArea.getId()).append("'");
		
		String sql = buf.toString();
		try {
			flag=this.iBaseServiceDaoA.execUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block  
			e.printStackTrace();
		}
		
	   return  flag;
	    
	}
}
