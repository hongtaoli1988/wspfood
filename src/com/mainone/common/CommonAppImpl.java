package com.mainone.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mainone.dao.IBaseServiceDao;
import com.mainone.util.DBRecord;
import com.mainone.util.TimeTools;

/**
 * @author��zzf
 * ����ʱ�䣺Dec 27, 2012 5:04:16 PM
 * ��˵����
 */
public class CommonAppImpl implements CommonApp {
	
	private IBaseServiceDao iBaseServiceDaoA;
	private IBaseServiceDao iBaseServiceDaoB;

	
	public void setIBaseServiceDaoB(IBaseServiceDao baseServiceDaoB) {
		iBaseServiceDaoB = baseServiceDaoB;
	}


	public void setIBaseServiceDaoA(IBaseServiceDao baseServiceDaoA) {
		iBaseServiceDaoA = baseServiceDaoA;
	}

	/**
	 * ����positionId��ȡ�ͻ�����typeId
	 * typeId=7 --> �߼��ͻ����� --> S4
	 * typeId=8 --> �ͻ����� --> S3
	 * typeId=9 --> �ͻ����� --> S2
	 * typeId=10 --> ����ͻ����� --> S1
	 * typeId=131 --> �ͻ����� --> S5
	 * typeId=132 --> �߼��ͻ����� --> S6 
	 * @param positionId
	 * @return
	 * @throws Exception
	 */
	public String getCustomerProtectTypeId(String positionId)throws Exception {
		
		String typeId = "";
		if("135".equals(positionId)){
			typeId = "7";
		}else if("7".equals(positionId) || "8".equals(positionId) || "9".equals(positionId) || "10".equals(positionId) || "131".equals(positionId) || "132".equals(positionId) ){
			typeId = positionId;
		}
		
		return typeId;
		
	}
	
