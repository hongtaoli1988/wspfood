<%@ page language="java" import="java.util.*,com.mainone.util.*,com.mainone.app.admin.*" pageEncoding="UTF-8"%>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%DBRecord dbr = (DBRecord)request.getAttribute("dbr");
System.out.println("进入000");
int hourTotal = 24;
int minuteTotal = 60;

			ArrayList visitList = (ArrayList)request.getAttribute("visitList");
			LoginInfo loginInfo = (LoginInfo)session.getAttribute("loginInfo");
			String comName = loginInfo.getComInfo().getCOM_NAME().trim();
			String depName = loginInfo.getDepInfo().getDEP_NAME().trim();
%>
<!DOCTYPE HTML>  
<html> 
<head>
<meta charset="utf-8"> 
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0, width=device-width"> 
<meta name="format-detection-" content="telephone=no"/> 
<title>表单页</title>
<link href="<%=request.getContextPath()%>/css/style1.css" rel="stylesheet" type="text/css" />
<script src="<%=request.getContextPath()%>/js/Calendar.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/jquery-1.7.2.js"></script>
<script type='text/javascript' src='<%=request.getContextPath() %>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=request.getContextPath() %>/dwr/util.js'></script>
<script type='text/javascript' src='<%=request.getContextPath() %>/dwr/interface/visitReviewAction.js'></script>
<script type="text/javascript">
 
function search(){
 	var c = document.getElementsByName("pageSize")[0].value;
 	//alert(c);
    document.form1.action = "visitReviewAction!search.html";
    document.form1.submit();
}
 function review(areaID,talkId){
   document.location.href = "<%=request.getContextPath()%>/visitReviewAction!review.html?areaID="+areaID+"&talkId="+talkId;
}


function choose(mo){
 	var year1 = document.form1.year1.value;
 
	if(null != mo && "" != year1){
		visitReviewAction.getDayStr(year1,mo,callback);
	}else{
		var oP = document.body.getElementsByTagName("select")[2];
   		oP.parentNode.removeChild(oP);
   		var s = "<option selected value='1'>1日</option>";
   		document.getElementById("daymanager").innerHTML = "<select class='formTab_sel82' name='day1'  id='day1' >" + s + "</select>";
	}
}

function callback(str){
   var oP = document.body.getElementsByTagName("select")[2];
   oP.parentNode.removeChild(oP);
   document.getElementById("daymanager").innerHTML = "<select class='formTab_sel82' name='day1'  id='day1' >" + str + "</select>";
}

function choose2(mo){
 	var year2 = document.form1.year2.value;
	if(null != mo && "" != year2){
		visitReviewAction.getDayStr(year2,mo,callback2);
	}else{
		var oP = document.body.getElementsByTagName("select")[5];
   		oP.parentNode.removeChild(oP);
   		var s = "<option selected value='1'>1日</option>";
   		document.getElementById("daymanager2").innerHTML = "<select class='formTab_sel82' name='day2'  id='day2' >" + s + "</select>";
	}
}

function callback2(str){
   var oP = document.body.getElementsByTagName("select")[5];
   oP.parentNode.removeChild(oP);
   document.getElementById("daymanager2").innerHTML = "<select class='formTab_sel82' name='day2'  id='day2' >" + str + "</select>";
}
</script> 
</head>
<body>&nbsp;&nbsp;&nbsp;  
<jsp:include page="/jsp/Top.jsp" flush="true" />
<form name='form1' action='visitReviewAction!search.html' method='post'>
<div class="form_info">
    <div class="formTit"><h3><a href="visitReviewAction!search.html" class="addxx1">拜访客户点评</a></h3></div>
    <div class="wrap_BT">
        <div class="formTab02">
            <table>
                <tr>
                 <th>客户名称</th>
                	 <td width="94%">
 		<input type="text" class="formTab_input" value="<s:property value="customerName" />" name="customerName" id="customerName" >
					</td>
                </tr>
                <tr>
     			 <th>拜访时间</th>
                    <td>
                   <select class="formTab_sel82" name="year1" id="year1">
                	<%int year = TimeTools.dateOfYMD("y"); 
                	System.out.println("currentYear==="+year); 
                	%>
                	<option value="<%=year%>" selected="selected"><%=year%>年</option>
                </select>-
				<select class="formTab_sel82" name="month1" id="month1" onchange="choose(this.value)">
				<%  
				String mo = (String)request.getAttribute("month1");
				System.out.println("mo===="+mo); 
				
				int monthTotal = 12;
					for(int j=1;j<=monthTotal;j++){ 
						if(Integer.parseInt(mo)==j){
				%> 
	                	   <option value="<%=j%>" selected><%=j%>月</option>
					    <%}else{%>
	                	    <option value="<%=j%>"><%=j%>月</option>
	                	<%}}%>
                </select>-
				<select class="formTab_sel82" name="day1" id="day1">
				<%int day = TimeTools.dateOfMonth(year,Integer.parseInt(mo)); 
				String ddd = (String)request.getAttribute("day1");
			  
				for(int i=1;i<=day;i++){ 
					if(Integer.parseInt(ddd)==i){
				%> 
				        <option value="<%=i%>" selected><%=i%>日</option>
				    <%}else{%>
                	    <option value="<%=i%>"><%=i%>日</option>
                	<%}}%>
                </select>
               <span id="daymanager" ></span>
                至
                  <select class="formTab_sel82" name="year2" id="year2">
                	<%int year2 = TimeTools.dateOfYMD("y"); 
                	System.out.println("currentYear2==="+year2); 
                	%>
                	<option value="<%=year2%>" selected="selected"><%=year2%>年</option>
                </select>-
				<select class="formTab_sel82" name="month2" id="month2"  onchange="choose2(this.value)">
				<%  
				String mo2 = (String)request.getAttribute("month2");
				System.out.println("mo2==="+mo2); 
				int monthTotal2 = 12;
					 
					for(int j2=1;j2<=monthTotal2;j2++){ 
						if(Integer.parseInt(mo2)==j2){
				%> 
	                	   <option value="<%=j2%>" selected><%=j2%>月</option>
					    <%}else{%>
	                	    <option value="<%=j2%>"><%=j2%>月</option>
	                	<%}}%>
                </select>-
				<select class="formTab_sel82" name="day2" id="day2">
				<%int day2 = TimeTools.dateOfMonth(year2,Integer.parseInt(mo2)); 
				
				String ddd2 = (String)request.getAttribute("day2");
				
				System.out.println("days2==="+day2); 
				for(int i2=1;i2<=day2;i2++){ 
					if(Integer.parseInt(ddd2)==i2){
				%> 
				        <option value="<%=i2%>" selected><%=i2%>日</option>
				    <%}else{%>
                	    <option value="<%=i2%>"><%=i2%>日</option>
                	<%}}%>
                </select>
                <span id="daymanager2" ></span>
