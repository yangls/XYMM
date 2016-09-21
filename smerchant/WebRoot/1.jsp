<%@ page language="java" import="java.util.*" pageEncoding="utf8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP '1.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
</script>
  </head>
  
  <body>
    This is my JSP page. <br>
    上传图片
    <form action="upload/uploadImg.do" method="post" enctype="multipart/form-data">  
<input type="file" name="file" /> <input type="submit" value="Submit" /></form>  
删除图片
<form action="upload/deleteImg.do" method="post">  
<input type="text" name="name" value="8a5d5f46326541c5a9668e67d9bae27d" /> 
<input type="submit" value="Submit" /></form>  
获取验证码
<form action="account/getMsgReq.do" method="post">  
<input type="text" name="params" value="XQJejpjODvWcdNTrsO8keI6kk9eQDbgOu2zH2dzG4XWfXLiyEpfRhTbCcxbp hhqGEz3Gwp3eE42cpT73Peeo0A==" /> 
<input type="submit" value="Submit" /></form> 
商家注册，手机号验证码，密码
<form action="merchant/register.do" method="post">  
<input type="text" name="params" value="XQJejpjODvWcdNTrsO8keI6kk9eQDbgOu2zH2dzG4XWfXLiyEpfRhTbCcxbp hhqGEz3Gwp3eE42cpT73Peeo0A==" /> 
<input type="submit" value="Submit" /></form> 
登陆
<form action="merchant/login.do" method="post">  
<input type="text" name="params" value="XQJejpjODvWcdNTrsO8keI6kk9eQDbgOu2zH2dzG4XWfXLiyEpfRhTbCcxbp hhqGEz3Gwp3eE42cpT73Peeo0A==" /> 
<input type="submit" value="Submit" /></form>

  </body>
</html>
