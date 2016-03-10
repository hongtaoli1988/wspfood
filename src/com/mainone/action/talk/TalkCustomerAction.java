package com.mainone.action.talk;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import uk.ltd.getahead.dwr.WebContext;
import uk.ltd.getahead.dwr.WebContextFactory;

import com.mainone.app.admin.LoginInfo;
import com.mainone.app.talk.SfaTalkAreaInfo;
import com.mainone.app.talk.TalkCustomerApp;
import com.mainone.common.CommonApp;
import com.mainone.model.sfa.SfaCustomerInfo;
import com.mainone.model.sfa.SfaLinkManInfo;
import com.mainone.util.BaseServletContext;
import com.mainone.util.DBRecord;
import com.mainone.util.PageBean;
import com.mainone.util.TimeTools;

public class TalkCustomerAction extends BaseServletContext {
	
	private TalkCustomerApp talkCustomerApp;
	private CommonApp commonApp;
	
	private String customerName;
	private String TalkCount;
	private String SalePhase;
	private String talkId;
	private String remark;
	private String intDate;//持续约见时间
	private String ContractCode;

	private String customerID;
	private String employeeID;
	private String departID;
	private String companyID;
	private String areaID;
	private String linkManID;
	private String linkManName;
	private String linkManMobile;
	private String linkManSex;

	private String remainDays;//剩余天数
	private String flag;//标记

	private String outHour;//外出时间小时
	private String outMinute;//外出时间分钟
	private String backHour;//返回时间小时
	private String backMinute;//返回时间分钟
	private String createTimeYear;//约见时间年
	private String createTimeMonth;//约见时间月
	private String createTimeDay;//约见时间日
	/**
	 * 添加约见验证客户
	 * @return
	 * @throws Exception
	 */
	public String check() throws Exception{
		return "check";
	}
	
