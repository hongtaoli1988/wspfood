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
 * �������� 2012-11-23
 * ���� zl
 * �������� 
 */
public class LoginAppImpl implements LoginApp{
	private IBaseServiceDao iBaseServiceDaoA;
	private IBaseServiceDao iBaseServiceDaoB;
	private IBaseServiceDao iBaseServiceDaoC;
    
	public void setIBaseServiceDaoA(IBaseServiceDao baseServiceDaoA) {
		this.iBaseServiceDaoA = baseServiceDaoA;
	} 

	public void setIBaseServiceDaoB(IBaseServiceDao baseServiceDaoB) {
		iBaseServiceDaoB = baseServiceDaoB;
	}
	
	
	
	public List getAllModule(String positiionId){
		List listReturn = new ArrayList();
		Vector ve = null;
		String sql = "SELECT DISTINCT MODULE_ID as moduleId , MODULE_PARENT,MODULE_NAME,PROGRAM_NAME  FROM V_POSITION_ROLE_MODEL where POSITION_ID='"+positiionId+"' order by MODULE_ID ";
		try{
			List list = new ArrayList();
			list = (List)iBaseServiceDaoA.getSqlJdbc(sql);
			for(int i=0;i<list.size();i++){
				DBRecord record = (DBRecord)list.get(i);
				String m_prarent = record.getString("MODULE_PARENT").trim();
				if(m_prarent.equals("-1") || m_prarent.length()<=0){
					ve = new Vector();
					ArrayList p_all_child  =  new ArrayList();
					String p_m_id = record.getString("moduleId");
					ve.add(record);
					
					for(int j=0;j<list.size();j++){
						DBRecord child = (DBRecord)list.get(j);
						String child_m_id = child.getString("MODULE_PARENT").trim();
						if(child_m_id.equals(p_m_id)){
							
							p_all_child.add(child);	
						}
					}
					/**�ø�Ŀ¼�µ����� ��ģ��*/
					ve.add(p_all_child);
					
					/**�ж��ٸ���Ŀ¼����Ŀ¼�º�����������ģ�飩*/
					listReturn.add(ve);
				  }else
						continue;
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		return listReturn;
	}
	/**
	 * �û���֤
	 * @param userName �û���
	 * @param passWord ����
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
			dbr = iBaseServiceDaoB.selectRow(sql);
			
		}catch(Exception e){
			return null;
		}
		return dbr;
	}
	/**
	 * ��֤���û��Ƿ����
	 * @param userName �û���
	 * @param passWord ����
	 * @return true:���� false:������
	 */
	public boolean checkUserIsExict(String userName,String passWord){
		String sql = "select * from MO_USER_INFO where USER_NAME =  '"+userName+"' and PASSWORD = '"+passWord+"' and RECORD_STATE ='0' ";
		DBRecord dbr = new DBRecord();
		boolean b = false;
		try{
			dbr = iBaseServiceDaoB.selectRow(sql);
			if(!dbr.isEmpty()){
				b = true;
				return b;
			}
		}catch(Exception e){
			e.printStackTrace();
			return b;
		}
		return b;
	}
	
	/**
	 * �û���֤
	 * @param userName �û���
	 * @return
	 */
	public DBRecord checkUser(String userName){
		StringBuffer buf = new StringBuffer();
		buf.append(" select u.USER_ID,u.CD_ID,u.TRUE_NAME,u.Esn,u.Ewn,u.Ias,u.Engage,u.Incomdate,u.Regular, ");
		buf.append(" c.COM_ID,c.COM_CODE,c.COM_NAME,c.SUB_COM_ID,c.Rate,c.Areakey,c.Builddata,c.Kotwal, ");
		buf.append(" c.Kotwalid,c.Template,c.Ispayoff,c.Majordomo,c.Majordomoid, ");
		buf.append(" d.DEP_ID,d.DEP_CODE,d.DEP_NAME,d.Sn from MO_USER_INFO u ,MO_DEPARTMENT_INFO d ,MO_COMPANY_INFO c ");
		buf.append(" where u.COM_ID=c.COM_ID and  u.DEP_ID=d.DEP_ID ");
		buf.append(" and u.USER_NAME = '").append(userName.trim()).append("'");
		buf.append(" and u.RECORD_STATE ='0' ");
		
		String sql = buf.toString();
		DBRecord dbr = new DBRecord();
		try{
			dbr = iBaseServiceDaoB.selectRow(sql);
			
		}catch(Exception e){
			return null;
		}
		return dbr;
	}
	
	
	/**
	 * ��λ��֤
	 * @param userId �û�id
	 * @return
	 */
	public DBRecord checkPosition(String userId){
		StringBuffer buf = new StringBuffer();
		buf.append(" select d.Positionid,d.Name,d.Poscode,d.Note,d.Sellflag  ");
		buf.append(" from Userstationinfo a join Departmentpositioninfo b on a.DP_ID=b.DP_ID ");
		buf.append(" join Companypositioninfo c on b.CP_ID=c.CP_ID ");
		buf.append(" join Positioninfo d on c.Positionid=d.Positionid ");
		buf.append(" where a.USER_ID ='").append(userId).append("'");
		
		String sql = buf.toString();
		DBRecord dbr = new DBRecord();
		try{
			dbr = iBaseServiceDaoB.selectRow(sql);
			
		}catch(Exception e){
			return null;
		}
		return dbr;
	}
	
	/**
	 * ��ɫ��֤
	 * @param POSITION_ID ��λid
	 * @return
	 */
	public DBRecord checkRole(String POSITION_ID){
		StringBuffer buf = new StringBuffer();
		
		buf.append(" select r.ROLE_ID,r.ROLE_NAME  ");
		buf.append(" from SFA_POSITION_ROLE_INFO pr join SFA_ROLE_INFO r on pr.ROLE_ID=r.ROLE_ID ");
		buf.append(" where pr.POSITION_ID ='").append(POSITION_ID).append("'");
		
		String sql = buf.toString();
		DBRecord dbr = new DBRecord();
		try{
			dbr = iBaseServiceDaoA.selectRow(sql);
			
		}catch(Exception e){
			return null;
		}
		return dbr;
	}
	
	/**
	 * ��ѯ��˾�ֵ��
	 * @param CompanyID ��˾����
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
	
	/**
	 * ���յ���ͳ��
	 * @return
	 */
	public String getGDT(String userId,String comId) {
		ArrayList list = null;
		String selfpay = "";
		try {
			String date = TimeTools.dateToString(new Date(),"yyyy-mm-dd");//��ǰ����
			StringBuffer proc = new StringBuffer("exec P_SFA_SYGDT_KD");
			proc.append(" ").append("'"+date+"',");
			proc.append("'"+userId+"',");
			proc.append("'"+comId+"'");
			System.out.println("wapsfa proc="+proc);
			list = iBaseServiceDaoC.getProcJdbc(proc.toString());
			for (int i = 0; i < list.size(); i++) {
				DBRecord dbr = (DBRecord) list.get(i);
				selfpay = dbr.getString("selfpay")==null||"".equals(dbr.getString("selfpay"))?"0":dbr.getString("selfpay");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return selfpay;
	}
	
	/**
	 * �����ͻ���
	 * @return
	 */
	public String getProtectNum(String employeeID) {
		String cus = "";
		String sql = "select count(*) as cus from SFA_Customer_info where EmployeeID='"+employeeID+"'";
		try {
			cus = iBaseServiceDaoA.selectOne(sql);
			if(cus==null){
				cus = "0";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cus;
		
	}
	

	public void setIBaseServiceDaoC(IBaseServiceDao baseServiceDaoC) {
		iBaseServiceDaoC = baseServiceDaoC;
	}
	
	

}
