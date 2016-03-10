<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="<%=path%>/js/jquery-1.7.2.js"></script>
		<script language="javascript">
function getMsg() {
	$.ajax( { 
		url : 'jsonCommonAction!searchUuid.html',
		type : 'post',
		dataType : 'json',
		success : function(data) {
			$("#result").html(data.message);
		}
	});
}

function getUser() {
	$("#result").html("");
	$.ajax( { 
		url : 'jsonCommonAction!search.html',
		type : 'post',
		dataType : 'json',
		success :function(data) {
		   $.each(data.talkList,function(i,value){   
			
			 $("#result").append("第"+(i+1)+"个用户")
	                      .append("编号："+value.DepartID+"")
                          .append("用户名："+value.TRUE_NAME+"")
                          .append("时间："+value.CreateTime+"")
                          .append("<br/>");
                          ;	
		});		
		}
	});
}

    
    function getUserList() {
	$("#result").html("");
	$.ajax({
         url:'jsonCommonAction!returnList.html', 
         type:'post',
         dataType:'json', 
         success:function(data){ 
           $.each(data.userList,function(i,value){   
	          $("#result").append("第"+(i+1)+"个用户")
	                      .append("编号："+value.id+"")
                          .append("用户名："+value.name+"")
                          .append("密码："+value.password+"")
                          .append("<br/>");
                          ;
           });
         }
      });
    }
</script>
	</head>

	<body> 

		<div id="result"></div>
		<input type="button" value="获得单个消息" onclick="getMsg()" />
		<input type="button" value="获得用户信息" onclick="getUser()" />
		<input type="button" value="获得用户列表" onclick="getUserList()" />
	</body>
</html>
