<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.mainone.util.DBRecord,java.util.Vector,java.util.List,java.util.ArrayList"%>
<%@ page import="com.mainone.app.admin.LoginInfo" %>
<script type='text/javascript' src='<%=request.getContextPath() %>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=request.getContextPath() %>/dwr/util.js'></script>
<script type='text/javascript' src='<%=request.getContextPath() %>/dwr/interface/loginAction.js'></script>
<%
	LoginInfo loginInfo = (LoginInfo)session.getAttribute("loginInfo");
	String userName = loginInfo.getUserInfo().getTRUE_NAME();
	//String comName = loginInfo.getComInfo().getCOM_NAME();
	//String depName = loginInfo.getDepInfo().getDEP_NAME();
	//String posiname = loginInfo.getPosiInfo().getName();
	//String str = userName + "(" + comName + "-" + depName + "-" + posiname +")";
%>
<script type="text/javascript">
window.onload=function(){
	loginAction.getGDT(callbackGdt);
	loginAction.getCusNum(callbackCus);
}

function callbackGdt(data){
	document.getElementById('gdt').innerHTML=data;
}

function callbackCus(data){
	document.getElementById('cus').innerHTML=data;
}

</script>
<div class="bgTop">
    <div class="logoBar">
        <a href="#" class="logo"></a>
        <span class="logo_tit">销售管理系统</span>
    </div>
</div>
<div class="userBar">
    <span class="userL"><%=userName%></span>
    <span class="userR">
    当月到账<strong><span id='gdt'></span></strong>&nbsp;&nbsp;&nbsp;
    客户数<strong><span id='cus'></span></strong>
    <a href="<%=request.getContextPath() %>/loginAction!exit.html" class="exit_btn">退出</a>
    </span>
</div>
