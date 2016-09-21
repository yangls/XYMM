<%@ page language="java" import="java.util.*" pageEncoding="utf8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
      
  <link rel="stylesheet" href="<%=request.getContextPath()%>/source/jquery-easyui-1.4.4/themes/black/easyui.css" type="text/css"></link>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/source/jquery-easyui-1.4.4/themes/icon.css" type="text/css"></link>
  <script type="text/javascript" src="<%=request.getContextPath()%>/source/jquery-easyui-1.4.4/jquery.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/source/jquery-easyui-1.4.4/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/source/jquery-easyui-1.4.4/locale/easyui-lang-zh_CN.js"></script>

