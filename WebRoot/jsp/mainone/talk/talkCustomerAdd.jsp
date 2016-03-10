<%@ page language="java" import="java.util.*,com.mainone.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%DBRecord dbr = (DBRecord)request.getAttribute("dbr");
System.out.println("进入000");
int hourTotal = 24;
int minuteTotal = 60;
%>
<!DOCTYPE HTML>  
<html> 
<head>
<meta charset="utf-8"> 
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0, width=device-width"> 
<meta name="format-detection-" content="telephone=no"/> 
<title>表单页</title>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
<script src="<%=request.getContextPath()%>/js/Calendar.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
 
function addTalk(){
	var sygn = check();
	if(sygn==false){
		return;
	} 
 	 
    document.form1.action = "talkCusAction!addSave.html";
    document.form1.submit();
}

function check()
	{
		var breturn = true;
		var strError = "";

		
		
		//无联系人
		if(document.getElementById("linkManID").value == "")
		{
			if(trim(document.getElementById("linkManName").value) == "")
			{
				breturn = false;
				strError += "请填写联系人!\r\n";
			}

			
			var reTel = /^([0-9])([0-9-,])*([0-9])$/;
			if(trim(document.getElementById("linkManMobile").value) == "")
			{
				breturn = false;
				strError += "请填写手机/电话!\r\n";
			}
			else
			{
				 if(!reTel.test(trim(document.getElementById("linkManMobile").value)))
			    {
			        breturn = false;
			    	strError += "手机/电话格式不正确!\r\n";
			    }
			    else
			    {
			        var str = trim(document.getElementById("linkManMobile").value);
			        if(str.indexOf("--")!=-1)
			        {
						breturn = false;
			    		strError += "手机/电话格式不正确,中间不能存在两个'--'!\r\n";
			        }
			        if(str.indexOf(",,")!=-1)
			        {
						breturn = false;
			    	    strError += "手机/电话格式不正确,中间不能存在两个',,'!\r\n";
			        }
			    }
			}
		}
		

		
		var tOutTime = trim(document.getElementById("outHour").value);
		if(tOutTime=="")
		{
			breturn = false;
			strError += "请选择外出小时!\r\n";
		}
		
		var outMinute = trim(document.getElementById("outMinute").value);
		if(outMinute=="")
		{
			breturn = false;
			strError += "请选择外出分钟!\r\n";
		}
		
		
		var tBackTime = trim(document.getElementById("backHour").value);
		if(tBackTime=="")
		{
			breturn = false;
			strError += "请选择返回小时!\r\n";
		}
		
		var backMinute = trim(document.getElementById("backMinute").value);
		if(backMinute=="")
		{
			breturn = false;
			strError += "请选择返回分钟!\r\n";
		}
		
		
		
		if(trim(document.getElementById("remark").value) == "")
		{
			breturn = false;
			strError += "请填写洽谈备注!\r\n";
		}
		else
		{	
			if(document.getElementById("remark").value.length<=20)
			{
				breturn = false;
				strError += "洽谈备注必须大于20个字符!\r\n";
			}
		}
		
		
		//结束
		if(strError!="")
		{
			alert(strError);
		}	
 
		return breturn;
	}
	
	//过滤空格
	function trim(str)
	{
		return str.replace(/(^([\s|　]*)|([\s|　])*$)/g, "");
	}
