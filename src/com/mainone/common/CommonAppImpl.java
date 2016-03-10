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
 * @author：zzf
 * 创建时间：Dec 27, 2012 5:04:16 PM
 * 类说明：
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
	 * 根据positionId获取客户保护typeId
	 * typeId=7 --> 高级客户主任 --> S4
	 * typeId=8 --> 客户主任 --> S3
	 * typeId=9 --> 客户代表 --> S2
	 * typeId=10 --> 助理客户代表 --> S1
	 * typeId=131 --> 客户顾问 --> S5
	 * typeId=132 --> 高级客户顾问 --> S6 
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
	 * 根据typeId、companyId获取销售代表保护设置信息
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
	 * 获取经理保护设置信息
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
	 * 根据depId、comId获取cdId
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
	 * 获得用户信息
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
	 * 获取客户负责人列表
	 * @param comId
	 * @param depId
	 * @param ias 0：在职		1：离职		""：所有	
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
	 * 获取客户负责人列表，不包含离职员工 和 已经删除部门下的员工 -- for分配客户
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
	 * 获取客户负责人下拉框 - 所有包括在职、离职
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
				str+="<option selected value=''>客户负责人</option>";
				for (int i = 0; i < list.size(); i++) {
					DBRecord dbr = (DBRecord) list.get(i);
					if (dbr.getString("USER_ID").equals(employeeId)) {
						if(dbr.getString("Ias").equals("1")){
							str += "<option value=" + dbr.getString("USER_ID").trim() + " selected >" + dbr.getString("TRUE_NAME").trim() + "（离职）" + "</option>";
						}else{
							str += "<option value=" + dbr.getString("USER_ID").trim() + " selected >" + dbr.getString("TRUE_NAME").trim() + "</option>";
						}
					} else {
						if(dbr.getString("Ias").equals("1")){
							str += "<option value=" + dbr.getString("USER_ID").trim() + " >" + dbr.getString("TRUE_NAME").trim() + "（离职）" + "</option>";
						}else{
							str += "<option value=" + dbr.getString("USER_ID").trim() + " >" + dbr.getString("TRUE_NAME").trim() + "</option>";
						}
					}
				}
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}else{
			str+="<option selected value=''>客户负责人</option>";
		}
		
		return str;  
	}
	
	/**
	 * 获取客户负责人下拉框
	 * @param comId
	 * @param depId
	 * @param employeeId
	 * @param ias	0：在职	1：离职
	 * @return
	 * @throws Exception
	 */
	public String getEmployeeSelect(String comId, String cdId, String employeeId, String ias) throws Exception {
		
		String str = "";
		
		if(null != comId && !"".equals(comId) && null != cdId && !"".equals(cdId)){
			try {
				ArrayList<DBRecord> list = this.getEmployeeList(comId, cdId, ias);
				str+="<option selected value=''>客户负责人</option>";
				for (int i = 0; i < list.size(); i++) {
					DBRecord dbr = (DBRecord) list.get(i);
					if (dbr.getString("USER_ID").equals(employeeId)) {
						if(dbr.getString("Ias").equals("1")){
							str += "<option value=" + dbr.getString("USER_ID").trim() + " selected >" + dbr.getString("TRUE_NAME").trim() + "（离职）" + "</option>";
						}else{
							str += "<option value=" + dbr.getString("USER_ID").trim() + " selected >" + dbr.getString("TRUE_NAME").trim() + "</option>";
						}
					} else {
						if(dbr.getString("Ias").equals("1")){
							str += "<option value=" + dbr.getString("USER_ID").trim() + " >" + dbr.getString("TRUE_NAME").trim() + "（离职）" + "</option>";
						}else{
							str += "<option value=" + dbr.getString("USER_ID").trim() + " >" + dbr.getString("TRUE_NAME").trim() + "</option>";
						}
					}
				}
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}else{
			str+="<option selected value=''>客户负责人</option>";
		}
		
		return str;  
	}
	
	
	/**
	 * 获取部门下拉框
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
				str+="<option selected value=''>所属部门</option>";
				for (int i = 0; i < list.size(); i++) {
					DBRecord dbr = (DBRecord) list.get(i);
					if (dbr.getString("CD_ID").equals(cdId)) {
						if(dbr.getString("DEP_STATE").equals("1") || dbr.getString("COM_DEP_STATE").equals("1")){
							str += "<option value=" + dbr.getString("CD_ID").trim() + " selected >" + dbr.getString("DEP_NAME").trim() + "（已删除）" + "</option>";
						}else{
							str += "<option value=" + dbr.getString("CD_ID").trim() + " selected >" + dbr.getString("DEP_NAME").trim() + "</option>";
						}
					} else {
						if(dbr.getString("DEP_STATE").equals("1") || dbr.getString("COM_DEP_STATE").equals("1")){
							str += "<option value=" + dbr.getString("CD_ID").trim() + " >" + dbr.getString("DEP_NAME").trim() + "（已删除）" +  "</option>";
						}else{
							str += "<option value=" + dbr.getString("CD_ID").trim() + " >" + dbr.getString("DEP_NAME").trim() + "</option>";
						}
					}
				}
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}else{
			str+="<option selected value=''>所属部门</option>";
		}
		
		return str;  
	}
	
	/**
	 * 获取部门下拉框，不包含删除的部门 -- for分配客户
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
				str+="<option selected value=''>所属部门</option>";
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
			str+="<option selected value=''>所属部门</option>";
		}
		
		return str;  
	}
	
	/**
	 * 获取大区下拉框
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
			str+="<option selected value=''>所属大区</option>";
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
	 * 获取公司下拉框
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
				str+="<option selected value=''>所属公司</option>";
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
			str+="<option selected value=''>所属公司</option>";
		}
		
		return str; 
	}
	
	/**
	 * 获取公司下拉框
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
			str += "<option selected value=''>所属公司</option>";
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
	 * 根据CD_ID得DEP_ID
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
	 * 根据depId获取部门信息
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
	 * 获取日期样式
	 * @param year
	 * @param currentDate
	 * @param halfDate
	 * @param holidayList
	 * 规则：
	 * 1、周日出现在调休的日期里，此时周日不标红
	 * 2、过半日出现在调休的日期里，还显示过半日的颜色
	 * 3、周日和节假日冲突，以节假日为主
	 * 4、过半日和节假日不会冲突
	 * @return
	 */
	private String getDateStyleStr(String year, String currentDate, String halfDate, ArrayList<DBRecord> holidayList){
		
		String style = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		try {
			// 判断当前日期是否为周日
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
				// 判断当前日期是否为节假日
				if (currentTime.after(startTime) && currentTime.before(endTime)) {
					style = "style=\"color:#F3C;\"";
					break;
				} else if (currentTime.equals(startTime) || currentTime.equals(endTime)) {
					style = "style=\"color:#F3C;\"";
					break;
				}
				// 判断当前日期是否为调休日
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
			// 判断当前日期是否为过半日
			if (currentDate.equals(halfDate)) {
				style = "style=\"color:#606;\"";
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return style;
	}
	
	
	/**
	 * 计算开始时间 与 结束时间的天数
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
	 * 转换日期格式 8.2 --> 08-02
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
