package com.mainone.action.talk;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.mainone.app.admin.LoginInfo;
import com.mainone.app.talk.SfaTalkAreaInfo;
import com.mainone.app.talk.VisitReviewApp;
import com.mainone.common.CommonApp;
import com.mainone.util.BaseServletContext;
import com.mainone.util.DBRecord;
import com.mainone.util.PageBean;
import com.mainone.util.TimeTools;

public class VisitReviewAction extends BaseServletContext {
	
	private VisitReviewApp visitReviewApp;
	private CommonApp commonApp;
	private int currentPage = 1;
	private int pageSize = 3;
	private String toolsMenu;
	private List visitList ;
	
	private String customerName;//客户名称
	private String flagState;//拜访状态标记
	private String year1;
	private String month1;
	private String day1;
	private String year2;
	private String month2;
	private String day2;
	private String strDay="";
	
	private String talkId;
	private String areaID;
	private String Assess;
	private String Item1;
	private String Item2;
	private String Item3;
	/**
	 * 查询拜访客户 
	 * @return
	 * @throws Exception
	 */
	public String search() throws Exception{
		System.out.println("=================VisitReviewAction=====search============");
   		try {
   	 
			PageBean pageBean = null;
			HttpSession session = request.getSession();
			LoginInfo loginInfo = (LoginInfo)session.getAttribute("loginInfo");
			String areaId = loginInfo.getComInfo().getAreakey().trim();
			String comId = loginInfo.getComInfo().getCOM_ID().trim();
			String cdId = loginInfo.getUserInfo().getCD_ID().trim();
			
			if(flagState==null){
				flagState = "-1";
			}
			if(year1==null||year1.equals("")){
				year1= String.valueOf(TimeTools.dateOfYMD("y")) ;
			}
			if(month1==null||month1.equals("")){
				month1= String.valueOf(TimeTools.dateOfYMD("m")) ;
			}
			if(day1==null||day1.equals("")){
				day1= "1" ;
			}
			if(year2==null||year2.equals("")){
				year2= String.valueOf(TimeTools.dateOfYMD("y")) ;
			}
			if(month2==null||month2.equals("")){
				month2= String.valueOf(TimeTools.dateOfYMD("m")) ;
			}
			if(day2==null||day2.equals("")){
				day2= String.valueOf(TimeTools.dateOfYMD("d")) ;
			}
			 
			String startTime = year1+"-"+month1+"-"+day1;
			String endTime = year2+"-"+month2+"-"+day2;
			
			System.out.println("======customerName========="+customerName);
			System.out.println("===startTime==========="+startTime);
			System.out.println("=====endTime=========="+endTime);
			System.out.println("=============flagState======="+flagState);
			System.out.println("=============areaId======="+areaId);
	
			pageBean =visitReviewApp.searchVisit(currentPage,pageSize, areaId,comId,cdId,customerName,startTime,endTime,flagState,request);
 
			toolsMenu = pageBean.getToolsMenu("form1");
			visitList = (List)pageBean.getAllDataForSql();
			request.setAttribute("flagState", flagState);
			request.setAttribute("day1", day1);
			request.setAttribute("month1", month1);
			request.setAttribute("year1", year1);
			request.setAttribute("year2", year2);
			request.setAttribute("month2", month2);
			request.setAttribute("day2", day2);
			request.setAttribute("visitList", visitList);
			System.out.println("===visitList==="+visitList);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return "search";
	}
 

	/**
	 * 获取拜访时间
	 * @param year
	 * @param mo
	 * @return
	 */
	public String getDayStr(String year,String mo){
	      try{
	    	int day = TimeTools.dateOfMonth(Integer.parseInt(year),Integer.parseInt(mo)); //当前月的总天数
			for(int i=1;i<=day;i++){ 
				strDay+="<option value=\""+i+"\">"+i+"日</option>";
			} 
	    
	    	System.out.println("strDay=="+strDay);
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }
	      return strDay;
	}
	 
	/**
	 * 经理点评
	 * @return
	 * @throws Exception
	 */
	public String review() throws Exception{
		System.out.println("=================VisitReviewAction=====review============");
		DBRecord dbr = visitReviewApp.getTalkArea(areaID, talkId);
		request.setAttribute("dbr", dbr);
		return "review";
	}

	/**
	 * 经理点评保存
	 * @return
	 * @throws Exception
	 */
	public String reviewSave() throws Exception{
		System.out.println("=================VisitReviewAction=====reviewSave============");
		boolean b=false;
		String message="";
		String path="";
		String sygn="";
		try{
			HttpSession session = request.getSession();
			LoginInfo loginInfo = (LoginInfo)session.getAttribute("loginInfo");
			String trueName = loginInfo.getUserInfo().getTRUE_NAME();
			String dateTime = TimeTools.dateToString(new Date(),"yyyy-mm-dd hh:mm:ss");
			SfaTalkAreaInfo talkArea = new SfaTalkAreaInfo();
			talkArea.setId(talkId);
			talkArea.setAreaId(areaID);
			talkArea.setAssess(Assess);
			talkArea.setItem1(Item1);
			talkArea.setItem2(Item2);
			talkArea.setItem3(Item3);
			talkArea.setOperatorName(trueName);
			talkArea.setOperateTime(dateTime);
			
			
			b=this.visitReviewApp.updateDpTalkArea(talkArea);
			if(b){
				sygn = "0";
				message = "点评成功!";
				path= "visitReviewAction!search.html";
				request.setAttribute("message", message);
				request.setAttribute("path", path);
				request.setAttribute("sygn", sygn);
			}
			else{		
				sygn = "1";
				message = "点评失败！";
				request.setAttribute("message", message);
				request.setAttribute("sygn", sygn);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			sygn = "1";
			message = "点评失败！";
			request.setAttribute("message", message);
			request.setAttribute("sygn", sygn);
			return "info";
		}
		return "info";
	}
	
	public VisitReviewApp getVisitReviewApp() {
		return visitReviewApp;
	}

	public void setVisitReviewApp(VisitReviewApp visitReviewApp) {
		this.visitReviewApp = visitReviewApp;
	}

	public CommonApp getCommonApp() {
		return commonApp;
	}

	public void setCommonApp(CommonApp commonApp) {
		this.commonApp = commonApp;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getToolsMenu() {
		return toolsMenu;
	}

	public void setToolsMenu(String toolsMenu) {
		this.toolsMenu = toolsMenu;
	}

	public List getVisitList() {
		return visitList;
	}

	public void setVisitList(List visitList) {
		this.visitList = visitList;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getFlagState() {
		return flagState;
	}

	public void setFlagState(String flagState) {
		this.flagState = flagState;
	}

	public String getYear1() {
		return year1;
	}
	public void setYear1(String year1) {
		this.year1 = year1;
	}

	public String getMonth1() {
		return month1;
	}

	public void setMonth1(String month1) {
		this.month1 = month1;
	}

	public String getDay1() {
		return day1;
	}

	public void setDay1(String day1) {
		this.day1 = day1;
	}

	public String getYear2() {
		return year2;
	}

	public void setYear2(String year2) {
		this.year2 = year2;
	}

	public String getMonth2() {
		return month2;
	}

	public void setMonth2(String month2) {
		this.month2 = month2;
	}

	public String getDay2() {
		return day2;
	}

	public void setDay2(String day2) {
		this.day2 = day2;
	}

	public String getStrDay() {
		return strDay;
	}

	public void setStrDay(String strDay) {
		this.strDay = strDay;
	}


	public String getTalkId() {
		return talkId;
	}


	public void setTalkId(String talkId) {
		this.talkId = talkId;
	}


	public String getAreaID() {
		return areaID;
	}

	public void setAreaID(String areaID) {
		this.areaID = areaID;
	}

	public String getAssess() {
		return Assess;
	}

	public void setAssess(String assess) {
		Assess = assess;
	}

	public String getItem1() {
		return Item1;
	}

	public void setItem1(String item1) {
		Item1 = item1;
	}

	public String getItem2() {
		return Item2;
	}

	public void setItem2(String item2) {
		Item2 = item2;
	}
	public String getItem3() {
		return Item3;
	}
	public void setItem3(String item3) {
		Item3 = item3;
	}
}