	/**
	 * 检查客户是否存在
	 * @param userId
	 * @return
	 */
	public String checkCustomer(String customerName){
		
		WebContext webContext = WebContextFactory.get();
		HttpSession session = webContext.getSession();
		LoginInfo loginInfo = (LoginInfo)session.getAttribute("loginInfo");
		String userId = loginInfo.getUserInfo().getUSER_ID().trim();
		String CustomerID = "";
	    try{
	    	DBRecord dbr = talkCustomerApp.getTalkCustomerInfo(customerName.trim(), userId);
	    	CustomerID = dbr.getString("CustomerID")==null?"":dbr.getString("CustomerID");	
	    }
	    catch(Exception e){
	       e.printStackTrace();
	    }
	    return CustomerID; 
	}
	
	
	/**
	 * 添加约见客户记录
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception{
		DBRecord dbr = talkCustomerApp.getTalkCusLinkman(customerID);
		request.setAttribute("dbr", dbr);
		return "add";
	}
	
	/**
	 * 添加约见客户记录
	 * @return
	 * @throws Exception
	 */
	public String addSave() throws Exception{
		
		boolean b=false;
		String message="";
		String path="";
		String path1="";
		String sygn="";
		
		try{ 
			HttpSession session = request.getSession();
			LoginInfo loginInfo = (LoginInfo)session.getAttribute("loginInfo");
			String userId = loginInfo.getUserInfo().getUSER_ID();
			SfaTalkAreaInfo talkArea = new SfaTalkAreaInfo();

			String dateTime = TimeTools.dateToString(new Date(),"yyyy-mm-dd hh:mm:ss");
			String nowTime = TimeTools.dateToString(new Date(),"yyyy-mm-dd");
			
			String talkTime = createTimeYear + "-" + createTimeMonth + "-" + createTimeDay;
			String outTime = talkTime+" "+outHour+":"+outMinute+":"+"00";
			String backTime = talkTime+" "+backHour+":"+backMinute+":"+"00";

			talkArea.setAreaId(areaID);
			talkArea.setCustomerId(customerID);
			talkArea.setEmployeeId(userId);
			talkArea.setDepartId(departID);
			talkArea.setCompanyId(companyID);
			talkArea.setTalkContent("谈话内容");
			talkArea.setTalkCount(TalkCount);
			talkArea.setOutTime(outTime);
			talkArea.setBackTime(backTime);
			talkArea.setCreateTime(talkTime);
			talkArea.setSalePhase(SalePhase);
			talkArea.setIntent("签单意向");
			talkArea.setNextVisit(nowTime);
			talkArea.setRemark(remark);
			talkArea.setItem1("");
			talkArea.setItem2("");
			talkArea.setItem3("");
			talkArea.setItem4("");
			talkArea.setAssess("-2");
			talkArea.setCTime(dateTime);
			talkArea.setIntDate(intDate);
			//成交
			if("3".equals(SalePhase)){
				talkArea.setIfChengjiao("1");
				talkArea.setContractCode(ContractCode);
				talkArea.setChengjiaoTime(dateTime);
			}
			else{
				talkArea.setIfChengjiao("0");
				talkArea.setContractCode("");
			}
			
			//0-添加联系人
			if(linkManID.equals("")){
				SfaLinkManInfo sli = new SfaLinkManInfo();
				sli.setCustomerId(Integer.valueOf(customerID));
				sli.setLinkManCategory(-1);
				sli.setLinkManName(linkManName);
				sli.setLinkManMobile(linkManMobile);
				sli.setAddDate(new Date());
				sli.setLinkManType(1);
				sli.setLinkManSex(0);//默认男
				talkCustomerApp.save(sli);
				talkArea.setLinkManId(sli.getLinkManId().toString());
			}
			//1-已存在联系人
			else{
				talkArea.setLinkManId(linkManID);
			}
			//查询上次拜访时间
			String lastTalkTime = talkCustomerApp.getTalkTime(customerID, areaID);
			if(lastTalkTime==null){
				lastTalkTime = "";
			}
			b=this.talkCustomerApp.addTalkArea(talkArea);
			if(b){
				
				SfaCustomerInfo sci = talkCustomerApp.getSingleCustomerInfo(Integer.parseInt(customerID));
				String IfBaifang = sci.getIfBaifang()==null?"":sci.getIfBaifang().toString();
				//修改客户拜访记录
				if(IfBaifang.equals("0")){
					sci.setIfBaifang("1");
					talkCustomerApp.edit(sci);
				}
				//延长成交客户保护时间
				String protectState = sci.getPortectState()==null?"":sci.getPortectState().toString();
				//成交客户
				if(protectState.equals("1")){
					String myProtect = sci.getMyProtest()==null?"":sci.getMyProtest().toString();
					//我的成交客户
					if(myProtect.equals("1")){
						String endtime = sci.getPortectEndTime()==null?"":sci.getPortectEndTime().toString();
						if(endtime.length() > 10){
							endtime = endtime.substring(0, 10);
						}
						long days = TimeTools.DateDiff(TimeTools.dateToString(new Date(),"yyyy-mm-dd"),endtime);
						System.out.println("days============"+days);
						if(days < 10){
							this.extendProtectTime(sci);
						}
					}
				}
				
				if(!lastTalkTime.equals("")){
					if(lastTalkTime.length()>10){
						lastTalkTime = lastTalkTime.substring(0,10);
					}
					sygn = "0";
					message = "添加成功!该客户曾在"+TimeTools.getTimeNameString(lastTalkTime)+"被拜访过.";
					path = "talkCusAction!check.html";
					request.setAttribute("message", message);
					request.setAttribute("path", path);
					request.setAttribute("sygn", sygn);
				}
				else{
					sygn = "0";
					message = "添加成功!";
					path = "talkCusAction!check.html";
					request.setAttribute("message", message);
					request.setAttribute("path", path);
					request.setAttribute("sygn", sygn);
				}
			}
			else{		
				sygn = "1";
				message = "添加失败！";
				request.setAttribute("message", message);
				request.setAttribute("sygn", sygn);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			sygn = "1";
			message = "操作失败！";
			request.setAttribute("message", message);
			request.setAttribute("sygn", sygn);
		}
		return "info";
	}
	
	
	//延长成交客户保护时间
	public void extendProtectTime(SfaCustomerInfo sci)throws Exception{
		HttpSession session = request.getSession();
		LoginInfo loginInfo = (LoginInfo)session.getAttribute("loginInfo");
		String positionId = loginInfo.getPosiInfo().getPositionid().trim();
		String comId = loginInfo.getComInfo().getCOM_ID().trim();
		String typeId = commonApp.getCustomerProtectTypeId(positionId);
		DBRecord dbr = null;
		if(!"".equals(typeId)){
			dbr = commonApp.getCustomerProtectInfo(typeId, comId);
		}
		 
		String flag = "";
		if(null != dbr && dbr.size() != 0){
			flag = dbr.getString("Flag")==null?"":dbr.getString("Flag");
		}else{
			flag = "0";
		}
		//开启保护系统限制
		if("1".equals(flag)){
			//保护结束时间
			if(null != dbr && dbr.size() != 0){
				String protectDayCount = "".equals(dbr.getString("ProtectDayCount"))?"0":dbr.getString("ProtectDayCount");
				//System.out.println("protectDayCount====="+protectDayCount);
				sci.setPortectEndTime(TimeTools.getDate(new Date(), Integer.parseInt(protectDayCount)));
			}else{
				sci.setPortectEndTime(TimeTools.getDate(new Date(), 60));
			}
		}
		else{
			sci.setPortectEndTime(TimeTools.getDate(new Date(), 60));
		}
		talkCustomerApp.edit(sci);
	}
	
	

	public void setTalkCustomerApp(TalkCustomerApp talkCustomerApp) {
		this.talkCustomerApp = talkCustomerApp;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	
	public String getTalkCount() {
		return TalkCount;
	}

	public void setTalkCount(String talkCount) {
		TalkCount = talkCount;
	}

	public String getSalePhase() {
		return SalePhase;
	}

	public void setSalePhase(String salePhase) {
		SalePhase = salePhase;
	}

	public TalkCustomerApp getTalkCustomerApp() {
		return talkCustomerApp;
	}

	public String getTalkId() {
		return talkId;
	}

	public void setTalkId(String talkId) {
		this.talkId = talkId;
	}


	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public String getDepartID() {
		return departID;
	}

	public void setDepartID(String departID) {
		this.departID = departID;
	}

	public String getCompanyID() {
		return companyID;
	}

	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}

	public String getAreaID() {
		return areaID;
	}

	public void setAreaID(String areaID) {
		this.areaID = areaID;
	}

	public String getLinkManID() {
		return linkManID;
	}

	public void setLinkManID(String linkManID) {
		this.linkManID = linkManID;
	}

	public String getLinkManName() {
		return linkManName;
	}

	public void setLinkManName(String linkManName) {
		this.linkManName = linkManName;
	}

	
	public String getLinkManMobile() {
		return linkManMobile;
	}

	public void setLinkManMobile(String linkManMobile) {
		this.linkManMobile = linkManMobile;
	}

	

	public String getIntDate() {
		return intDate;
	}

	public void setIntDate(String intDate) {
		this.intDate = intDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public void setCommonApp(CommonApp commonApp) {
		this.commonApp = commonApp;
	}

	public String getRemainDays() {
		return remainDays;
	}



	public void setRemainDays(String remainDays) {
		this.remainDays = remainDays;
	}



	public String getFlag() {
		return flag;
	}



	public void setFlag(String flag) {
		this.flag = flag;
	}



	public String getContractCode() {
		return ContractCode;
	}

	public void setContractCode(String contractCode) {
		ContractCode = contractCode;
	}

	public String getOutHour() {
		return outHour;
	}

	public void setOutHour(String outHour) {
		this.outHour = outHour;
	}

	public String getOutMinute() {
		return outMinute;
	}

	public void setOutMinute(String outMinute) {
		this.outMinute = outMinute;
	}

	public String getBackHour() {
		return backHour;
	}

	public void setBackHour(String backHour) {
		this.backHour = backHour;
	}

	public String getBackMinute() {
		return backMinute;
	}

	public void setBackMinute(String backMinute) {
		this.backMinute = backMinute;
	}

	public String getCreateTimeYear() {
		return createTimeYear;
	}

	public void setCreateTimeYear(String createTimeYear) {
		this.createTimeYear = createTimeYear;
	}

	public String getCreateTimeMonth() {
		return createTimeMonth;
	}

	public void setCreateTimeMonth(String createTimeMonth) {
		this.createTimeMonth = createTimeMonth;
	}

	public String getCreateTimeDay() {
		return createTimeDay;
	}

	public void setCreateTimeDay(String createTimeDay) {
		this.createTimeDay = createTimeDay;
	}

	public String getLinkManSex() {
		return linkManSex;
	}

	public void setLinkManSex(String linkManSex) {
		this.linkManSex = linkManSex;
	}
}
