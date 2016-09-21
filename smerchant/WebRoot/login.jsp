<%@ page language="java" import="java.util.*" pageEncoding="utf8"%>
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

		<title>校园妈妈-管理登陆</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<style type="text/css">
html {
	height: 100%;
	margin: 0;
}

body {
	
	margin: 0;
	background-color: white;
	/**
	height: 100%;
	background-image:
		url("<%=request.getContextPath()%>/source/img/home.png");
		*/
	background-repeat: no-repeat;
	background-position: center;
	background-attachment: fixed;
}
#xymmht{
margin-top: 20%;
font-weight: bolder;
color: red;
padding-left:65%;

font-size: 24px;
}
#loginDiv {
background-color:red;
	
	padding-left:65%;
	color: #F0F8FF;
	font-size: 14px;
}
#loginButton{
width: 145px;
border-bottom:1px solid #177EE5;
color: #008000;

}
table{
color: white;
}
#foot{
bottom: 0;
color: red;
position: fixed;
text-align: center;
width: 100%;
margin-bottom: 15px;
font-weight: bolder;
font-size: 24px;
}

</style>
<script type="text/javascript">
function changeCode() { 
	var imgNode = document.getElementById("vimg");
	imgNode.src = "<%=request.getContextPath()%>/xymm/authImageServlet.do?t=" + Math.random();  // 防止浏览器缓存的问题 
}
</script>
	</head>

	<body>
		
		<div id="xymmht">后台管理</div>
		<div id="loginDiv">
			<form action="<%=request.getContextPath()%>/xymm/login.do"
				method="post">
				
				<table>
				<tr>
				<td>用户:</td>
				<td><input name="user" /></td>
				</tr>
				
				<tr>
				<td>密码:</td>
				<td><input name="password" type="password" /></td>
				</tr>
				
				
				<tr>
				<td>验证码:</td>
				<td>	<input name="yzm" style="width: 70px"/>
			<img id="vimg"  title="点击更换" onclick="changeCode();" src="<%=request.getContextPath()%>/xymm/authImageServlet.do">
				</td>
				</tr>
				<tr>
				<td></td>
				<td><button id="loginButton" type="submit" >
					登录
				</button><font color="white">${msg}</font></td>
				</tr>
				</table>
				
			</form>
			
		</div >
		
		<div id="foot">
		校园妈妈☎
		</div>
	</body>
</html>
