<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.mainone.util.*,com.mainone.app.admin.*"%>

<%
	LoginInfo loginInfo = (LoginInfo)session.getAttribute("loginInfo");
	String userName = loginInfo.getUserInfo().getTRUE_NAME();
	//String comName = loginInfo.getComInfo().getCOM_NAME();
	//String depName = loginInfo.getDepInfo().getDEP_NAME();
	//String str = userName + "(" + comName + "-" + depName + ")";
	
	String sygn = (String)request.getAttribute("sygn"); 
	String message = (String)request.getAttribute("message"); 
	String path = "";
	if("0".equals(sygn)){
		path = (String)request.getAttribute("path"); 
	}
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0, width=device-width"> 
<meta name="format-detection-" content="telephone=no"/> 
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
<title>添加客户</title>

<SCRIPT LANGUAGE=javascript>

function go(path){
	window.location.href = path;
}

function exit(){
    document.form1.action = "loginAction!exit.html";
    document.form1.submit();
}

</SCRIPT>

</head>

<body>
<div class="bgTop">
    <div class="logoBar">
        <a href="#" class="logo"></a>
        <span class="logo_tit">销售管理系统</span>
    </div>
</div>
<div class="userBar">
    <span class="userL"><%=userName%></span>
    <span class="userR">
     <strong></strong>
    <a href="#" onclick="exit()" class="exit_btn">退出</a>
    </span>
</div>
<form name="form1" method="post">
<div class="form_info">
    <div class="form_tishi">
	       <table width="100%" border="0" align="center" cellpadding="1" cellspacing="1" bgcolor="#cecece" style="border:1px solid #cecece;">
              <tr>
                <td width="100%" height="40"  bgcolor="#0282e3" align="center"><font color="#FFFFFF" size="+2">信息提示</font></td>
             </tr>
              <tr>
                   <td height="200" align="center" bgcolor="#FFFFFF"><font color="#FF0000" size="+3"><%=message %></font></td>
               </tr>
                  
            </table>

	</div>
	
	
    <div class="submit_btn">
    
    	<%
 	      if("1".equals(sygn)){
        %>
    	
    	<input type="button" value="返 回" class="submit_btn_inp" onclick="history.go(-1)">
    	
    	<% }else{ %>
    	
    	<input type="button" value="继 续 添 加" class="submit_btn_inp" onclick="go('<%=path %>')">
    	
    	<%}%>
    
    </div>


</div>

</form>
</body>
</html>