-->
</script> 
</head>
<body>
<jsp:include page="/jsp/Top.jsp" flush="true" />
<form name='form1' action='talkCusAction!addSave.html' method='post'>
<div class="form_info">
	<div class="bg_layer" style="display:none;">
        <p>提交成功</p>
        <div class="btnBox"><input type="button" value="确定" class="btnO_inp">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="再填写一个" class="btnO_inp"></div>
	</div>
	<div class="formTit"><h3><a href="<%=request.getContextPath() %>/customerProtectAction!add.html" class="addxx1">添加客户信息</a><a href="#" class="addxx2">添加约见信息</a></h3></div>
	<div class="formTit"><h3>客户联系人信息</h3></div>
    <div class="formTab">
    	<table style="table-layout:fixed;">
        	<tr>
            	<th><font>*</font>客户名称</th>
              <td><input type="text" class="formTab_input" value="<%=dbr.getString("CompanyName")==null?"":dbr.getString("CompanyName")%>" disabled="disabled"></td>
            </tr>
			<tr>
            	<th><font>*</font>客户地址</th>
                <td><input type="text" class="formTab_input" value="<%=dbr.getString("CompanyAdd")==null?"0":dbr.getString("CompanyAdd")%>" disabled="disabled"></td>
            </tr>
			<tr>
            	<th><font>*</font></th>
            	<%String linkman = dbr.getString("LinkManID")==null?"":dbr.getString("LinkManID"); 
            	String linkmanvalue="",linkmancss="",linkmancss1="",islinkman = "",islinkman1 = "",islinkman2 = "";
            	if(!linkman.equals("")){
            		islinkman = "checked";
            		islinkman2 = "disabled";
            		//linkmanvalue = "<font color='green'>联系人已存在!</font>";
            		linkmancss = "on";
            	}
            	else{
            		islinkman1 = "checked";
            		linkmanvalue = "<font color='red'>联系人不存在,请添加联系人及电话!</font>";
            		linkmancss1 = "on";
            	} 
            	%>
                <td>
					<span class="maintouch" >
						<input type="radio" name="ContractList" value="1" <%=islinkman%> />
						<label class="<%=linkmancss%>" disabled>是</label>
						<input type="radio" name="ContractList" value="0" <%=islinkman1%> />						
						<label class="<%=linkmancss1%>" disabled>否</label>
					</span>
					&nbsp;&nbsp;<%=linkmanvalue%>
				</td>
            </tr>
        	<tr>
            	<th><font>*</font>联系人</th>
                <td><input type="text" name="linkManName" id="linkManName" class="formTab_input" value="<%=dbr.getString("LinkManName")==null?"0":dbr.getString("LinkManName")%>" <%=islinkman2%> ></td>
            </tr>
             
            <tr>
            	<th><font>*</font>手机/电话</th>
                <td><input type="text" name="linkManMobile" id="linkManMobile" class="formTab_input" value="<%=dbr.getString("LinkManMobile")==null?"0":dbr.getString("LinkManMobile")%>" <%=islinkman2%> ></td>
            </tr>
            
        </table>
    </div>
   
	 <div class="formTit"><h3>添加约见信息</h3></div>
    <div class="formTab"> 

	 <input name="linkManID" id="linkManID" type="hidden" value="<%=linkman%>">
     <input name="customerID" id="customerID" type="hidden" value="<%=dbr.getString("CustomerID")==null?"0":dbr.getString("CustomerID")%>">       	
     <input name="areaID" id="areaID" type="hidden" value="<%=dbr.getString("AreaID")==null?"0":dbr.getString("AreaID")%>">
     <input name="departID" id="departID" type="hidden" value="<%=dbr.getString("DepartID")==null?"0":dbr.getString("DepartID")%>">	
     <input name="companyID" id="companyID" type="hidden" value="<%=dbr.getString("CompanyID")==null?"0":dbr.getString("CompanyID")%>">	
    	
    	<table> 
    	<tr> 
            	<th><font>*</font>约见时间</th>
                <td><select class="formTab_sel1" name="createTimeYear" id="createTimeYear">
                	<%int year = TimeTools.dateOfYMD("y"); 
                	System.out.println("currentYear==="+year); 
                	%>
                	<option value="<%=year%>" selected="selected"><%=year%>年</option>
                </select>-
				<select class="formTab_sel1" name="createTimeMonth" id="createTimeMonth">
				<%int monthTotal = 12;
				int currentMonth = TimeTools.dateOfYMD("m");
				System.out.println("currentMonth==="+currentMonth);  
				for(int j=1;j<=monthTotal;j++){ 
					if(currentMonth==j){
				%> 
                	   <option value="<%=j%>" selected><%=j%>月</option>
				    <%}%>
				    
				    <%--<%else{%>
                	    <option value="<%=j%>"><%=j%>月</option> 
                	  <%}%>--%>
                	<%}%>
                </select>-
				<select class="formTab_sel1" name="createTimeDay" id="createTimeDay">
				<%int day = TimeTools.dateOfMonth(year,currentMonth); 
				int today = TimeTools.dateOfYMD("d");
				System.out.println("currentDay==="+today); 
				System.out.println("days==="+day); 
				for(int i=1;i<=day;i++){ 
					if(today==i){
				%> 
				        <option value="<%=i%>" selected><%=i%>日</option>
				    <%}else{%>
                	    <option value="<%=i%>"><%=i%>日</option>
                	<%}}%>
                </select>
				</td>
            </tr>
		 <tr>
		 		
            	<th><font>*</font>外出时间</th>
                <td><select class="formTab_sel2" name="outHour" id="outHour">
                <option value="" selected>请选择</option>
                <%for(int k=0;k<hourTotal;k++){
                %>
                <option value="<%=k%>"><%=k%>点</option>
                <%}%>
				    
			</select>-
			<select class="formTab_sel2" name="outMinute" id="outMinute">
			<option value="" selected>请选择</option>
                <%for(int l=0;l<minuteTotal;l++){
                %>
                <option value="<%=l%>"><%=l%>分</option>
                <%}%>
                
                </select>
			</td>
            </tr>
        	<tr>
            	<th><font>*</font>返回时间</th>
                <td><select class="formTab_sel2" name="backHour" id="backHour">
				    <option value="" selected>请选择</option>
                <%for(int n=0;n<hourTotal;n++){
                %>
                <option value="<%=n%>"><%=n%>点</option>
                <%}%>
			</select>-
			<select class="formTab_sel2" name="backMinute" id="backMinute">
			        <option value="" selected>请选择</option>
                <%for(int m=0;m<minuteTotal;m++){
                %>
                <option value="<%=m%>"><%=m%>分</option>
                <%}%>
                
                </select></td>
            </tr>
             
			<tr>
            	<th><font>*</font>洽谈内容</th>
                <td><textarea name="remark" id="remark" class="formTab_area"></textarea>
