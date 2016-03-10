<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML> 
<html>
<head>
<meta charset="utf-8"> 
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0, width=device-width"> 
<meta name="format-detection-" content="telephone=no"/> 
<title>登录页</title>
<script type='text/javascript' src='<%=request.getContextPath() %>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=request.getContextPath() %>/dwr/util.js'></script>
<script type='text/javascript' src='<%=request.getContextPath() %>/dwr/interface/loginAction.js'></script>
<script language="JavaScript" type="text/JavaScript">
<!--
	
	function check(){
		var userName = document.form1.userName.value;
		var password = document.form1.password.value;
		if(userName==""){
			document.getElementById("info").innerHTML = "用户名不能为空！";
			document.form1.userName.focus();
			return false;
		}
		if(password==""){
			document.getElementById("info").innerHTML = "密码不能为空！";
			document.form1.password.focus();
			return false;
		}

		if(document.form1.rand.value.match(/^\s*$/g)){//如果未输入验证码，提示
	        document.getElementById("info").innerHTML ="请输入验证码！";	
		    document.form1.rand.focus();
		    return false;
	    }
	    
		loginAction.rand(document.form1.rand.value,callback1);

	}

	function callback1(data){
		var ra = data;
		var userName = document.form1.userName.value;
		var password = document.form1.password.value;
		if(ra == "0"){
	         loginAction.checkUserIsExict(userName,password,checkin);	    
	    }
	    else{
	    	document.getElementById('info').innerHTML="验证码输入错误，请重新输入！";
	        document.forms["form1"].rand.value="";
	        show(document.getElementById('random'));
	        document.forms["form1"].rand.select();
		    return false;
	    }
	}
	
	function checkin(flag){
		if(flag==false){
		   document.getElementById("info").innerHTML = "用户名或密码错误！请重试！";
		   return false;
		}else{
			document.form1.action="loginAction!checkLogin.html";
			document.form1.submit();
		}
	}
	
	function show(o){
	    //重载验证码
	    var timenow = new Date().getTime();
	    o.src="<%=request.getContextPath()%>/jsp/random.jsp?d="+timenow;
   
	}
//-->
</script>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
 <form name="form1" method="post" action="loginAction!checkLogin.html" onsubmit="return false;">
<div class="login_box">
    <div class="login_logo"></div>
    <div class="login_title">销售管理系统</div>
    <div class="login_form">
  		<div class="info_list">
    		<div class="inp username">
            	<span class="close_btn"></span>
            	<input name="userName" type="text" id="userName" value="" class="w_input">
            </div>
    	</div>
        <div class="info_list">
    		<div class="inp password">
            	<span class="close_btn"></span>
            	<input name="password" type="password" id="password" value="" class="w_input">
            </div>
    	</div>
        <div class="info_list">
    		<div class="inp yzm"><input type="text" name="rand" value="" class="w_input_yzm" maxlength="4"></div>
            <a href="#" class="yzm_img" onclick="show(document.getElementById('random'))"><img border=0 id="random" align="" valign="absmiddle" hspace="5" src="<%=request.getContextPath()%>/jsp/random.jsp"></a>
    	</div>
    	 <font size=3 color=red><span id="info"></span></font>
        <div class="info_list"> 
        	<div class="inp Lbtn" style="padding:0;">
            	<input type="button" class="login_btn" value="登录" onClick="return check();">
            </div>
        </div>
       
        <!--<div class="info_list"><a href="#" class="forfet_pwd">忘记密码</a></div>-->
    </div>
</div>
</form>
</body> 
</html>




