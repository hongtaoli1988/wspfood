<%@ page language="java" import="java.util.*,com.mainone.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
 
DBRecord dbr = (DBRecord)request.getAttribute("dbr");
String time = dbr.getString("CreateTime")==null?"":dbr.getString("CreateTime");
if(time.length() > 10){
	time = time.substring(0, 10);
}
%>
<!DOCTYPE HTML>  
<html> 
<head>
<meta charset="utf-8"> 
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0, width=device-width"> 
<meta name="format-detection-" content="telephone=no"/> 
<title>点评</title>
<link href="<%=request.getContextPath()%>/css/style1.css" rel="stylesheet" type="text/css" />
<script src="<%=request.getContextPath()%>/js/Calendar.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
 
function reviewSave()
{
	if(document.form1.Assess.value=='-2'){
		 alert("未点评不能提交！");
		 document.form1.Assess.focus();
		 return false;
	 } 
	 
	if(document.form1.Item1.value==''){
		 alert("经理点评内容不能为空!");
		 document.form1.Item1.focus();
		 return false;
	 } 
	 
	 if(document.form1.Item1.value.length<20){
		 alert("对不起,点评内容字数不能小于20个,请您认真填写!");
		 document.form1.Item1.focus();
		 return false;
	 } 
	 
	 	 
    document.form1.action = "visitReviewAction!reviewSave.html";
    document.form1.submit();

}
</script> 
</head>
<body>
<jsp:include page="/jsp/Top.jsp" flush="true" />
<form name='form1'  method='post'>
<div class="form_info">
<div class="formTit"><h3><a href="visitReviewAction!search.html" class="addxx1">拜访客户点评</a></h3></div>
    <div class="wrap_BT">
        <dl class="bt_info">
            <dt>
           <input name="talkId" id="talkId" type="hidden" value="<%=dbr.getString("ID")==null?"0":dbr.getString("ID")%>">
		<input name="areaID" id="areaID" type="hidden" value="<%=dbr.getString("AreaID")==null?"0":dbr.getString("AreaID")%>">
            <%=dbr.getString("CompanyName")==null?"":dbr.getString("CompanyName")%>
          </dt>
            <dd><span>地址：</span><span><%=dbr.getString("CompanyAdd")==null?"":dbr.getString("CompanyAdd")%></span></dd>
            <dd><span><%=dbr.getString("CustomerTel")==null?"":dbr.getString("CustomerTel")%></span>
            <%String CustomerComeID = dbr.getString("CustomerComeID")==null?"":dbr.getString("CustomerComeID").trim();
				if("3".equals(CustomerComeID)){
					CustomerComeID = "电话开发";
				}
				else if("4".equals(CustomerComeID)){
					CustomerComeID = "转介绍";
				}
				else if("5".equals(CustomerComeID)){
					CustomerComeID = "陌生拜访";
				}
				else if("8".equals(CustomerComeID)){
					CustomerComeID = "手机录入";
				}
				else if("9".equals(CustomerComeID)){
					CustomerComeID = "展会收集";
				}
				else if("10".equals(CustomerComeID)){
					CustomerComeID = "其他";
				}
			%>	<span><%=CustomerComeID%></span>
            <%String state = dbr.getString("State")==null?"":dbr.getString("State").trim();
        		if("0".equals(state)){
        			state = "新客户";
        		}else if("1".equals(state)){
        			state = "老客户";
        		} %> 
           <span><%=state%></span></dd>
            <dd><span>主要联系人</span><span> <%=dbr.getString("LinkManName")==null?"":dbr.getString("LinkManName")%></span>
             <%String sex = dbr.getString("LinkManSex")==null?"":dbr.getString("LinkManSex").trim();
        		if("0".equals(sex)){
        			sex = "男";
        		}else if("1".equals(sex)){
        			sex = "女";
        		} %> 
            <span><%=sex%></span><span><%=dbr.getString("LinkManMobile")==null?"":dbr.getString("LinkManMobile")%></span></dd>
        </dl>
        <dl class="bt_info" style="border:none; padding-bottom:10px;">
            <dt class="b">约见信息</dt>
            <dd><span><%=time%></span>
               <%String SalePhase = dbr.getString("SalePhase")==null?"":dbr.getString("SalePhase").trim();
				if("0".equals(SalePhase)){
					SalePhase = "面谈";
				}
				else if("1".equals(SalePhase)){
					SalePhase = "说明";
				}
				else if("2".equals(SalePhase)){
					SalePhase = "促成";
				}
				else if("3".equals(SalePhase)){
					SalePhase = "成交";
				}
			%>	
            <span><%=SalePhase%></span></dd>
            <dd class="w"><%=dbr.getString("Remark")==null?"":dbr.getString("Remark").trim()%></dd>
        </dl>
    </div>
    <div class="formTab03">
        <table>
            <tr><th>经理点评</th></tr>
            <tr><td>
<%String dpAssess = dbr.getString("Assess")==null?"":dbr.getString("Assess");
		String dpAssess0="",dpAssess1="",dpAssess2="",dpAssess3="",dpAssess4="",dpAssess5="",dpAssess6="";
		if("0".equals(dpAssess)){
			dpAssess0 = "selected";
		}else if("1".equals(dpAssess)){
			dpAssess1 = "selected";
		}else if("2".equals(dpAssess)){
			dpAssess2 = "selected";
		}else if("3".equals(dpAssess)){
			dpAssess3 = "selected";
		}else if("4".equals(dpAssess)){
			dpAssess4 = "selected";
		}else if("-1".equals(dpAssess)){
			dpAssess5 = "selected";
		}else{
			dpAssess6 = "selected";
		}
		%><select name="Assess" id="Assess" class="formTab_sel82" >
			<option value="-2" <%=dpAssess6%>>未点评</option>
			<option value="0" <%=dpAssess0%>>初步接触</option>
			<option value="-1" <%=dpAssess5%>>无效数据</option>
			<option value="1" <%=dpAssess1%>>挖掘需求</option>
			<option value="2" <%=dpAssess2%>>逼单</option>
			<option value="3" <%=dpAssess3%>>方案确定</option>
			<option value="4" <%=dpAssess4%>>成交</option>
		</select>
                   </td>
            </tr>
            <tr><th>经理点评内容<font color="red">&nbsp;*</font></th></tr>
            <tr><td>
<textarea name="Item1" id="Item1" class="formTab_input formTab_inputH75"><%=dbr.getString("Item1")==null?"":dbr.getString("Item1")%></textarea>
            </td></tr>
            <tr><th>给员工建议</th></tr>
            <tr><td>
<textarea name="Item2" id="Item2" class="formTab_input formTab_inputH75"><%=dbr.getString("Item2")==null?"":dbr.getString("Item2")%></textarea>
            </td></tr>
            <tr><th>针对员工情况分析</th></tr>
            <tr><td>
<textarea name="Item3" id="Item3" class="formTab_input formTab_inputH75"><%=dbr.getString("Item3")==null?"":dbr.getString("Item3")%></textarea>
            </td></tr>
            <tr><td><div class="submit_btn"><input type="button" value="确定" class="submit_btn_inp" onclick="reviewSave();" ></div></td></tr>
        </table>
    </div>
</div>
 
</body>
</html>
