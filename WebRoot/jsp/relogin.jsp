<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0, width=device-width"> 
<meta name="format-detection-" content="telephone=no"/> 
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
<title>添加客户</title>

<SCRIPT LANGUAGE=javascript>

function relogin(){
    document.form1.action = "loginAction!login.html";
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

<form name="form1" method="post">
<div class="form_info">
    <div class="form_tishi">
	       <table width="100%" border="0" align="center" cellpadding="1" cellspacing="1" bgcolor="#cecece" style="border:1px solid #cecece;">
              <tr>
                <td width="100%" height="40"  bgcolor="#0282e3" align="center"><font color="#FFFFFF" size="+2">信息提示</font></td>
             </tr>
              <tr>
                   <td height="200" align="center" bgcolor="#FFFFFF"><font color="#FF0000" size="+3"><a href="<%=request.getContextPath() %>/loginAction!login.html" style="color:red; text-decoration:none;">没登陆或已超时!!请重新登陆!谢谢</a></font></td>
               </tr>
                  
            </table>

	</div>
	
	
    <div class="submit_btn">
    	
    	<input type="button" value="重 新 登 录" class="submit_btn_inp" onclick="relogin()">
    	
    </div>


</div>

</form>
</body>
</html>