	/**
	 * ����typeId��companyId��ȡ���۴�����������Ϣ
	 * @param typeId
	 * @param companyId
	 * @return
	 * @throws Exception
	 */
	public DBRecord getCustomerProtectInfo(String typeId, String companyId)throws Exception {
		
		DBRecord dbr = null;
		String sql = " select * from SFA_CustomerProtect where Typeid = '" +typeId+ "' and CompanyID = '" +companyId+"' and Kind = 1 ";
		try {
			dbr = iBaseServiceDaoA.selectRow(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dbr;
		
	}
	
	/**
	 * ��ȡ������������Ϣ
	 * @param cdId
	 * @return
	 * @throws Exception
	 */
	public DBRecord getManagerCustomerProtectInfo(String cdId)throws Exception {
		
		DBRecord dbr = null;
		String sql = " select * from SFA_CustomerProtect where DepartID = '" +cdId+ "' and Kind = 2 ";
		try {
			dbr = iBaseServiceDaoA.selectRow(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dbr;
		
	}
	
	/**
	 * ����depId��comId��ȡcdId
	 * @param depId
	 * @param comId
	 * @return
	 * @throws Exception
	 */
	public String getCdId(String depId, String comId)throws Exception {
		
		String cdId = "";
		String sql = " select CD_ID from MO_COM_DEP_INFO where DEP_ID = '" +depId+ "' and COM_ID = '" +comId+"'";
		try {
			cdId = iBaseServiceDaoB.selectOne(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(null == cdId){
			cdId = "";
		}
		
		return cdId;
		
	}
	
	/**
	 * ����û���Ϣ
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public DBRecord getUserInfo(String userId)throws Exception {
		
		DBRecord dbr = null;
		String sql = " select * from MO_USER_INFO where USER_ID = '" +userId+"' and RECORD_STATE in (0, 1) ";
		try {
			dbr = iBaseServiceDaoB.selectRow(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dbr;
		
	}
	
	/**
	 * ��ȡ�ͻ��������б�
	 * @param comId
	 * @param depId
	 * @param ias 0����ְ		1����ְ		""������	
	 * @return
	 */
	public ArrayList<DBRecord> getEmployeeList(String comId, String cdId, String ias) throws Exception {
	
		ArrayList<DBRecord> list = null;
		
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(
					" select m.USER_ID, m.TRUE_NAME, m.Ias from MO_USER_INFO m join MO_DEPARTMENT_INFO mdi on m.DEP_ID = mdi.DEP_ID " +
					"join MO_COM_DEP_INFO mci on m.CD_ID = mci.CD_ID " +
					"join Userstationinfo usi on m.USER_ID = usi.USER_ID " +
					"join Departmentpositioninfo dpi on dpi.DP_ID = usi.DP_ID " +
					"join Companypositioninfo csi on csi.CP_ID = dpi.CP_ID " +
					"join Positioninfo pi on pi.Positionid = csi.Positionid " +
					"where pi.Positionid in (131, 132, 135, 7, 8, 9, 10) and m.RECORD_STATE in (0, 1) and mdi.Type = '1' "
							  );
			
			if (null != comId && !"".equals(comId)) {
				sql.append(" and m.COM_ID = " + comId);
			}
			if (null != cdId && !"".equals(cdId)) {
				sql.append(" and m.CD_ID = " + cdId);
			}
			if (null != ias && !"".equals(ias)) {
				sql.append(" and m.Ias = " + ias);
			}
			sql.append(" order by m.Ias asc");
			
			list = (ArrayList<DBRecord>) iBaseServiceDaoB.getSqlJdbc(sql.toString());

		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	/**
	 * ��ȡ�ͻ��������б���������ְԱ�� �� �Ѿ�ɾ�������µ�Ա�� -- for����ͻ�
	 * @param comId
	 * @param depId
	 * @return
	 */
	public ArrayList<DBRecord> getEmployeeListNormal(String comId, String cdId) throws Exception {
	
		ArrayList<DBRecord> list = null;
		
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(
			" select m.USER_ID, m.TRUE_NAME from MO_USER_INFO m join MO_DEPARTMENT_INFO mdi on m.DEP_ID = mdi.DEP_ID " +
			"join MO_COM_DEP_INFO mci on m.CD_ID = mci.CD_ID " +
			"join Userstationinfo usi on m.USER_ID = usi.USER_ID " +
			"join Departmentpositioninfo dpi on dpi.DP_ID = usi.DP_ID " +
			"join Companypositioninfo csi on csi.CP_ID = dpi.CP_ID " +
			"join Positioninfo pi on pi.Positionid = csi.Positionid " +
			"where pi.Positionid in (131, 132, 135, 7, 8, 9, 10) and m.RECORD_STATE = '0' and mdi.RECORD_STATE = '0' " +
			"and mci.RECORD_STATE = '0' and m.Ias = '0' and mdi.Type = '1' "
					  );
			if (null != comId && !"".equals(comId)) {
				sql.append(" and m.COM_ID = " + comId);
			}
			if (null != cdId && !"".equals(cdId)) {
				sql.append(" and m.CD_ID = " + cdId);
			}
			sql.append(" order by m.USER_ID asc");
			
			System.out.println(sql);
			
			list = (ArrayList<DBRecord>) iBaseServiceDaoB.getSqlJdbc(sql.toString());

		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * ��ȡ�ͻ������������� - ���а�����ְ����ְ
	 * @param comId
	 * @param depId
	 * @param employeeId
	 * @return
	 */
	public String getEmployeeSelect(String comId, String cdId, String employeeId) throws Exception {
		
		String str = "";
		
		if(null != comId && !"".equals(comId) && null != cdId && !"".equals(cdId)){
			try {
				ArrayList<DBRecord> list = this.getEmployeeList(comId, cdId, "");
				str+="<option selected value=''>�ͻ�������</option>";
				for (int i = 0; i < list.size(); i++) {
					DBRecord dbr = (DBRecord) list.get(i);
					if (dbr.getString("USER_ID").equals(employeeId)) {
						if(dbr.getString("Ias").equals("1")){
							str += "<option value=" + dbr.getString("USER_ID").trim() + " selected >" + dbr.getString("TRUE_NAME").trim() + "����ְ��" + "</option>";
						}else{
							str += "<option value=" + dbr.getString("USER_ID").trim() + " selected >" + dbr.getString("TRUE_NAME").trim() + "</option>";
						}
					} else {
						if(dbr.getString("Ias").equals("1")){
							str += "<option value=" + dbr.getString("USER_ID").trim() + " >" + dbr.getString("TRUE_NAME").trim() + "����ְ��" + "</option>";
						}else{
							str += "<option value=" + dbr.getString("USER_ID").trim() + " >" + dbr.getString("TRUE_NAME").trim() + "</option>";
						}
					}
				}
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}else{
			str+="<option selected value=''>�ͻ�������</option>";
		}
		
		return str;  
	}
	
	/**
	 * ��ȡ�ͻ�������������
	 * @param comId
	 * @param depId
	 * @param employeeId
	 * @param ias	0����ְ	1����ְ
	 * @return
	 * @throws Exception
	 */
	public String getEmployeeSelect(String comId, String cdId, String employeeId, String ias) throws Exception {
		
		String str = "";
		
		if(null != comId && !"".equals(comId) && null != cdId && !"".equals(cdId)){
			try {
				ArrayList<DBRecord> list = this.getEmployeeList(comId, cdId, ias);
				str+="<option selected value=''>�ͻ�������</option>";
				for (int i = 0; i < list.size(); i++) {
					DBRecord dbr = (DBRecord) list.get(i);
					if (dbr.getString("USER_ID").equals(employeeId)) {
						if(dbr.getString("Ias").equals("1")){
							str += "<option value=" + dbr.getString("USER_ID").trim() + " selected >" + dbr.getString("TRUE_NAME").trim() + "����ְ��" + "</option>";
						}else{
							str += "<option value=" + dbr.getString("USER_ID").trim() + " selected >" + dbr.getString("TRUE_NAME").trim() + "</option>";
						}
					} else {
						if(dbr.getString("Ias").equals("1")){
							str += "<option value=" + dbr.getString("USER_ID").trim() + " >" + dbr.getString("TRUE_NAME").trim() + "����ְ��" + "</option>";
						}else{
							str += "<option value=" + dbr.getString("USER_ID").trim() + " >" + dbr.getString("TRUE_NAME").trim() + "</option>";
						}
					}
				}
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}else{
			str+="<option selected value=''>�ͻ�������</option>";
		}
		
		return str;  
	}
	
	
	/**
	 * ��ȡ����������
	 * @param comId
	 * @param cdId
	 * @return
	 */
	public String getDepSelect(String comId, String cdId) throws Exception {
		
		String str = "";
		
		if(null != comId && !"".equals(comId)){
			ArrayList<DBRecord> list = null;
			String sql = " select mci.CD_ID, mdi.DEP_NAME, mdi.RECORD_STATE As DEP_STATE, mci.RECORD_STATE AS COM_DEP_STATE from MO_DEPARTMENT_INFO mdi join MO_COM_DEP_INFO mci on mdi.DEP_ID = mci.DEP_ID where mdi.RECORD_STATE in (0, 1) and mci.RECORD_STATE in (0, 1) and mdi.Type = '1' and mci.COM_ID = '" +comId+"' order by mdi.RECORD_STATE, mci.RECORD_STATE, mci.CD_ID asc ";
			
			try {
				list = (ArrayList<DBRecord>)iBaseServiceDaoB.getSqlJdbc(sql);
				str+="<option selected value=''>��������</option>";
				for (int i = 0; i < list.size(); i++) {
					DBRecord dbr = (DBRecord) list.get(i);
					if (dbr.getString("CD_ID").equals(cdId)) {
						if(dbr.getString("DEP_STATE").equals("1") || dbr.getString("COM_DEP_STATE").equals("1")){
							str += "<option value=" + dbr.getString("CD_ID").trim() + " selected >" + dbr.getString("DEP_NAME").trim() + "����ɾ����" + "</option>";
						}else{
							str += "<option value=" + dbr.getString("CD_ID").trim() + " selected >" + dbr.getString("DEP_NAME").trim() + "</option>";
						}
					} else {
						if(dbr.getString("DEP_STATE").equals("1") || dbr.getString("COM_DEP_STATE").equals("1")){
							str += "<option value=" + dbr.getString("CD_ID").trim() + " >" + dbr.getString("DEP_NAME").trim() + "����ɾ����" +  "</option>";
						}else{
							str += "<option value=" + dbr.getString("CD_ID").trim() + " >" + dbr.getString("DEP_NAME").trim() + "</option>";
						}
					}
				}
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}else{
			str+="<option selected value=''>��������</option>";
		}
		
		return str;  
	}
	
	/**
	 * ��ȡ���������򣬲�����ɾ���Ĳ��� -- for����ͻ�
	 * @param comId
	 * @param depId
	 * @return
	 */
	public String getDepSelectNormal(String comId, String cdId) throws Exception {
		
		String str = "";
		
		if(null != comId && !"".equals(comId)){
			ArrayList<DBRecord> list = null;
			String sql = " select mci.CD_ID, mdi.DEP_NAME from MO_DEPARTMENT_INFO mdi join MO_COM_DEP_INFO mci on mdi.DEP_ID = mci.DEP_ID where mdi.RECORD_STATE = '0' and mci.RECORD_STATE = '0' and mdi.Type = '1' and mci.COM_ID = '" +comId+"' order by mci.CD_ID asc ";
			
			try {
				list = (ArrayList<DBRecord>)iBaseServiceDaoB.getSqlJdbc(sql);
				str+="<option selected value=''>��������</option>";
				for (int i = 0; i < list.size(); i++) {
					DBRecord dbr = (DBRecord) list.get(i);
					if (dbr.getString("CD_ID").equals(cdId)) {
						str += "<option value=" + dbr.getString("CD_ID").trim() + " selected >" + dbr.getString("DEP_NAME").trim() + "</option>";
					} else {
						str += "<option value=" + dbr.getString("CD_ID").trim() + " >" + dbr.getString("DEP_NAME").trim() + "</option>";
					}
				}
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}else{
			str+="<option selected value=''>��������</option>";
		}
		
		return str;  
	}
	
	/**
	 * ��ȡ����������
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public String getAreaSelect(String areaId) throws Exception {
		
		String str = "";
		ArrayList<DBRecord> list = null;
		String sql = " select sda.AreaID, sda.AreaName from SFA_Dic_AreaInfo sda order by sda.AreaID asc ";
		try {
			list = (ArrayList<DBRecord>)iBaseServiceDaoA.getSqlJdbc(sql);
			str+="<option selected value=''>��������</option>";
			for (int i = 0; i < list.size(); i++) {
				DBRecord dbr = (DBRecord) list.get(i);
				if (dbr.getString("AreaID").equals(areaId)) {
					str += "<option value=" + dbr.getString("AreaID").trim() + " selected >" + dbr.getString("AreaName").trim() + "</option>";
				} else {
					str += "<option value=" + dbr.getString("AreaID").trim() + " >" + dbr.getString("AreaName").trim() + "</option>";
				}
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		return str; 
	}
	
	/**
	 * ��ȡ��˾������
	 * @param areaId
	 * @param comId
	 * @return
	 * @throws Exception
	 */
	public String getComSelect(String areaId, String comId) throws Exception {
		
		String str = "";
		
		if(null != areaId && !"".equals(areaId)){
			ArrayList<DBRecord> list = null;
			String sql = " select mci.COM_ID, mci.COM_NAME from MO_COMPANY_INFO mci where mci.RECORD_STATE = '0' and mci.Areakey = '" +areaId+"' order by mci.COM_ID asc ";
			try {
				list = (ArrayList<DBRecord>)iBaseServiceDaoB.getSqlJdbc(sql);
				str+="<option selected value=''>������˾</option>";
				for (int i = 0; i < list.size(); i++) {
					DBRecord dbr = (DBRecord) list.get(i);
					if (dbr.getString("COM_ID").equals(comId)) {
						str += "<option value=" + dbr.getString("COM_ID").trim() + " selected >" + dbr.getString("COM_NAME").trim() + "</option>";
					} else {
						str += "<option value=" + dbr.getString("COM_ID").trim() + " >" + dbr.getString("COM_NAME").trim() + "</option>";
					}
				}
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}else{
			str+="<option selected value=''>������˾</option>";
		}
		
		return str; 
	}
	
	/**
	 * ��ȡ��˾������
	 * @param comId
	 * @return
	 * @throws Exception
	 */
	public String getComSelect(String comId) throws Exception {
		
		String str = "";

		ArrayList<DBRecord> list = null;
		String sql = " select mci.COM_ID, mci.COM_NAME from MO_COMPANY_INFO mci where mci.RECORD_STATE = '0' order by mci.COM_ID asc ";
		try {
			list = (ArrayList<DBRecord>) iBaseServiceDaoB.getSqlJdbc(sql);
			str += "<option selected value=''>������˾</option>";
			for (int i = 0; i < list.size(); i++) {
				DBRecord dbr = (DBRecord) list.get(i);
				if (dbr.getString("COM_ID").equals(comId)) {
					str += "<option value=" + dbr.getString("COM_ID").trim() + " selected >" + dbr.getString("COM_NAME").trim() + "</option>";
				} else {
					str += "<option value=" + dbr.getString("COM_ID").trim() + " >" + dbr.getString("COM_NAME").trim() + "</option>";
				}
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return str; 
	}

	
	/**
	 * ����CD_ID��DEP_ID
	 * @param cdId
	 * @return
	 */
	public String getDepId(String cdId){
		String  depId = ""; 
		if("".equals(cdId)){
			return depId;
		}else{
			String sql = "select DEP_ID from MO_COM_DEP_INFO where CD_ID='"+cdId+"'";
			
			boolean b = false;
			try{
				depId = iBaseServiceDaoB.selectOne(sql);
				System.out.println("cdId=="+cdId);
				System.out.println("depid=="+depId);
				return depId;
			}catch(Exception e){
				e.printStackTrace();
				return depId;
			}
		}
	}
	
	
	/**
	 * ����depId��ȡ������Ϣ
	 * @param depId
	 * @return
	 * @throws Exception
	 */
	public DBRecord getDeptInfo(String depId) throws Exception {
		DBRecord dbr = null;
		String sql = " select * from MO_DEPARTMENT_INFO where DEP_ID = '" +depId+ "'";
		try {
			dbr = iBaseServiceDaoB.selectRow(sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dbr;
	}
	
	

	

	
	
	
	
	/**
	 * ��ȡ������ʽ
	 * @param year
	 * @param currentDate
	 * @param halfDate
	 * @param holidayList
	 * ����
	 * 1�����ճ����ڵ��ݵ��������ʱ���ղ����
	 * 2�������ճ����ڵ��ݵ����������ʾ�����յ���ɫ
	 * 3�����պͽڼ��ճ�ͻ���Խڼ���Ϊ��
	 * 4�������պͽڼ��ղ����ͻ
	 * @return
	 */
	private String getDateStyleStr(String year, String currentDate, String halfDate, ArrayList<DBRecord> holidayList){
		
		String style = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		try {
			// �жϵ�ǰ�����Ƿ�Ϊ����
			boolean b = TimeTools.judgeIfSunday(this.convertDate(year, currentDate));
			if (b) {
				style = "style=\"color:#ff0000;\"";
			}
			for (int i = 0; i < holidayList.size(); i++) {
				DBRecord dbr = holidayList.get(i);
				String startDate = dbr.getString("START_DATE")==null?"":dbr.getString("START_DATE");
				String endDate = dbr.getString("END_DATE")==null?"":dbr.getString("END_DATE");
				String restDates = dbr.getString("REST_DATES")==null?"":dbr.getString("REST_DATES");
				if (null != startDate && !"".equals(startDate)) {
					startDate = startDate.substring(0, 10);
				}
				if (null != endDate && !"".equals(endDate)) {
					endDate = endDate.substring(0, 10);
				}
				Date startTime = format.parse(startDate);
				Date endTime = format.parse(endDate);
				Date currentTime = format.parse(this.convertDate(year, currentDate));
				// �жϵ�ǰ�����Ƿ�Ϊ�ڼ���
				if (currentTime.after(startTime) && currentTime.before(endTime)) {
					style = "style=\"color:#F3C;\"";
					break;
				} else if (currentTime.equals(startTime) || currentTime.equals(endTime)) {
					style = "style=\"color:#F3C;\"";
					break;
				}
				// �жϵ�ǰ�����Ƿ�Ϊ������
				if(!"".equals(restDates)){
					String[] str = restDates.split(",");
					for (int j = 0; j < str.length; j++) {
						String rDate = str[j];
						Date rTime = format.parse(rDate);
						if (currentTime.equals(rTime)) {
							style = "";
							break;
						}
					}
				}

			}
			// �жϵ�ǰ�����Ƿ�Ϊ������
			if (currentDate.equals(halfDate)) {
				style = "style=\"color:#606;\"";
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return style;
	}
	
	
	/**
	 * ���㿪ʼʱ�� �� ����ʱ�������
	 * @param year
	 * @param month
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	private List<String> computeStartDateEndDate(int year, int month, String startDate, String endDate){
		
		List<String> monthDayList = new ArrayList<String>();
		String monthEnd = TimeTools.getMonthEnd(String.valueOf(year), String.valueOf(month));
		long days = TimeTools.DateDiff(monthEnd, endDate);
		int beginDay = Integer.parseInt(startDate.substring(8, startDate.length()));
		
		if(days > 0){
			long m = TimeTools.DateDiff(startDate, monthEnd);
			for (int i = 0; i <= m; i++) {
				String str = month + "." + beginDay;
				monthDayList.add(str);
				beginDay++;
			}
			long n = TimeTools.DateDiff(monthEnd, endDate);
			
			int k = 1;
			for (int i = 0; i < n; i++) {
				String str = (month + 1) + "." + k;
				monthDayList.add(str);
				k++;
			}
		}else{
			long l = TimeTools.DateDiff(startDate, endDate);
			for (int i = 0; i <= l; i++) {
				String str = month + "." + beginDay;
				monthDayList.add(str);
				beginDay++;
			}
			
		}
		
		return monthDayList;
	}
	
	
	/**
	 * ת�����ڸ�ʽ 8.2 --> 08-02
	 * @param year
	 * @param date
	 * @return
	 */
	private String convertDate(String year, String date){
		
		String str = "";
		String[] s = date.split("\\.");
		String month = s[0];
		String day = s[1];
		if(month.length() == 1){
			month = "0" + month;
		}
		if(day.length() == 1){
			day = "0" + day;
		}
		str = year + "-" + month + "-" + day;
		
		return str;
		
	}



}
