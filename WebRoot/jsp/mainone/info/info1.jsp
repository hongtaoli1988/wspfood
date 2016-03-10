<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.util.ArrayList , com.mainone.model.*, java.util.*"%>
<%@page import="com.mainone.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String message = (String)request.getAttribute("message"); 
	String path =(String)request.getAttribute("path");
	String path1 =(String)request.getAttribute("path1");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title></title>
    
  </head>
  
  <body>
  	
  </body>
</html>

<SCRIPT LANGUAGE=javascript>

		
info();
function info(){
	var message = "<%=message%>";
	var path = "<%=path%>";
	var path1 = "<%=path1%>";
	if(confirm(message)){ 
	    document.location = path;
	} 
	else{
		document.location = path1;
	}
}
</SCRIPT>
