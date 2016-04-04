package com.mainone.app.json;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import uk.ltd.getahead.dwr.WebContext;
import uk.ltd.getahead.dwr.WebContextFactory;

import com.mainone.dao.IBaseServiceDao;
import com.mainone.ehcache.MyCacheManager;
import com.mainone.util.DBRecord;
import com.mainone.util.PageBean;
import com.mainone.util.PropertyFileUtil;

/**
 * @author：zl
 * 类说明：json接口
 */
public class JsonCommonAppImpl implements JsonCommonApp {
	
	private IBaseServiceDao iBaseServiceDaoA;
	public void setIBaseServiceDaoA(IBaseServiceDao baseServiceDaoA) {
		iBaseServiceDaoA = baseServiceDaoA;
	}
	
	 /**
	  * 我的保护客户列表 -- 销售代表和销售主管
	  * @param currentPage
	  * @param pageSize
	  * @param userId
	  * @param comId
	  * @param cdId
	  * @param flag
	  * @param condition
	  * @return
	  * @throws Exception
	  */
    public List getProtectCustomerList(int currentPage, int pageSize, String userId, String comId, String cdId, String flag, String condition ) throws Exception {
        List list = null;
        try {
        	
            StringBuffer sql1 = new StringBuffer();
            sql1.append(" select sfi.CustomerID, sfi.AreaID, sfi.AddDate, sfi.CompanyName, sfi.CompanyAdd, " +
            		"sfi.CustomerComeID, sfi.LastUseTime, sfi.State, sfi.UseNum, sfi.PortectBeginTime, " +
            		"sfi.PortectEndTime, sfi.IfBaifang, sfi.IfGenjin, sfi.PortectState, sfi.MyProtest, " +
            		"sfi.IfCombine,datediff(dd,getdate(),sfi.PortectEndTime) as remainPortectDays from SFA_Customer_info sfi where sfi.EmployeeID = ").append(userId);

            sql1.append(" and sfi.CompanyID = " + comId);
            sql1.append(" and sfi.DepartID = " + cdId);
            

            if (condition != null && !"".equals(condition)) {
                sql1.append(" and (sfi.CompanyName like '%" + condition.trim().replaceAll("'", "''") + "%' or sfi.CompanyNameAlias like '%" + condition.trim().replaceAll("'", "''") + "%')");
              
            }
            
            sql1.append(" order by sfi.AddDate desc");
            //System.out.println("sql1====="+sql1.toString());
            
            list = iBaseServiceDaoA.findBySqlConditionParmFenYe(currentPage, pageSize, sql1.toString());
            //System.out.println("list===="+list);

        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return list;
    }
    
	
	/**
	 * 客户历史约见
	 * @param areaId
	 * @param comId
	 * @param cdId
	 * @param customerID
	 * @param talkId
	 * @return
	 * @throws Exception
	 */
	public List getHisTalkCustomerList(String areaId,String comId, String cdId, String customerID)throws Exception{
		StringBuffer sql = new StringBuffer();
		List array = null;
		sql.append(" select talk.*,sci.CompanyName,mui.TRUE_NAME,mdi.DEP_NAME from SFA_Talk_Area").append(areaId).append(" talk inner join SFA_Customer_info sci ");
		sql.append(" on talk.CustomerID=sci.CustomerID  join ").append(PropertyFileUtil.getProperty("dbName1")).append(" MO_USER_INFO mui on talk.EmployeeID = mui.user_id join ");
		sql.append(PropertyFileUtil.getProperty("dbName1")).append(" MO_COM_DEP_INFO mcdi on talk.DepartID = mcdi.CD_ID join ");
		sql.append(PropertyFileUtil.getProperty("dbName1")).append(" MO_DEPARTMENT_INFO mdi on mcdi.DEP_ID = mdi.DEP_ID ");
		sql.append(" where talk.CustomerID='").append(customerID).append("'");
	    sql.append(" order by talk.C_Time desc");
	    
		try {
			System.out.println("sql====="+sql.toString());
			array = (ArrayList)iBaseServiceDaoA.getSqlJdbc(sql.toString());
		   
		    
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		
		return array;
	}
	//产品推荐
	public List getproductRecommendService(int currentPage, int pageSize,
			String uid, String condition) {
		 List listResult = new ArrayList();
		 StringBuffer sql = new StringBuffer();
		 sql.append(" select u.uid,u.username,g.id as good_id,g.title,g.score,g.addtime,g.address,g.picture,g.summary,g.scan,'100' as mylike,g.comments,g.dynamic from user u ");
		 sql.append(" join goods g on u.uid = g.uid where 1=1");
		 if (uid!= null && !"".equals(uid)) {
			 sql.append(" and u.uid = '").append(uid).append("'");
           
         }
		 if (condition!= null && !"".equals(condition)) {
			 sql.append(" and (g.title like '%" + condition.trim().replaceAll("'", "''") + "%'");
           
         }
		 sql.append(" order by g.updatetime desc");
		 System.out.println(sql.toString());
		 listResult = iBaseServiceDaoA.findBySqlConditionParmFenYe(currentPage, pageSize, sql.toString());
		 return listResult;
	}
}
