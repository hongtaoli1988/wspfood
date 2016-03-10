<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@page import="com.mainone.util.*,com.mainone.app.admin.*"%>
<!DOCTYPE HTML> 
<html>
<head>
<meta charset="utf-8"> 
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0, width=device-width"> 
<meta name="format-detection-" content="telephone=no"/> 
<title>添加客户</title>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
<script src="<%=request.getContextPath()%>/js/jquery-1.7.2.js"></script>
<script src="<%=request.getContextPath()%>/js/Calendar.js" type="text/javascript"></script>
<script type='text/javascript' src='<%=request.getContextPath() %>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=request.getContextPath() %>/dwr/interface/customerProtectAction.js'></script>

<script type="text/javascript">

function addCustomer(){
	var sygn = CheckForm();
	if(sygn==false){
		return;
	}
	
	var sygn2 = CheckForm2();
	if(sygn2==false){
		return;
	}
 	
    document.form1.action = "customerProtectAction!addCustomer.html";
    document.form1.submit();
}

function CheckForm()
	{
		var breturn = true;
		var strError = "";
		
		if(trim(document.getElementById("customerName").value) == "")
		{
			breturn = false;
			strError += "请填写客户名称!\r\n";
		}
		
		if(trim(document.getElementById("customerName").value) != "")
		{	
			var source = trim(document.getElementById("customerName").value);
		    var re = /^[\u4e00-\u9fa5a-zA-Z0-9(]+[\u4e00-\u9fa5a-zA-Z0-9\.\-()]+[\u4e00-\u9fa5a-zA-Z0-9)]$/;
			if(!re.test(trim(document.getElementById("customerName").value))){
			    var re1 = /^[\u4e00-\u9fa5a-zA-Z0-9]{2,2}$/;
				if(!re1.test(trim(document.getElementById("customerName").value))){
			    breturn = false;
		    	strError += "客户名称格式不正确!\r\n";
				}
			}
			var p = /[\.\-()]{2,}/;
			if(p.test(trim(document.getElementById("customerName").value))){
			    breturn = false;
		    	strError += "客户名称特殊符号不能连续出现!\r\n";
			}
			if(source.indexOf("(")!=-1){
                if(source.indexOf(")")==-1 || source.indexOf("(")>source.indexOf(")"))
                {
                    breturn = false;
		    	    strError += "客户名称(格式不正确!\r\n";
                }
            }
            if(source.indexOf(")")!=-1){
                if(source.indexOf("(")==-1 || source.indexOf("(")>source.indexOf(")"))
                {
                  breturn = false;
		    	    strError += "客户名称)格式不正确!\r\n";
                }
            }
		}
		
		
		
		if(trim(document.getElementById("customerAddr").value) == "")
		{
			breturn = false;
			strError += "请填写客户地址!\r\n";
		}
		if(trim(document.getElementById("customerAddr").value).length<=4)
		{
			breturn = false;
			strError += "客户地址必须大于4个字符!\r\n";
		}
		
		if(trim(document.getElementById("zip").value)!= "")
		{
			var re = /^\d{6}$/;
		    if(!re.test(trim(document.getElementById("zip").value)))
		    {
		        breturn = false;
		    	strError += "邮政编码格式不正确!\r\n";
		    }
		}		 
		var re = /^([0-9])([0-9-,])*([0-9])$/;		
		if(trim(document.getElementById("customerTel").value)!= "")
		{
		    if(!re.test(trim(document.getElementById("customerTel").value)))
		    {
		        breturn = false;
		    	strError += "电话格式不正确!\r\n";
		    }
		    else
		    {
		        var str = trim(document.getElementById("customerTel").value);
		        if(str.indexOf("--")!=-1)
		        {
					breturn = false;
		    		strError += "电话格式不正确,中间不能存在两个'--'!\r\n";
		        }
		        if(str.indexOf(",,")!=-1)
		        {
					breturn = false;
		    	    strError += "电话格式不正确,中间不能存在两个',,'!\r\n";
		        }
		    }
		} 
		else
		{
			breturn = false;
			strError += "请填写客户电话!\r\n";
		}
		if(trim(document.getElementById("customerFax").value)!= "")
		{
		    if(!re.test(trim(document.getElementById("customerFax").value)))
		    {
		        breturn = false;
		    	strError += "传真格式不正确!\r\n";
		    }
		    else
		    {
		        var str = trim(document.getElementById("customerFax").value);
		        if(str.indexOf("--")!=-1)
		        {
					breturn = false;
		    		strError += "传真格式不正确,中间不能存在两个'--'!\r\n";
		        }
		        if(str.indexOf(",,")!=-1)
		        {
					breturn = false;
		    	    strError += "传真格式不正确,中间不能存在两个',,'!\r\n";
		        }
		    }
		} 
 
			var re = /^[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+\.?$/;	
			if(trim(document.getElementById("httpUrl").value)!= "")
			{		   
				var str = trim(document.getElementById("httpUrl").value);
			 	
			 	
				if(checkChinese(str)==true)
				{
					var n = str.indexOf(':');
					if(n!=str.length-3)
					{
						if(n>0)
						{
							str = str.substring(n+3,str.length-1);
						}
						if(!re.test(trim(str)))
						{
							breturn = false;
		    				strError += "网站域名格式不正确!\r\n";
						}
					}
				}
 
			} 
		 
		if(trim(document.getElementById("product").value) == "")
		{
			breturn = false;
			strError += "请填写经营产品!\r\n";
		} 
		 	 
		if(strError!="")
		{
			alert(strError);
		}
		return breturn;
}

function CheckForm2()
	{
		var breturn = true;
		var strError = "";
		if(trim(document.getElementById("linkManName").value) == "")
		{
			breturn = false;
			strError += "请填写联系人!\r\n";
		}
		if(trim(document.getElementById("linkManName").value)<2)
		{
			breturn = false;
			strError += "联系人应该大于2个字符!\r\n";
		}
		if(trim(document.getElementById("linkManMobile").value) == "")
		{
				breturn = false;
		    	strError += "请填写手机!\r\n";
		}
		if(trim(document.getElementById("linkManZip").value)!= "")
		{
			var re = /^\d{6}$/;
		    if(!re.test(trim(document.getElementById("linkManZip").value)))
		    {
		        breturn = false;
		    	strError += "邮政编码格式不正确!\r\n";
		    }
		}	
		if(trim(document.getElementById("linkManQq").value)!= "")
		{
			var re = /^([0-9])([0-9-,])*([0-9])$/;
		    if(!re.test(trim(document.getElementById("linkManQq").value)))
		    {
		        breturn = false;
		    	strError += "QQ号码格式不正确!\r\n";
		    }
		}		 
		if(trim(document.getElementById("linkManMobile").value)!= "")
		{
			var re = /^([0-9])([0-9-,])*([0-9])$/;
		    if(!re.test(trim(document.getElementById("linkManMobile").value)))
		    {
		        breturn = false;
		    	strError += "手机格式不正确!\r\n";
		    }
		    else
		    {
		        var str = trim(document.getElementById("linkManMobile").value);
		        if(str.indexOf("--")!=-1)
		        {
					breturn = false;
		    		strError += "手机不正确,中间不能存在两个'--'!\r\n";
		        }
		        if(str.indexOf(",,")!=-1)
		        {
					breturn = false;
		    	    strError += "手机不正确,中间不能存在两个',,'!\r\n";
		        }
		    }
		} 
 
		if(trim(document.getElementById("linkManTel").value)!= "")
		{
			var re = /^([0-9])([0-9-,])*([0-9])$/;
		    if(!re.test(trim(document.getElementById("linkManTel").value)))
		    {
		        breturn = false;
		    	strError += "电话不正确!\r\n";
		    }
		    else
		    {
		        var str = trim(document.getElementById("linkManTel").value);
		        if(str.indexOf("--")!=-1)
		        {
					breturn = false;
		    		strError += "电话不正确,中间不能存在两个'--'!\r\n";
		        }
		        if(str.indexOf(",,")!=-1)
		        {
					breturn = false;
		    	    strError += "电话不正确,中间不能存在两个',,'!\r\n";
		        }
		    }
		} 
	    if(trim(document.getElementById("linkManFax").value)!= "")
		{
			var re = /^([0-9])([0-9-,])*([0-9])$/;
		    if(!re.test(trim(document.getElementById("linkManFax").value)))
		    {
		        breturn = false;
		    	strError += "传真格式不正确!\r\n";
		    }
		    else
		    {
		        var str = trim(document.getElementById("linkManFax").value);
		        if(str.indexOf("--")!=-1)
		        {
					breturn = false;
		    		strError += "传真格式不正确,中间不能存在两个'--'!\r\n";
		        }
		        if(str.indexOf(",,")!=-1)
		        {
					breturn = false;
		    	    strError += "传真格式不正确,中间不能存在两个',,'!\r\n";
		        }
		    }
		} 		
		if(trim(document.getElementById("linkManMsn").value)!= "")
		{
			var re = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/; 
		    if(!re.test(trim(document.getElementById("linkManMsn").value)))
		    {
		        breturn = false;
		    	strError += "MSN号码格式不正确!\r\n";
		    }
		}	 	 
		if(trim(document.getElementById("linkManEmail").value)!= "")
		{
			var re = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/; 
		    if(!re.test(trim(document.getElementById("linkManEmail").value)))
		    {
		        breturn = false;
		    	strError += "Email号码格式不正确!\r\n";
		    }
		}
			 	 
		 
		if(strError!="")
		{
			alert(strError);
		}
		return breturn;
	}

function checkChinese(str) {
	var f = true;
	var re = /[\u4E00-\u9FA0]+/;
	for (i = 0; i < str.length; i++) {
		if (str.substring(i, i + 1)) {
			var s = str.substring(i, i + 1);
			if (re.test(s)) {
				f = false;
			}
		}
	}
	return f;
}
function checkChinese1(str) {
	var f = false;
	var re = /[\u4E00-\u9FA0]+/;
	for (i = 0; i < str.length; i++) {
		if (str.substring(i, i + 1)) {
			var s = str.substring(i, i + 1);
			if (!re.test(s)) {
				f = true;
			}
		}
	}
	return f;
}
 
	//过滤空格
function trim(str) {
	return str.replace(/(^([\s|　]*)|([\s|　])*$)/g, "");
}

function checkCustomer(){
	if(document.getElementById("customerName").value==""){
		document.getElementById('msgDiv').innerHTML="<div><font color='red'>客户名称不能为空！</font></div>";
 		//document.getElementById("customerName").focus();
 		return false;
 	}
 	var customerName = trim(document.getElementById("customerName").value);
 	document.getElementById("customerName").value = customerName;  
 	customerProtectAction.checkCustomer(customerName, callback);
}

function callback(employeeId){
   employeeId = parseInt(employeeId)
   if(employeeId > 0){
   		document.getElementById('msgDiv').innerHTML="<div><font color='red'>客户已是别人的保护客户，不能添加！</font></div>";
   }else if(employeeId == 0){
   		document.getElementById('msgDiv').innerHTML="<div><font color='green'>客户已在客户池里，请去客户池领用！</font></div>";
   }else if(employeeId == -2){
   		document.getElementById('msgDiv').innerHTML="<div><font color='blue'>客户已是你的保护客户，不用添加，可以直接做拜访、约见！</font></div>";
   }else if(employeeId == -1){
   		document.getElementById('msgDiv').innerHTML="<div><font color='green'>客户已是部门客户，请联系销售经理分配客户！</font></div>";
   }else if(employeeId == -10){
   		document.getElementById('msgDiv').innerHTML="<div><font color='red'>客户名称格式不正确，请重新输入！</font></div>";
   }else{
   		document.getElementById('msgDiv').innerHTML="<div><font color='green'>客户不存在，可以添加！</font></div>";	
   }
}


</script>

</head>
<body>

<jsp:include page="/jsp/Top.jsp" flush="true" />

<form name="form1" method="post">

<div class="form_info">
	<div class="bg_layer" style="display:none;">
        <p>提交成功</p>
        <div class="btnBox"><input type="button" value="确 定" class="btnO_inp">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="再填写一个" class="btnO_inp"></div>
	</div>
	<div class="formTit"><h3><a href="#" class="addxx2">添加客户信息</a><a href="<%=request.getContextPath() %>/talkCusAction!check.html" class="addxx1">添加约见信息</a></h3></div>
	
    <div class="formTab">
    	<table style="table-layout:fixed;">
        	<tr>
            	<th><font>*</font>客户名称</th>
                <td><input type="text" class="formTab_input" name="customerName" id="customerName" onblur="checkCustomer()"></td>
            </tr>
            
            <div id="msgDiv"></div>
            
            <tr>
            	<th><font>*</font>客户地址</th>
                <td><input type="text" class="formTab_input" name="customerAddr" id="customerAddr"></td>
            </tr>
            <tr>
            	<th><font>*</font>客户电话</th>
                <td><input type="text" class="formTab_input" name="customerTel" id="customerTel"></td>
            </tr>
            <tr>
            	<th><font>*</font>经营产品</th>
                <td><input type="text" class="formTab_input" name="product" id="product"></td>
            </tr>
            
            <tr style="display:none">
            	<th>客户别名</th>
                <td><input type="text" class="formTab_input" name="mainName" id="mainName"></td>
            </tr>
            <tr style="display:none">
            	<th>邮政编码</th>
                <td><input type="text" class="formTab_input" name="zip" id="zip"></td>
            </tr>
            <tr style="display:none">
            	<th>客户传真</th>
                <td><input type="text" class="formTab_input" name="customerFax" id="customerFax"></td>
            </tr>
            <tr style="display:none">
            	<th>公司规模</th>
                <td>
                 <select name="scale" id="scale" class="formTab_sel">
					<option selected="selected" value="请选择">请选择</option>
					<option value="少于8人">少于8人</option>
					<option value="8 - 20人">8 - 20人</option>
					<option value="21 - 100人">21 - 100人</option>
					<option value="101 - 200人">101 - 200人</option>
					<option value="201 - 500人">201 - 500人</option>
					<option value="501 - 1000人">501 - 1000人</option>
					<option value="多于1000人">多于1000人</option>
				</select>
                </td>
            </tr>
            <tr style="display:none">
            	<th>客户网站</th>
                <td><input type="text" class="formTab_input" name="httpUrl" id="httpUrl"></td>
            </tr>
            <tr style="display:none">
            	<th>负责人</th>
                <td><input type="text" class="formTab_input" name="customerManager" id="customerManager"></td>
            </tr>
            
            <tr>
            	<td colspan="2">
                	<div class="open js_open1">展开</div>
                </td>
            </tr>
        </table>
    </div>
    <div class="formTit"><h3>添加联系人信息</h3></div>
    <div class="formTab">
    	<table>
        	<tr>
            	<th><font>*</font>联系人</th>
                <td><input type="text" class="formTab_input" name="linkManName" id="linkManName"></td>
            </tr>
            <tr>
            	<th><font>*</font></th>
                <td>
					<span class="maintouch">
						<input type="radio" name="linkManType" id="linkManType" value="1" checked/>
						<label class="on">是</label>
						<input type="radio" name="linkManType" id="linkManType" value="0"/>						
						<label>否</label>
					</span>
					主要联系人
				</td>
            </tr>
            <tr>
            	<th><font>*</font>手机</th>
                <td><input type="text" class="formTab_input" name="linkManMobile" id="linkManMobile"></td>
            </tr>
            <tr  style="display:none">
            	<th><font>*</font>性别</th>
                <td>
					<span class="sex">
						<input type="radio" name="linkManSex" id="linkManSex" value="0" checked/>
						<label class="on">男</label>
						<input type="radio" name="linkManSex" id="linkManSex" value="1"/>						
						<label>女</label>
					</span>
				</td>
            </tr>
            <tr style="display:none">
            	<th>客户类别</th>
                <td>
                <select name="linkManCategory" id="linkManCategory" class="formTab_sel">
					<option value="1">老板</option>
					<option value="2">负责人</option>
				</select>
				</td>
            </tr>
            <tr style="display:none">
            	<th>职务</th>
                <td><input type="text" class="formTab_input" name="item1" id="item1"></td>
            </tr>
            <tr style="display:none">
            	<th>电话</th>
                <td><input type="text" class="formTab_input" name="linkManTel" id="linkManTel"></td>
            </tr>
            <tr style="display:none">
            	<th>email</th>
                <td><input type="text" class="formTab_input" name="linkManEmail" id="linkManEmail"></td>
            </tr>
            <tr style="display:none">
            	<th>传真</th>
                <td><input type="text" class="formTab_input" name="linkManFax" id="linkManFax"></td>
            </tr>
            <tr style="display:none">
            	<th>QQ</th>
                <td><input type="text" class="formTab_input" name="linkManQq" id="linkManQq"></td>
            </tr>
            <tr style="display:none">
            	<th>MSN</th>
                <td><input type="text" class="formTab_input" name="linkManMsn" id="linkManMsn"></td>
            </tr>
            <tr style="display:none">
            	<th>地址</th>
                <td><input type="text" class="formTab_input" name="linkManAdd" id="linkManAdd"></td>
            </tr>
            <tr style="display:none">
            	<th>邮编</th>
                <td><input type="text" class="formTab_input" name="linkManZip" id="linkManZip"></td>
            </tr>
            <tr>
            	<td colspan="2">
                	<div class="open js_open2">展开</div>
                </td>
            </tr>
        </table>
    </div>
    
    <br/>
    <br/>
    
    <div class="submit_btn"><input type="button" value="提  交" class="submit_btn_inp" onclick="addCustomer()"></div>
</div>

</form>

<script>
	//var tr=$('.js_open').parents('table').find('tr');
	$('.js_open1').toggle(function(){
		$(this).parents('table').find('tr').show();
		$(this).text('收起');
	},function(){
		var tr=$(this).parents('table').find('tr');
		for(var i=0;i<tr.length;i++){
			if($(tr[i]).index()>4){
				$(tr[i]).hide();
			}
		}
		$(tr[tr.length-1]).show();
		$(this).text('展开');
	})
	$('.js_open2').toggle(function(){
		$(this).parents('table').find('tr').show();
		$(this).text('收起');
	},function(){
		var tr=$(this).parents('table').find('tr');
		for(var i=0;i<tr.length;i++){
			if($(tr[i]).index()>2){
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
</body>
</html>
