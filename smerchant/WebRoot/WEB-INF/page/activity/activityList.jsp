<%@ page language="java" import="java.util.*" pageEncoding="utf8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


 <style>
.table_tr{
margin: 5px;

}
</style>
  <body>
  
  <script type="text/javascript">
 
$(function(){
	setTimeout(function(){
		$('#activity_dg').datagrid({    
    url:path+'/admin/activi/activityListReq.do' ,
    striped:true,
    loadMsg:' please wait …',
    idField:'id',
    pagination:true,
    rownumbers:true,
    autoRowHeight:true,
    singleSelect:true,
    onLoadSuccess:function(data){
    },
    toolbar: '#tb_activity'
       
});
	},90);
	
});
  
  function iconFormatter(value,row,index){
	 if(value!=""){
		 var url="<%=request.getContextPath()%>/acicon"+value;
  		value='<img alt="'+url+'" src="'+url+'" width="320px" height="100px">';
	 }
  	
		return value;
 }
 function viewVote(){
	 var rows= $('#activity_dg').datagrid('getChecked');  
	if(rows.length!=1){
		alert("请选择要预览的活动");
	}else{
		var id=rows[0].id;
		$("#activityId").val(id);
		$("#goVotePage_form").submit();  
	}
 } 
 function  reloadvote(){
	$('#activity_dg').datagrid("reload"); 
 }
 
function addactivity(){
	alert("暂时无功能");
} 
  </script>
    <table id="activity_dg" class="easyui-datagrid"   
        data-options="fitColumns:true,singleSelect:true" style="height: 100%">   
    <thead>   
        <tr>   
            <th data-options="field:'id',hidden:true">id</th>
            <th data-options="field:'activityName',width:100,halign:'center',align:'center'" >活动名称</th>
            <th data-options="field:'activitySummary',width:100,halign:'center',align:'center'" >介绍</th>
            <th data-options="field:'picUrl',width:100,halign:'center',align:'center'" formatter="iconFormatter">图片</th> 
            <th data-options="field:'startTime',width:70,halign:'center',align:'center'">开始时间</th> 
             <th data-options="field:'endTime',width:70,halign:'center',align:'center'">结束时间</th>
        </tr>   
    </thead> 
</table>
<div id="tb_activity">
		<form id="activity_form_serach">
				<table style="font-size: 12px;">
					<tr>
						<td class="table_td">
							名称:<input>
						</td>
					</tr>
				</table>
				
<a href="javascript:addactivity()"  class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
<a href="javascript:updateactivity()"  class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">修改</a>
<a href="javascript:deleteactivity()"  class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">删除</a>
<a href="javascript:viewVote();"  class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">预览活动页面</a>
						
<a href="javascript:reloadvote()"  class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true">刷新</a>
<div style="float: right;padding-right: 80px">
<a href="javascript:bankarrSerach();" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="padding-right: 12px;padding-left: 12px;margin-right: 15px;">查询</a>
<a href="javascript:bankarrReset();" type="reset" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="padding-right: 12px;padding-left: 12px;margin-right: 20px;">重置</a> 
</div>
</form>
</div>
<form id="goVotePage_form" action="<%=request.getContextPath()%>/admin/activi/votePage.do" target="_blank" method="post">
	<input name="phone" type="hidden" value="18999999">
	<input id="activityId" type="hidden" name="id"  >
</form>
  </body>

