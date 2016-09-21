<%@ page language="java" import="java.util.*" pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="description" content="">
  <meta name="keywords" content="">
  <meta name="viewport"
        content="width=device-width, initial-scale=1">
    <title>校园妈妈</title>
    <meta name="viewport" content="width=device-width,initial-scale=1 user-scalable=0"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="校园妈妈,校园妈妈,keyword3">
	<meta http-equiv="description" content="校园妈妈">
	<!-- Set render engine for 360 browser -->
  <meta name="renderer" content="webkit">

  <!-- No Baidu Siteapp-->
  <meta http-equiv="Cache-Control" content="no-siteapp"/>

  <link rel="icon" type="image/png" href="assets/i/favicon.png">

  <!-- Add to homescreen for Chrome on Android -->
  <meta name="mobile-web-app-capable" content="yes">
  <link rel="icon" sizes="192x192" href="amazeui/assets/i/app-icon72x72@2x.png">

  <!-- Add to homescreen for Safari on iOS -->
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <meta name="apple-mobile-web-app-title" content="校园妈妈"/>
  <link rel="apple-touch-icon-precomposed" href="amazeui/assets/i/app-icon72x72@2x.png">

  <!-- Tile icon for Win8 (144x144 + tile color) -->
  <meta name="msapplication-TileImage" content="amazeui/assets/i/app-icon72x72@2x.png">
  <meta name="msapplication-TileColor" content="#0e90d2">

  <link rel="stylesheet" href="amazeui/assets/css/amazeui.min.css">
  <link rel="stylesheet" href="amazeui/assets/css/app.css">
  
  
  <style type="text/css">
html body{
height:100%;
}  

  
  </style>
  <script src="amazeui/assets/js/jquery.min.js"></script>

<script src="amazeui/assets/js/amazeui.min.js"></script>
<script type="text/javascript">
function funVote(a){
	var aid='${ac.id}';
	
	var b=window.confirm("确定投票给"+a+"号?");
	if(b){
		//传用户编号，投票者手机号,活动id
$.ajax({
  type: 'POST',
  url: '<%=request.getContextPath()%>/apiactivity/addVote.do',
  data: {"id":aid,"phone":"${phone}","userSerialNo":a},
  success: function(result){
    alert(result.msg); 
    if(result.ret==0){
    	$("#goVote_form").submit(); 
    	
    }
  },
  dataType:'json'
});
	}
}
</script>
  </head>
  
  <body>
  
    <header data-am-widget="header" class="am-header am-header-default">
      
      <div class="am-header-title">
          <font size="4">${ac.activityName}</font>  
      </div>
  </header>
 <form id="goVote_form" action="<%=request.getContextPath()%>/apiactivity/activityvote.do" method="post">
	<input name="phone" type="hidden" value="${phone}">
	<input id="activityId" type="hidden" name="id" value="${ac.id}" >
</form>
  <div class="am-panel am-panel-default">
  <div class="am-panel-hd"><font color="red">活动介绍</font></div>
  <div class="am-panel-bd">
    &nbsp &nbsp &nbsp ${ac.activitySummary}<br/>
    </div>
</div>
<div class="am-panel am-panel-default">
  <div class="am-panel-hd"><font color="red">活动时间</font></div>
  <div class="am-panel-bd">
    ${startTime}—${endTime}<br/>
    </div>
</div>
 <div class="am-panel am-panel-default">
  <div class="am-panel-hd" style="background-color: red;">
  <font color="#FFFFFF">候选人简要信息</font></div>
  
</div>
  
   <section data-am-widget="accordion" class="am-accordion am-accordion-default" data-am-accordion='{  }'>
      <c:forEach items="${userList}" var="u" varStatus="idx">
      <dl class="am-accordion-item ">
        <dt class="am-accordion-title">
        <div class="am-g" style="background-color: #E0E0E0">
  <div class="am-u-sm-5" style="padding-left: 0px;"><img alt="" src="<%=request.getContextPath()%>/acicon/${u.userIconUrl}" width="120px" height="100px"></div>
  <div class="am-u-sm-4" style="line-height: 25px;margin-top: 10px;">
  <p><font style="font-weight: bold">${u.userName}</font></p>
  <p>票数:${u.vote}</p></div>
<div class="am-u-sm-3" style="text-align: center;line-height: 100px;"><font size="5">${u.userSerialNo}号</font> </div>
</div>
        </dt>
        <dd class="am-accordion-bd am-collapse <c:if test="${idx.index==0}"> am-in</c:if> ">
          <!-- 规避 Collapase 处理有 padding 的折叠内容计算计算有误问题， 加一个容器 -->
          <div class="am-accordion-content">
          简介：${u.userSummary}</div>
        </dd>
      </dl>
      <button type="button" class="am-btn am-btn-default am-btn-block" onclick="funVote(${u.userSerialNo});">我要投票</button>
      </c:forEach>
  </section>
  

  
  </div>
  <hr>
   <footer data-am-widget="footer"
          class="am-footer am-footer-default">
    
    <div class="am-footer-miscs ">

          <p>由 <a href="http://www.yunshipei.com/" title="school"
                                                target="_blank" class="">校园妈妈</a>
            提供技术支持</p>
        <p>CopyRight©2016  AllMobilize Inc.</p>
        
    </div>
  </footer>
  
  </body>
</html>
