<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.util.ArrayList , com.mainone.model.*, java.util.*"%>
<%@page import="com.mainone.util.*,com.mainone.app.admin.*"%>
<%@page import="com.mainone.common.ToolsApp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String dateStr = (String)request.getAttribute("dateStr");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>销售管理系统</title>
<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet" type="text/css" />
<script src="<%=request.getContextPath() %>/js/jquery.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/js/Calendar.js"  type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/js/move.js"  type="text/javascript"></script>
<script  type="text/javascript"> 
function change2(){
 
document.getElementById("a6").style.color='#496e93';
document.getElementById("a7").style.color='#000';
document.getElementById("a8").style.color='#496e93';
}
 
function change1(){
 
document.getElementById("a6").style.color='#000';
document.getElementById("a7").style.color='#496e93';
document.getElementById("a8").style.color='#496e93';
}
function change3(){
 
document.getElementById("a6").style.color='#496e93';
document.getElementById("a7").style.color='#496e93';
document.getElementById("a8").style.color='#000';
}
</script>
 
<script type="text/JavaScript"> 
<!--
var SelectAllFlag = false;
 
function goToForAllSelect(){
 var doc = document.form1;
 var l=doc.checkbox.length
 if(l==null)
 {
  l=1;
 }
 
 if(l==1){
   if(!SelectAllFlag){
     doc.checkbox.checked = true;
   }else{
   doc.checkbox.checked = false;}
 }else{
   for(i = 0; i < l; i++){
    if(!SelectAllFlag){
     doc.checkbox[i].checked = true;
    }else{
     doc.checkbox[i].checked = false;
     }
 
   }
 
 }
 if(!SelectAllFlag){
  SelectAllFlag = true;
  doc.quan.value="反 选";
 }else{
  SelectAllFlag = false;
  doc.quan.value="全 选";
 
 }
 
}
-->
</script>
<script type="text/JavaScript"> 
<!--
var SelectAllFlag = false;
 
function goToForAllSelect1(){
 var doc = document.form2;
 var l=doc.checkbox.length
 if(l==null)
 {
  l=1;
 }
 
 if(l==1){
   if(!SelectAllFlag){
     doc.checkbox.checked = true;
   }else{
   doc.checkbox.checked = false;}
 }else{
   for(i = 0; i < l; i++){
    if(!SelectAllFlag){
     doc.checkbox[i].checked = true;
    }else{
     doc.checkbox[i].checked = false;
     }
 
   }
 
 }
 if(!SelectAllFlag){
  SelectAllFlag = true;
  doc.quan.value="反 选";
 }else{
  SelectAllFlag = false;
  doc.quan.value="全 选";
 
 }
 
}
-->
</script>
<script type="text/javascript">
var timeout         = 500;
var closetimer		= 0;
var ddmenuitem      = 0;
 
function jsddm_open()
{	jsddm_canceltimer();
	jsddm_close();
	ddmenuitem = $(this).find('ul').eq(0).css('visibility', 'visible');}
 
function jsddm_close()
{	if(ddmenuitem) ddmenuitem.css('visibility', 'hidden');}
 
function jsddm_timer()
{	closetimer = window.setTimeout(jsddm_close, timeout);}
 
function jsddm_canceltimer()
{	if(closetimer)
	{	window.clearTimeout(closetimer);
		closetimer = null;}}
 
$(document).ready(function()
{	$('#jsddm > li').bind('mouseover', jsddm_open);
	$('#jsddm > li').bind('mouseout',  jsddm_timer);});
 
document.onclick = jsddm_close;
  </script>
</head>
 
<body>
<!--logobar-->
<div class="logobar">
  <a href="#" class="logo left"><img src="<%=request.getContextPath() %>/images/logo3.gif" /></a>
  <span class="login right">Rachel，您好! <a href="#">[注销]</a></span>