&nbsp;&nbsp;
 <select class="formTab_sel82" name="flagState" id="flagState" >
                    <option value="-1" <s:if test="#request.flagState==-1 ">selected</s:if>>全部拜访</option>
					<option value="0" <s:if test="#request.flagState==0 ">selected</s:if>>未点评</option>
					<option value="1" <s:if test="#request.flagState==1 ">selected</s:if>>已点评</option>
                    </select>
                    </td>
                 </tr>
 
                <tr><td colspan="2"><div class="submit_btn"><input type="button" value="开始查询" class="submit_btn_inp" onclick="search()"></div></td></tr>
            </table>
        </div>
    </div>
    
    <div class="list_con">
    <%
    if(visitList!=null){
    	for(int i=0;i<visitList.size();i++){
    		Map map = (Map)visitList.get(i);
    		String ID = map.get("ID")==null?"":map.get("ID").toString();
	        String AreaID = map.get("AreaID")==null?"":map.get("AreaID").toString();
    		String CompanyName = map.get("CompanyName")==null?"":map.get("CompanyName").toString();
    		String CreateTime = map.get("CreateTime")==null?"":map.get("CreateTime").toString();
    		if(CreateTime.length()>10){
    			CreateTime =CreateTime.substring(0,10);
    		}
    		
     		long remainPortectDays = Integer.valueOf(map.get("remainPortectDays")==null?"0":map.get("remainPortectDays").toString());
		    if(remainPortectDays < 0){
       			remainPortectDays = 0;
       		}
 			String TRUE_NAME = map.get("TRUE_NAME")==null?"":map.get("TRUE_NAME").toString();
   
			String Assess = map.get("Assess")==null?"":map.get("Assess").toString();
      		String dpimg = "";
      		if("-2".equals(Assess)){
      			dpimg = "Error.gif";
      		}
      		else{
      			dpimg = "Right.gif";
      		}
      		
      		long dpDays = TimeTools.DateDiff(CreateTime,TimeTools.dateToString(new Date(),"yyyy-mm-dd"));
    %>
        <div class="infolistBox">
            <div class="infolist">
                <h3><a href="#"><%=CompanyName %></a></h3>
                <div>
               <%if(dpDays<4){%>
                <a href="javascript:review('<%=AreaID%>','<%=ID%>')" class="btn_DP" >点评</a>
               <% } else{%>
                <a href="#" class="btn_DP1">不能点评</a>
              	<%} %> 	
                    <p class="timebar"><span class="other">剩余保护<%=remainPortectDays %>天</span>拜访时间：<%= CreateTime%></p>
                    <p class="list_det"><a href="#" class="close_btn02"><img src='<%=request.getContextPath()%>/images/<%=dpimg%>'/></a><span><%= comName%></span><span><%= depName%></span><span><%= TRUE_NAME%></span>
                   
                    </p>
                </div>
            </div>
        </div>
<%}} %> 
        
    </div>
    <div class="page">
 <table align="center" cellpadding="0" cellspacing="0" width="98%">
	<tr height="10px">
		<td  >&nbsp;</td>
	</tr>
	<tr height="1px">
		<td bgcolor="#C2D8F0"  ></td>
	</tr>
	<tr height="40px">
		<td align="left"> 
<table width="100%" border="0" cellpadding="0" cellspacing="0"><tr><td align="right"><s:property escape="false" value="toolsMenu"/>&nbsp;&nbsp;</td></tr></table>
		</td>
	</tr>
</table> 
    </div>
</div>
 
 
</form>
</body>
</html>