</td>
            </tr>

			<tr style="display:none">
            	<th>面谈次数</th>
                <td><select class="formTab_sel" name="TalkCount" id="TalkCount">
					<option value="0">首次面谈</option>
					<option value="1">二次面谈</option>
					<option value="2">多次面谈</option>
                </select></td>
            </tr>
            <tr style="display:none">
            	<th>持续约见时间</th>
                <td><select class="formTab_sel" name="intDate" id="intDate">
                	<option value="-1">请选择</option>
					<option value="0">十分钟以下</option>
					<option value="1">10--20分钟</option>
					<option value="2">20--30分钟</option>
					<option value="3">30--60分钟</option>
					<option value="4">一小时以上</option>
                </select></td>
            </tr>
            <tr style="display:none">
            	<th>销售阶段</th>
                <td>
                <select class="formTab_sel" name="SalePhase" id="SalePhase">
                	<option value="0">面谈</option>
					<option value="1">说明</option>
					<option value="2">促成</option>
					<option value="3">成交</option>
                </select></td>
            </tr>
            <tr>
			    <th>&nbsp;</th>
            	<td>
                	<div class="open js_open3">展开</div>
                </td>
            </tr>
        </table>
		
    </div>
    <div class="submit_btn"><input type="button" value="提交" class="submit_btn_inp" onclick="addTalk()"></div>
</div>

<script>

	//var tr=$('.js_open').parents('table').find('tr');
	$('.js_open3').toggle(function(){
		$(this).parents('table').find('tr').show();
		$(this).text('收起');
	},function(){
		var tr=$(this).parents('table').find('tr');
		for(var i=0;i<tr.length;i++){
			if($(tr[i]).index()>3){
				$(tr[i]).hide();
			}
		}
		$(tr[tr.length-1]).show();
		$(this).text('展开');
	})
	$('.maintouch label').click(function(){
		//$('.maintouch radio').attr('checked','');
		$('.maintouch :radio:eq('+$(this).index('label')+')').attr('checked',true).siblings().removeAttr('checked');
		$(this).addClass('on').siblings().removeClass('on');
	})
	$('.sex label').click(function(){
		//$('.maintouch radio').attr('checked','');
		$('.sex :radio:eq('+$(this).index('label')+')').attr('checked',true).siblings().removeAttr('checked');
		$(this).addClass('on').siblings().removeClass('on');
	})
</script>
</form>
</body>
</html>