</div>
<!-- end of logobar-->
<!--navbar-->
<div class="navbar">
	<ul id="jsddm" >
    	<li><a href="<%=request.getContextPath() %>/index.html" class="a1 focus">HOME</a>
		</li>
       <li><a href="#" class="a1">客户管理</a>
		   <ul>
			   <li><a href="<%=request.getContextPath() %>/kehu/baohukehu.html">保护客户</a></li>
               <li><a href="<%=request.getContextPath() %>/kehu/bumenkehu.html">部门客户</a></li>
               <li><a href="<%=request.getContextPath() %>/kehu/lizhikehu.html" >离职员工客户再分配</a></li>
               <li><a href="<%=request.getContextPath() %>/kehu/chengjiaokehu.html" >成交客户</a></li>
               <li><a href="<%=request.getContextPath() %>/kehu/kehuchiguanli.html" class="laster">客户领用</a></li>
			</ul>
		</li>
        <li><a href="#" class="a2" >销售管理</a>
		     <ul>
			   <li><a href="<%=request.getContextPath() %>/xiaoshouguanli/yixiangdianping.html">跟进客户点评</a></li>
               <li><a href="<%=request.getContextPath() %>/xiaoshouguanli/baifangdianping.html">拜访客户点评</a></li>
			</ul>
		</li>
		<li><a href="#" class="a2" >销售报表</a>
		     <ul>
               <li><a href="<%=request.getContextPath() %>/daozhangjilu/gerendaozhang1.html">到账龙虎榜</a></li>
               <li><a href="<%=request.getContextPath() %>/daozhangjilu/bumendaozhang.html">部门到账统计</a></li>  
               <li><a href="<%=request.getContextPath() %>/xiaoshouloudou/xiaoshoutongji.html">销售漏斗统计</a></li>    
			   <li><a href="<%=request.getContextPath() %>/KPI/KPIchakan.html" class="laster">KPI查看</a></li>
               
			</ul>
		</li>
        <li><a href="#" class="a1" >任务/计划</a>
		     <ul>
               <li><a href="zhidingrenwu.html">制定任务</a></li>   
               <li><a href="renwuchaxun.html"class="laster">任务查询</a></li>  
			</ul>
		</li>
        <li class="last"><a href="#" class="a1" >日常数据</a>
		    <ul>
               <li><a href="gongzuoguanli.html" class="laster">员工每日工作管理</a></li>
			</ul>
		</li>
         
    </ul>
</div>
 
