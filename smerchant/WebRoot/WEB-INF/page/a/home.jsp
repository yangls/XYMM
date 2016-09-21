<%@ page language="java" import="java.util.*" pageEncoding="utf8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("path",path);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>校园妈妈</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="xymm">
	<jsp:include page="/common/easyui.jsp"></jsp:include>
	
<style type="text/css">
.table_td{
padding-right: 20px;
}
.menu_ul{
list-style-type: none;
margin: 0;
padding: 0;
font-size: 14px;
}
.menu_ul li{
margin-top:5px;
padding: 7px;
}
li:HOVER {
background-color:#4B0082;
cursor:pointer;
}
#time_div{
margin-top: 20px;
position: absolute;
}
</style>
 <script type="text/javascript" src="<%=request.getContextPath()%>/source/js/home.js"></script>
  <script type="text/javascript">
  var path='${path}';
  
  </script>
  
  </head>
  
  <script type="text/javascript">
		function addPanel(title_v,url_v,t){
			$("li").css("background","");
			t.style.background = "#4B0082";
			$("li").css("color","");
			$(t).css("color","red");
		var b=$('#tt').tabs('exists', title_v);
			if(!b){
			$('#tt').tabs('add',{
				title: title_v,
				href: url_v,
				closable: true,
				cache:false
			});
			}else{
			$('#tt').tabs('select', title_v);	
			}
			
			
		}
	</script>
  <body class="easyui-layout">
    
    <div data-options="region:'north',split:true" style="height:75px;">
    目前覆盖城市:贵州省 ,云南省 ,陕西省 ,甘肃省 ,青海省 ,台湾省
    <div id="time_div" style="background-color: blue;">
    <span id="time_home" style="padding:10px;" ></span>
    </div>
    </div>   
     <div data-options="region:'west',title:'菜单列表',split:true" style="width:150px;">
     
     <div id="aa" class="easyui-accordion" style="width:144px;">   
    <div title="提现管理" data-options="iconCls:'icon-save',selected:true" style="overflow:auto;">   
        
        <div>
        	<ul class="menu_ul">
        		<li class="menu_ul_li" onclick="addPanel('提现信息','${path}/admin/cashHandle/applyOutPage1.do',this);"><img src="<%=request.getContextPath()%>/source/jquery-easyui-1.4.4/themes/icons/tip.png"></img>提现信息处理</li>
        		<li class="menu_ul_li" onclick="addPanel('用户账单','${path}/admin/adminHandle/userBillPage.do',this);"><img src="<%=request.getContextPath()%>/source/jquery-easyui-1.4.4/themes/icons/tip.png"></img>用户账单流水</li>
        	</ul>
        </div>
    </div>   
    <div title="平台账户管理" data-options="iconCls:'icon-save'" >   
         <div>
        	<ul class="menu_ul">
        		<li class="menu_ul_li" onclick="addPanel('平台账户信息','${path}/admin/plat/platInfoPage.do',this);"><img src="<%=request.getContextPath()%>/source/jquery-easyui-1.4.4/themes/icons/tip.png"></img>平台账户信息</li>
        		<li class="menu_ul_li" onclick="addPanel('平台账户流水','${path}/admin/plat/platBillPage.do',this);"><img src="<%=request.getContextPath()%>/source/jquery-easyui-1.4.4/themes/icons/tip.png"></img>平台账户账单</li>
        		<li class="menu_ul_li" onclick="addPanel('转账给用户','${path}/admin/plat/userWalletPage.do',this);"><img src="<%=request.getContextPath()%>/source/jquery-easyui-1.4.4/themes/icons/tip.png"></img>平台转账给用户</li>
        	
        	</ul>
        </div>  
    </div> 
    <div title="其他管理" data-options="iconCls:'icon-save'" >   
         <div>
        	<ul class="menu_ul">
        		<li class="menu_ul_li" onclick="addPanel('招聘/公告信息','${path}/admin/notice/noticePage.do',this);"><img src="<%=request.getContextPath()%>/source/jquery-easyui-1.4.4/themes/icons/tip.png"></img>招聘/公告信息</li>
        		<li class="menu_ul_li" onclick="addPanel('用户反馈信息','${path}/admin/feedback/feedbackPage.do',this);"><img src="<%=request.getContextPath()%>/source/jquery-easyui-1.4.4/themes/icons/tip.png"></img>用户反馈信息</li>
        		<li class="menu_ul_li" onclick="addPanel('提现到账时间管理','${path}/admin/bankarrive/bkPage.do',this);"><img src="<%=request.getContextPath()%>/source/jquery-easyui-1.4.4/themes/icons/tip.png"></img>提现到账时间管理</li>
        	<li class="menu_ul_li" onclick="addPanel('投票活动列表','${path}/admin/activi/activityPage.do',this);"><img src="<%=request.getContextPath()%>/source/jquery-easyui-1.4.4/themes/icons/tip.png"></img>投票活动列表</li>
        	<li class="menu_ul_li" onclick="addPanel('投票活动参与者','${path}/admin/activi/activityUserPage.do',this);"><img src="<%=request.getContextPath()%>/source/jquery-easyui-1.4.4/themes/icons/tip.png"></img>投票活动参与者</li>
        	<li class="menu_ul_li" onclick="addPanel('广告投放列表','${path}/admin/bankarrive/bkPage.do',this);"><img src="<%=request.getContextPath()%>/source/jquery-easyui-1.4.4/themes/icons/tip.png"></img>广告投放列表</li>
        	
        	</ul>
        </div>  
    </div>  
       
</div> 
     
     </div> 
    <div data-options="region:'south'" style="height:30px;text-align: center">
    	校园麻麻2016
    </div>   
    <div data-options="region:'center',title:'xiaoyuanmama-GZ '" style="padding:1px;background:#eee;">
    
    <div id="tt" class="easyui-tabs" data-options="tools:'#tab-tools'" style="height:100%">
    <div title="首页" style="padding:20px;">   
        使用说明    ：本系统功能为平台账户系统，主要包括用户账户和平台账户模块，对平台进行充值，对平台用户进行转账，充值处理
        <br/>
        使用对象:管理员/会计员<br>
        模板功能说明:<br>
   当前功能个数:5个<br/>     
        提现管理-提现信息处理:查询用户提现信息，审核，手动转账给用户功能，只有状态为申请中的用户才能处理提现。当转账到用户银行账户后，需求银行填入处理流水号，方便以后对账.<br/>
       提现管理-用户账单流水:可详细查询用户平台账户转出-转出信息.<br>
    平台账户功能-平台账户信息:可查询当前平台总余额信息，最后交易日期，可向平台增加金额 .<br/>
   平台账户功能-平台账户账单:可查询平台账号每笔收支情况. <br/>
  平台账户功能-平台转账给用户:可向平台用户转入虚拟金额到用户钱包,用户可申请提现把虚拟钱包余额申请转出到用户银行卡.   
            
    </div> 
    
	</div>

    </div> 
    
  </body>
</html>
