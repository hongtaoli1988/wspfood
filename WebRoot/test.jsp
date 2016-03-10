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
			
			 $("#result").append("��"+(i+1)+"���û�")
	                      .append("��ţ�"+value.DepartID+"")
                          .append("�û�����"+value.TRUE_NAME+"")
                          .append("ʱ�䣺"+value.CreateTime+"")
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
	          $("#result").append("��"+(i+1)+"���û�")
	                      .append("��ţ�"+value.id+"")
                          .append("�û�����"+value.name+"")
                          .append("���룺"+value.password+"")
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
		<input type="button" value="��õ�����Ϣ" onclick="getMsg()" />
		<input type="button" value="����û���Ϣ" onclick="getUser()" />
		<input type="button" value="����û��б�" onclick="getUserList()" />
	</body>
</html>