<!--end of navbar-->
<div class="clearfix"></div>
<!--index-->
<div  class="container">
<div class="baobiao2" style="text-align:left; font-weight:bold;"><h1>当前位置：日常数据 > 员工每日工作管理</h1></div>
 
  <div  class="tabwrap" style="margin:0px;">
     <div class="tab_title2">
 
      <ul id="card0">
        <li><a href="gongzuoguanli.html" class="focus">员工每日工作记录</a></li>
        <li><a href="gongzuoguanli1.html">工作情况查看</a></li>
      </ul>
    </div>
    <div class="tablist" id="card00" style="display:block;">
	  <div class="tablist">  
                <form name="form1" method="post"> 
                <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="text1">
  <tr >
								 <td height="33" style=" border:1px solid #91d8e4;">
								   <div class="tab_title4">
      
      									<%=dateStr %>
      
    </div>
	</td>
	</tr>                  
				</table> 
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="text2">	
				<tr >
		<th width="2%" height="30" rowspan="2" class="tr_th3" style="width:3%;" ><b>选择</b></th>
		<th width="5%"height="30" rowspan="2" class="tr_th3" style="width:7%;"><b>日期</b></th>
		<th width="3%" height="30" rowspan="2" class="tr_th3" style="width:5%;" ><b>姓名</b></th>
		<th height="30" colspan="3"  class="tr_th2" style="width:20%;"><b>客户资料情况</b></th>
        <th height="30" colspan="3" class="tr_th2" style="width:20%;"><b>工作情况</b></th>
		<th height="30" colspan="2" class="tr_th2" style="width:15%;"><b>考勤</b></th>
        <th height="30" colspan="2" class="tr_th2" style="width:20%;"><b>工作状态</b></th>
		<th height="30" rowspan="2" class="tr_th3" style="width:10%;"><b>操作</b></th>
	</tr>
    <tr class="tr_th2" >
        <th height="28" width="6%" ><b>关键词</b></th>
		<th width="5%" height="28"><b>法人</b></th>
		<th width="6%" height="28"><b>数量</b></th>
		<th width="7%" height="28"><b>电话量</b></th>
        <th height="28" width="6%"><b>意向客户数</b></th>
		<th width="7%" height="28"><b>拜访客户数</b></th>
        <th width="7%" height="28"><b>考勤状态</b></th>
		<th width="9%" height="28" ><b>备注</b></th>
        <th width="7%" height="28"><b>工作状态</b></th>
		<th width="7%" height="28" ><b>对员工评价</b></th>
    </tr>
    <tr >
    <td height="30" class="tr_td"> <input name="checkbox" type="checkbox" class="noborder" value="checkbox"></td>
    <td class="tr_td">2013-03-14</td>
    <td class="tr_td">姜大伟</td>
    <td class="tr_td"><input name="key" type="text" value="黄金"size="8" /></td>
    <td class="tr_td"><select name="faren">
               <option value="30" selected="selected">是</option>
               <option value="31">否</option>
        </select></td>
    <td class="tr_td"><input name="num" type="text" value="20" size="8"/></td>
    <td class="tr_td"><input name="phone" type="text" value="40" size="8"/></td>
    <td class="tr_td"><input name="yixiang" type="text" value="10" size="8"/></td>
    <td class="tr_td"><input name="visit" type="text" value="25" size="8"/></td>
    <td class="tr_td"><select name="kaoqin">
               <option value="20" selected="selected">准时</option>
               <option value="21">迟到</option>
               <option value="22">事假</option>
               <option value="23">病假</option>
        </select>
    </td>
    <td class="tr_td"><textarea name="beizhu" cols="10" rows="2"></textarea>
    </td>
    <td class="tr_td"><select name="work">
               <option value="24" selected="selected">良好</option>
               <option value="25">迟到</option>
               <option value="26">事假</option>
               <option value="27">病假</option>
        </select>
    </td>
    <td class="tr_td"><textarea name="shuoming" cols="10" rows="2"></textarea>
    </td>
    <td class="tr_td" nowrap="nowrap"> <input name="Submit2" type="button" class="ButtonOrange2" value="修改"  >&nbsp;&nbsp; <input name="Submit2" type="button" class="ButtonOrange2" value="添加" style="display:none;"  >
    </td>
    </tr>
     <tr >
    <td height="30" class="tr_td"> <input name="checkbox" type="checkbox" class="noborder" value="checkbox"></td>
    <td class="tr_td">2013-03-14</td>
    <td class="tr_td">张欣</td>
    <td class="tr_td"><input name="key" type="text" value="键盘"size="8" /></td>
    <td class="tr_td"><select name="faren">
               <option value="30" >是</option>
               <option value="31" selected="selected">否</option>
        </select></td>
    <td class="tr_td"><input name="num" type="text" value="20" size="8"/></td>
    <td class="tr_td"><input name="phone" type="text" value="40" size="8"/></td>
    <td class="tr_td"><input name="yixiang" type="text" value="10" size="8"/></td>
    <td class="tr_td"><input name="visit" type="text" value="25" size="8"/></td>
    <td class="tr_td"><select name="kaoqin">
               <option value="20">准时</option>
               <option value="21" selected="selected">迟到</option>
               <option value="22">事假</option>
               <option value="23">病假</option>
        </select>
    </td>
    <td class="tr_td"><textarea name="beizhu" cols="10" rows="2"></textarea>
    </td>
    <td class="tr_td"><select name="work">
               <option value="24">良好</option>
               <option value="25" selected="selected">迟到</option>
               <option value="26">事假</option>
               <option value="27">病假</option>
        </select>
    </td>
    <td class="tr_td"><textarea name="shuoming" cols="10" rows="2"></textarea>
    </td>
    <td class="tr_td"><input name="Submit2" type="button" class="ButtonOrange2" value="修改"  >&nbsp;&nbsp; <input name="Submit2" type="button" class="ButtonOrange2" value="添加"  style="display:none;">
    </td>
    </tr>
    <tr >
    <td height="30" class="tr_td"> <input name="checkbox" type="checkbox" class="noborder" value="checkbox"></td>
    <td class="tr_td">2013-03-14</td>
    <td class="tr_td">任莹</td>
    <td class="tr_td"><input name="key" type="text" value="平板电脑"size="8" /></td>
    <td class="tr_td"><select name="faren">
               <option value="30" >是</option>
               <option value="31" selected="selected">否</option>
        </select></td>
    <td class="tr_td"><input name="num" type="text" value="20" size="8"/></td>
    <td class="tr_td"><input name="phone" type="text" value="40" size="8"/></td>
    <td class="tr_td"><input name="yixiang" type="text" value="10" size="8"/></td>
    <td class="tr_td"><input name="visit" type="text" value="25" size="8"/></td>
    <td class="tr_td"><select name="kaoqin">
               <option value="20">准时</option>
               <option value="21">迟到</option>
               <option value="22" selected="selected">事假</option>
               <option value="23">病假</option>
        </select>
    </td>
    <td class="tr_td"><textarea name="beizhu" cols="10" rows="2"></textarea>
    </td>
    <td class="tr_td"><select name="work">
               <option value="24">良好</option>
               <option value="25">迟到</option>
               <option value="26" selected="selected">事假</option>
               <option value="27">病假</option>
        </select>
    </td>
    <td class="tr_td"><textarea name="shuoming" cols="10" rows="2"></textarea>
    </td>
    <td class="tr_td"> <input name="Submit2" type="button" class="ButtonOrange2" value="修改"  >&nbsp;&nbsp; <input name="Submit2" type="button" class="ButtonOrange2" value="添加" style="display:none;" >
    </td>
    </tr>
     <tr >
    <td height="30" class="tr_td"> <input name="checkbox" type="checkbox" class="noborder" value="checkbox"></td>
    <td class="tr_td">2013-03-14</td>
    <td class="tr_td">乔晓东</td>
    <td class="tr_td"><input name="key" type="text" value="手机"size="8" /></td>
    <td class="tr_td"><select name="faren">
               <option value="30" selected="selected">是</option>
               <option value="31">否</option>
        </select></td>
    <td class="tr_td"><input name="num" type="text" value="20" size="8"/></td>
    <td class="tr_td"><input name="phone" type="text" value="40" size="8"/></td>
    <td class="tr_td"><input name="yixiang" type="text" value="10" size="8"/></td>
    <td class="tr_td"><input name="visit" type="text" value="25" size="8"/></td>
    <td class="tr_td"><select name="kaoqin">
               <option value="20">准时</option>
               <option value="21">迟到</option>
               <option value="22">事假</option>
               <option value="23" selected="selected">病假</option>
        </select>
    </td>
    <td class="tr_td"><textarea name="beizhu" cols="10" rows="2"></textarea>
    </td>
    <td class="tr_td"><select name="work">
               <option value="24">良好</option>
               <option value="25">迟到</option>
               <option value="26">事假</option>
               <option value="27" selected="selected">病假</option>
        </select>
    </td>
    <td class="tr_td"><textarea name="shuoming" cols="10" rows="2"></textarea>
    </td>
    <td class="tr_td"><input name="Submit2" type="button" class="ButtonOrange2" value="修改"  >&nbsp;&nbsp; <input name="Submit2" type="button" class="ButtonOrange2" value="添加"   style="display:none;">
    </td>
    </tr>
    <tr>
    <td colspan="14">
    <table width="100%" style="margin-top:20px;">
	<tr height="1" >
		<td bgcolor="#c2d8f0" colspan="4"></td>
	</tr>
	<tr height="40">
		<td height="35" width="10%" style="text-align:left;"><input type="button" name="quan" value="全 选" onClick="goToForAllSelect()" class="ButtonOrange2"> </td>
                              <td  width="7%" style="text-align:left;">&nbsp;&nbsp;批量操作：</td>
                              <td width="9%" style="text-align:left;">&nbsp;&nbsp;
                                <input name="Submit2" type="button" class="ButtonOrange2" value="修改"  >
                              </td>
                              
                              <td width="50%">
