package com.mainone.action.customer;

import java.util.Date;

import javax.servlet.http.HttpSession;

import uk.ltd.getahead.dwr.WebContext;
import uk.ltd.getahead.dwr.WebContextFactory;

import com.mainone.app.admin.LoginInfo;
import com.mainone.app.customer.CustomerProtectApp;
import com.mainone.common.CommonApp;
import com.mainone.model.sfa.SfaCustomerInfo;
import com.mainone.model.sfa.SfaLinkManInfo;
import com.mainone.util.BaseServletContext;
import com.mainone.util.DBRecord;
import com.mainone.util.TimeTools;

/**
 * @author：zzf
 * 创建时间：Jul 9, 2013 10:27:28 AM
 * 类说明：客户保护
 */
public class CustomerProtectAction extends BaseServletContext {
	
	//客户信息
	private String customerName;
	private String startTime;
	private String endTime;
	private String state;
	private String source;
	private String useNum;
	private String customerTel;
	private String customerAddr;
	private String customerFax;
	private String zip;
	private String scale;
	private String httpUrl;
	private String product;
	private String remark;
	private String mainName;
	private String customerManager;
	
	//联系人信息
	private int linkManCategory;
	private String linkManName;
	private String linkManAdd;
	private String linkManTel;
	private String linkManMobile;
	private String linkManFax;
	private String linkManZip;
	private String linkManQq;
	private String linkManMsn;
	private int linkManSex;
	private String linkManEmail;
	//职务
	private String item1;
	private int linkManType;
	
	private String employeeId;
	private int customerId;
	
	private CustomerProtectApp customerProtectApp;
	private CommonApp commonApp;

	public void setCustomerProtectApp(CustomerProtectApp customerProtectApp) {
		this.customerProtectApp = customerProtectApp;
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
		String comId = loginInfo.getComInfo().getCOM_ID().trim();
		String employeeId = "";
	    try{
	        boolean yanzheng = customerProtectApp.yanZhengCustomer(customerName);
            if(!yanzheng){
                employeeId = "-10";
                return employeeId;
            }
	    	DBRecord dbr = customerProtectApp.getSingleCustomerInfo(customerName.trim(), comId);
	    	if(null != dbr && dbr.size() != 0){
	    		employeeId = dbr.getString("EmployeeID")==null?"":dbr.getString("EmployeeID");
	    		if(employeeId.equals(userId)){
	    			employeeId = "-2";
	    		}
	    	}  
	    }
	    catch(Exception e){
	       e.printStackTrace();
	    }
	    return employeeId;
	}
	
	/**
	 * 进入添加页面
	 * @return
	 * @throws Exception
	 */
	public String add()throws Exception{
		return "add";
	}
	
