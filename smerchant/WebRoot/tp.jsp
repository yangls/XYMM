<%@ page language="java" import="java.util.*" pageEncoding="utf8"%>
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
	
	var b=window.confirm("确定投票给"+a+"号?");
	if(b){
		alert("ok!");
	}else{
		alert("good");
	}
}
</script>
  </head>
  
  <body>
  
    <header data-am-widget="header" class="am-header am-header-default">
      <div class="am-header-left am-header-nav">
      </div>
      <div class="am-header-title">
          <font size="4">瞭妹王投票活动</font>  
      </div>
  </header>
 
  <div class="am-panel am-panel-default">
  <div class="am-panel-hd">活动介绍</div>
  <div class="am-panel-bd">
    音乐节是临时存在的音乐演出，有着临时存在、多位艺人轮流演出、人数多的必要条件。主要形式通常是在特定的地方用统一的内容，例如民族音乐、现代音乐或发扬某一杰出作曲家的作品，举行连续性的演出，音乐节意指持续数天和数周的、一种或几种艺术的庆祝聚会。 其中大多数每隔一段的时间并通常在同一地... 
  </div>
</div>
  
   <section data-am-widget="accordion" class="am-accordion am-accordion-default" data-am-accordion='{  }'>
      <dl class="am-accordion-item ">
        <dt class="am-accordion-title">
        <div class="am-g" style="background-color: #E0E0E0">
  <div class="am-u-sm-5" style="padding-left: 0px;"><img alt="" src="<%=request.getContextPath()%>/acicon/A1.jpg" width="120px" height="100px"></div>
  <div class="am-u-sm-4" style="line-height: 25px;margin-top: 10px;">
  <p>周杰伦</p>
  <p>得票数:49</p></div>
<div class="am-u-sm-3" style="text-align: center;line-height: 100px;"><font size="5">4号</font> </div>
</div>
        </dt>
        <dd class="am-accordion-bd am-collapse ">
          <!-- 规避 Collapase 处理有 padding 的折叠内容计算计算有误问题， 加一个容器 -->
          <div class="am-accordion-content">
          简介：周杰伦（Jay Chou），1979年1月18日出生于台湾省新北市，中国台湾流行乐男歌手、音乐人、演员、导演、编剧、监制、商人。2000年发行首张个人专辑《Jay》。</div>
        </dd>
      </dl>
      <button type="button" class="am-btn am-btn-default am-btn-block">我要投票</button>
     <dl class="am-accordion-item ">
        <dt class="am-accordion-title">
        <div class="am-g" style="background-color: #E0E0E0">
  <div class="am-u-sm-5" style="padding-left: 0px;"><img alt="" src="<%=request.getContextPath()%>/acicon/A1.jpg" width="120px" height="100px"></div>
  <div class="am-u-sm-4" style="line-height: 25px;margin-top: 10px;">
  <p>周杰伦</p>
  <p>得票数:49</p></div>
<div class="am-u-sm-3" style="text-align: center;line-height: 100px;"><font size="5">4号</font> </div>
</div>
        </dt>
        <dd class="am-accordion-bd am-collapse ">
          <!-- 规避 Collapase 处理有 padding 的折叠内容计算计算有误问题， 加一个容器 -->
          <div class="am-accordion-content">
          简介：周杰伦（Jay Chou），1979年1月18日出生于台湾省新北市，中国台湾流行乐男歌手、音乐人、演员、导演、编剧、监制、商人。2000年发行首张个人专辑《Jay》。</div>
        </dd>
      </dl>
      <button type="button" class="am-btn am-btn-default am-btn-block">我要投票</button><dl class="am-accordion-item ">
        <dt class="am-accordion-title">
        <div class="am-g" style="background-color: #E0E0E0">
  <div class="am-u-sm-5" style="padding-left: 0px;"><img alt="" src="<%=request.getContextPath()%>/acicon/A1.jpg" width="120px" height="100px"></div>
  <div class="am-u-sm-4" style="line-height: 25px;margin-top: 10px;">
  <p>周杰伦</p>
  <p>得票数:49</p></div>
<div class="am-u-sm-3" style="text-align: center;line-height: 100px;"><font size="5">4号</font> </div>
</div>
        </dt>
        <dd class="am-accordion-bd am-collapse ">
          <!-- 规避 Collapase 处理有 padding 的折叠内容计算计算有误问题， 加一个容器 -->
          <div class="am-accordion-content">
          简介：周杰伦（Jay Chou），1979年1月18日出生于台湾省新北市，中国台湾流行乐男歌手、音乐人、演员、导演、编剧、监制、商人。2000年发行首张个人专辑《Jay》。</div>
        </dd>
      </dl>
      <button type="button" class="am-btn am-btn-default am-btn-block">我要投票</button>
      <dl class="am-accordion-item ">
        <dt class="am-accordion-title">
        <div class="am-g" style="background-color: #E0E0E0">
  <div class="am-u-sm-5" style="padding-left: 0px;"><img alt="" src="<%=request.getContextPath()%>/acicon/A1.jpg" width="120px" height="100px"></div>
  <div class="am-u-sm-4" style="line-height: 25px;margin-top: 10px;">
  <p>周杰伦</p>
  <p>得票数:49</p></div>
<div class="am-u-sm-3" style="text-align: center;line-height: 100px;"><font size="5">4号</font> </div>
</div>
        </dt>
        <dd class="am-accordion-bd am-collapse ">
          <!-- 规避 Collapase 处理有 padding 的折叠内容计算计算有误问题， 加一个容器 -->
          <div class="am-accordion-content">
          简介：周杰伦（Jay Chou），1979年1月18日出生于台湾省新北市，中国台湾流行乐男歌手、音乐人、演员、导演、编剧、监制、商人。2000年发行首张个人专辑《Jay》。</div>
        </dd>
      </dl>
      <button type="button" class="am-btn am-btn-default am-btn-block" onclick="funVote(4);">我要投票</button>
      
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