<table width="100%" border="0" cellpadding="0" cellspacing="0"><tr><td style="text-align:right;">首页&nbsp;上页&nbsp;<a href="">下页</a>&nbsp;<a href="">末页</a>&nbsp;&nbsp; 共73条记录  每页<SELECT size=1 name=pageSize >
<OPTION value=3>3</OPTION>
<OPTION value=10 selected>10</OPTION>
<OPTION value=20>20</OPTION>
<OPTION value=30>30</OPTION>
<OPTION value=50>50</OPTION>
<OPTION value=100>100</OPTION>
</SELECT>条 分8页显示 转到<SELECT size=1 name=Pagelist >
<OPTION value=1 selected>1</OPTION>
<OPTION value=2>2</OPTION>
<OPTION value=3>3</OPTION>
<OPTION value=4>4</OPTION>
<OPTION value=5>5</OPTION>
<OPTION value=6>6</OPTION>
<OPTION value=7>7</OPTION>
<OPTION value=8>8</OPTION>
</SELECT>页<INPUT type=hidden  value=1 name="currentPage" > <INPUT type=hidden  value=10 name="pageSize"> &nbsp;&nbsp;</td></tr></table>
</td>
</tr></table></td></tr></table></form></div></div>
</div></div>
  <!--end of index-->
<!--footer-->
<div id="footer">
<span class="fleft"></span>
<span class="fright"></span>
</div>
<!--end of footer-->
</body>
</html>
 