	/**
	 * 添加客户--销售代表
	 * @return
	 * @throws Exception
	 */
	public String addCustomer() throws Exception{
		
		try {
			String message = "";
			String path = "";
			String sygn = "";
			HttpSession session = request.getSession();
			LoginInfo loginInfo = (LoginInfo)session.getAttribute("loginInfo");
			String userId = loginInfo.getUserInfo().getUSER_ID().trim();
			String positionId = loginInfo.getPosiInfo().getPositionid().trim();
			String comId = loginInfo.getComInfo().getCOM_ID().trim();
			String cdId = loginInfo.getUserInfo().getCD_ID().trim();
			String areaId = loginInfo.getComInfo().getAreakey().trim();
			boolean yanzheng = customerProtectApp.yanZhengCustomer(customerName);
            if(!yanzheng){
                sygn = "1";
                message = "添加失败(客户名称格式错误)!";
                request.setAttribute("message", message);
                request.setAttribute("sygn", sygn);
                return "info";
            }
			DBRecord customerInfo = customerProtectApp.getSingleCustomerInfo(customerName.trim(), comId);
	    	if(null != customerInfo && customerInfo.size() != 0){
	    		  employeeId = customerInfo.getString("EmployeeID")==null?"":customerInfo.getString("EmployeeID");
	    	} 
			if(null == employeeId || "".equals(employeeId)){
				//检查客户电话
				DBRecord telDbr = customerProtectApp.checkCustomerTel(customerTel, comId);
				if(null != telDbr && telDbr.size() != 0){
					sygn = "1";
					message = "客户电话已存在，此客户不能添加！";
					request.setAttribute("message", message);
					request.setAttribute("sygn", sygn);
					return "info";
		    	} 
				String typeId = commonApp.getCustomerProtectTypeId(positionId);
				DBRecord dbr = null;
				if(!"".equals(typeId)){
					dbr = commonApp.getCustomerProtectInfo(typeId, comId);
				}
				
				//添加客户
				SfaCustomerInfo sci = new SfaCustomerInfo();
				sci.setCompanyName(customerName);
				sci.setCompanyAdd(customerAddr);
				sci.setCustomerTel(customerTel);
				sci.setCustomerFax(customerFax);
				sci.setCustomerZip(zip);
				sci.setCustomerComeId(8);
				sci.setEmployeeId(Integer.parseInt(userId));
				//这个是cdId，不是depId
				sci.setDepartId(Integer.parseInt(cdId));
				sci.setCompanyId(Integer.parseInt(comId));
				sci.setAreaId(Integer.parseInt(areaId));
				
				sci.setAddDate(new Date());
				sci.setState(0);
				sci.setItem1(scale);
				
				if(null == httpUrl || "".equals(httpUrl)){
					sci.setUrlState(0);
					sci.setItem2(null);
				}else{
					sci.setUrlState(1);
					sci.setItem2(httpUrl);
				}
				
				sci.setPortectBeginTime(new Date());
				
				//保护天数
				if(null != dbr && dbr.size() != 0){
					String protectDayCount = "".equals(dbr.getString("ProtectDayCount"))?"0":dbr.getString("ProtectDayCount");
					sci.setPortectEndTime(TimeTools.getDate(new Date(), Integer.parseInt(protectDayCount)));
				}else{
					sci.setPortectEndTime(new Date());
				}
				
				sci.setPortectState(0);
				sci.setAddId(Integer.parseInt(userId));
				sci.setCustomerProduct(product);
				sci.setInsertNo(0);
				sci.setUseNum(1);
				sci.setLastUseTime(new Date());
				sci.setIfGenjin("0");
				sci.setIfBaifang("0");
				sci.setResourceState("0");
				sci.setDataSource("1");
				sci.setMainName(mainName);
				sci.setCusRemark(remark);
				sci.setManager(customerManager);
				sci.setExtendTimes(0);
				
				//添加联系人 
				SfaLinkManInfo sli = new SfaLinkManInfo();
				sli.setLinkManCategory(linkManCategory);
				sli.setLinkManName(linkManName);
				sli.setLinkManAdd(linkManAdd);
				sli.setLinkManTel(linkManTel);
				sli.setLinkManMobile(linkManMobile);
				sli.setLinkManFax(linkManFax);
				sli.setLinkManZip(linkManZip);
				sli.setAddDate(new Date());
				sli.setLinkManType(linkManType);
				sli.setItem1(item1);
				sli.setLinkManQq(linkManQq);
				sli.setLinkManMsn(linkManMsn);
				sli.setLinkManSex(linkManSex);
				sli.setLinkManEmail(linkManEmail);
				
				customerId = customerProtectApp.save(sci, sli);
				if(0 == customerId){
					sygn = "1";
					message = "添加失败！";
					request.setAttribute("message", message);
					request.setAttribute("sygn", sygn);
				}else{
					sygn = "0";
					message = "添加成功！";
					path = "customerProtectAction!add.html";
					request.setAttribute("message", message);
					request.setAttribute("path", path);
					request.setAttribute("sygn", sygn);
				}
			}else{
				int flag2 = Integer.parseInt(employeeId);
				if(employeeId.equals(userId)){
					flag2 = -2;
				}
				if(flag2 > 0){
					message = "客户已是别人的保护客户，不能添加！";
				}else if(flag2 == 0){
					message = "客户已在客户池里，请去客户池领用！";
				}else if(flag2 ==  -1){
					message = "客户已是部门客户，请联系销售经理分配客户！";
				}else if(flag2 ==  -2){
					message = "客户已是你的保护客户，不用添加，可以直接做拜访、约见！";
				}
				sygn = "1";
				request.setAttribute("message", message);
				request.setAttribute("sygn", sygn);
			}
			
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		
		return "info";
	}	
	

	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getStartTime() {
		return startTime;
	}


	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}


