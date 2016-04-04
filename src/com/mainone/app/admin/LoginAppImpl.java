package com.mainone.app.admin;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import uk.ltd.getahead.dwr.WebContextFactory;

import com.mainone.dao.IBaseServiceDao;

import com.mainone.util.DBRecord;
import com.mainone.util.TimeTools;

/**
 * 创建日期 2012-11-23
 * 作者 zl
 * 功能描述 
 */
public class LoginAppImpl implements LoginApp{
	private IBaseServiceDao iBaseServiceDaoA;
	public IBaseServiceDao getiBaseServiceDaoA() {
		return iBaseServiceDaoA;
	}
	public void setiBaseServiceDaoA(IBaseServiceDao iBaseServiceDaoA) {
		this.iBaseServiceDaoA = iBaseServiceDaoA;
	}


	/**
	 * 验证此用户是否存在
	 * @param userName 用户名
	 * @param passWord 密码
	 * @return true:存在 false:不存在
	 */
	public DBRecord checkUserIsExist(String userName,String passWord){
		StringBuilder sql = new StringBuilder();
		sql.append(" select uid,username,role from user where username =  '");
		sql.append(userName).append("' and password = '").append(passWord).append("'");
		System.out.println(sql.toString());
		DBRecord dbr = new DBRecord();
		try{
			dbr = iBaseServiceDaoA.selectRow(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return dbr;
	}
	
	
//	public List getAllModule(String positiionId){
//		List listReturn = new ArrayList();
//		Vector ve = null;
//		String sql = "SELECT DISTINCT MODULE_ID as moduleId , MODULE_PARENT,MODULE_NAME,PROGRAM_NAME  FROM V_POSITION_ROLE_MODEL where POSITION_ID='"+positiionId+"' order by MODULE_ID ";
//		try{
//			List list = new ArrayList();
//			list = (List)iBaseServiceDaoA.getSqlJdbc(sql);
//			for(int i=0;i<list.size();i++){
//				DBRecord record = (DBRecord)list.get(i);
//				String m_prarent = record.getString("MODULE_PARENT").trim();
//				if(m_prarent.equals("-1") || m_prarent.length()<=0){
//					ve = new Vector();
//					ArrayList p_all_child  =  new ArrayList();
//					String p_m_id = record.getString("moduleId");
//					ve.add(record);
//					
//					for(int j=0;j<list.size();j++){
//						DBRecord child = (DBRecord)list.get(j);
//						String child_m_id = child.getString("MODULE_PARENT").trim();
//						if(child_m_id.equals(p_m_id)){
//							
//							p_all_child.add(child);	
//						}
//					}
//					/**该父目录下的所有 子模块*/
//					ve.add(p_all_child);
//					
//					/**有多少个父目录（父目录下含有其所有子模块）*/
//					listReturn.add(ve);
//				  }else
//						continue;
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//			return null;
//		}
//		
//		return listReturn;
//	}
	/**
	 * 用户验证
	 * @param userName 用户名
	 * @param passWord 密码
	 * @return
	 */
	public DBRecord checkUser(String userName,String passWord){
		StringBuffer buf = new StringBuffer();
		buf.append(" select u.USER_ID,u.CD_ID,u.TRUE_NAME,u.Esn,u.Ewn,u.Ias,u.Engage,u.Incomdate,u.Regular, ");
		buf.append(" c.COM_ID,c.COM_CODE,c.COM_NAME,c.SUB_COM_ID,c.Rate,c.Areakey,c.Builddata,c.Kotwal, ");
		buf.append(" c.Kotwalid,c.Template,c.Ispayoff,c.Majordomo,c.Majordomoid, ");
		buf.append(" d.DEP_ID,d.DEP_CODE,d.DEP_NAME,d.Sn from MO_USER_INFO u ,MO_DEPARTMENT_INFO d ,MO_COMPANY_INFO c ");
		buf.append(" where u.COM_ID=c.COM_ID and  u.DEP_ID=d.DEP_ID ");
		buf.append(" and u.USER_NAME = '").append(userName.trim()).append("'");
		buf.append(" and u.PASSWORD = '").append(passWord.trim()).append("'");
		buf.append(" and u.RECORD_STATE ='0' ");
		
		String sql = buf.toString();
		DBRecord dbr = new DBRecord();
		try{
			//System.out.println("sql======"+sql);
			dbr = iBaseServiceDaoA.selectRow(sql);
			
		}catch(Exception e){
			return null;
		}
		return dbr;
	}
	
	
	
	
	
	
	/**
	 * 查询公司字典表
	 * @param CompanyID 公司名称
	 */
	public String searchCompanyInfo(String CompanyID){
		String sql = "select AreaID from SFA_Dic_CompanyInfo where CompanyID = '"+CompanyID+"'";
		//System.out.println("sql===="+sql);
		String areaname = "";
		try{
			areaname = iBaseServiceDaoA.selectOne(sql);
			if(areaname==null){
				areaname = "";
			}
		}catch(Exception e){
			e.printStackTrace();
			return areaname;
		}
		return areaname.trim();
	}
	//根据用户名密码更新
	public boolean updateTokenUser(String auth_token, String userName,
			String passWord) {
		boolean b = false;
		String sql = "update user set auth_key = '"+auth_token+"' where username =  '"+userName+"' and password = '"+passWord+"'";
		System.out.println(sql);
		try {
			b = iBaseServiceDaoA.execUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return b;
	}
	//验证此用户是否登陆  用户id和token
	public DBRecord checkUserIsLogin(String uid, String auth_key) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select uid  from user where uid =  '");
		sql.append(uid).append("' and auth_key = '").append(auth_key).append("'");
		System.out.println(sql.toString());
		DBRecord dbr = new DBRecord();
		try{
			dbr = iBaseServiceDaoA.selectRow(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return dbr;
	}
}
