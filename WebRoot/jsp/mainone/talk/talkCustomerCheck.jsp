<%@ page language="java" import="java.util.*,com.mainone.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML> 
<html>
<head>
<meta charset="utf-8"> 
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0, width=device-width"> 
<meta name="format-detection-" content="telephone=no"/> 
<title>表单页</title>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='<%=request.getContextPath() %>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=request.getContextPath() %>/dwr/interface/talkCusAction.js'></script>
<script type="text/javascript">
function check(){
	var customerName = document.getElementById("customerName").value;
	if(customerName==""){
		document.getElementById('msgDiv').innerHTML="<div><font color='red'>客户名称不能为空！</font></div>";
 		return false;
 	}
 	
 	talkCusAction.checkCustomer(customerName, callback);
}

function callback(customerID){
   customerID = parseInt(customerID)
   if(customerID > 0){
   	   //document.getElementById('msgDiv').innerHTML="<div><font color='green'>客户是您的保护客户，可以下一步添加！</font></div>";
   	   window.location.href = "<%=request.getContextPath()%>/talkCusAction!add.html?customerID="+customerID;	
   }
   else{
       document.getElementById('msgDiv').innerHTML="<div><font color='red'>客户不是您的保护客户，不能添加约见信息！</font></div>";
       return false;
   }
}


</script>
</head>
<body>
<jsp:include page="/jsp/Top.jsp" flush="true" />
<div class="form_info">
	<div class="bg_layer" style="display:none;">
        <p>提交成功</p>
        <div class="btnBox"><input type="button" value="确定" class="btnO_inp">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="再填写一个" class="btnO_inp"></div>
	</div>
	<div class="formTit"><h3><a href="<%=request.getContextPath() %>/customerProtectAction!add.html" class="addxx1">添加客户信息</a><a href="#" class="addxx2">添加约见信息</a></h3></div>
    <div class="formTab">
    	<table style="table-layout:fixed;">
        	<tr>
            	<th><font>*</font>客户名称</th>
              <td><input type="text" class="formTab_input" name="customerName" id="customerName"></td>
            </tr>
             <div id="msgDiv"></div>
        </table>
    </div>
    <div class="submit_btn"><input type="button" value="下一步" class="submit_btn_inp" onClick="check()"></div>
</div>
</body>
</html>