	public String getEndTime() {
		return endTime;
	}


	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getSource() {
		return source;
	}


	public void setSource(String source) {
		this.source = source;
	}


	public String getUseNum() {
		return useNum;
	}


	public void setUseNum(String useNum) {
		this.useNum = useNum;
	}


	public void setCommonApp(CommonApp commonApp) {
		this.commonApp = commonApp;
	}


	public String getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}


	public String getCustomerTel() {
		return customerTel;
	}


	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
	}


	public String getCustomerAddr() {
		return customerAddr;
	}


	public void setCustomerAddr(String customerAddr) {
		this.customerAddr = customerAddr;
	}


	public String getCustomerFax() {
		return customerFax;
	}


	public void setCustomerFax(String customerFax) {
		this.customerFax = customerFax;
	}


	public String getZip() {
		return zip;
	}


	public void setZip(String zip) {
		this.zip = zip;
	}


	public String getScale() {
		return scale;
	}


	public void setScale(String scale) {
		this.scale = scale;
	}


	public String getHttpUrl() {
		return httpUrl;
	}


	public void setHttpUrl(String httpUrl) {
		this.httpUrl = httpUrl;
	}


	public String getProduct() {
		return product;
	}


	public void setProduct(String product) {
		this.product = product;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMainName() {
		return mainName;
	}


	public void setMainName(String mainName) {
		this.mainName = mainName;
	}


	public String getCustomerManager() {
		return customerManager;
	}


	public void setCustomerManager(String customerManager) {
		this.customerManager = customerManager;
	}


	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getLinkManCategory() {
		return linkManCategory;
	}


	public void setLinkManCategory(int linkManCategory) {
		this.linkManCategory = linkManCategory;
	}


	public String getLinkManName() {
		return linkManName;
	}


	public void setLinkManName(String linkManName) {
		this.linkManName = linkManName;
	}


	public String getLinkManAdd() {
		return linkManAdd;
	}


	public void setLinkManAdd(String linkManAdd) {
		this.linkManAdd = linkManAdd;
	}


	public String getLinkManTel() {
		return linkManTel;
	}


	public void setLinkManTel(String linkManTel) {
		this.linkManTel = linkManTel;
	}


	public String getLinkManMobile() {
		return linkManMobile;
	}


	public void setLinkManMobile(String linkManMobile) {
		this.linkManMobile = linkManMobile;
	}


	public String getLinkManFax() {
		return linkManFax;
	}


	public void setLinkManFax(String linkManFax) {
		this.linkManFax = linkManFax;
	}


	public String getLinkManZip() {
		return linkManZip;
	}


	public void setLinkManZip(String linkManZip) {
		this.linkManZip = linkManZip;
	}


	public String getLinkManQq() {
		return linkManQq;
	}


	public void setLinkManQq(String linkManQq) {
		this.linkManQq = linkManQq;
	}


	public String getLinkManMsn() {
		return linkManMsn;
	}


	public void setLinkManMsn(String linkManMsn) {
		this.linkManMsn = linkManMsn;
	}

	public int getLinkManSex() {
		return linkManSex;
	}


	public void setLinkManSex(int linkManSex) {
		this.linkManSex = linkManSex;
	}


	public String getLinkManEmail() {
		return linkManEmail;
	}


	public void setLinkManEmail(String linkManEmail) {
		this.linkManEmail = linkManEmail;
	}


	public String getItem1() {
		return item1;
	}


	public void setItem1(String item1) {
		this.item1 = item1;
	}


	public int getLinkManType() {
		return linkManType;
	}


	public void setLinkManType(int linkManType) {
		this.linkManType = linkManType;
	}

}
