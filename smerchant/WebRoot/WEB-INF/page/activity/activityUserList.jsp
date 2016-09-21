<%@ page language="java" import="java.util.*" pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		$('#activityUser_dg').datagrid({    
    url:path+'/admin/activi/activityUserListReq.do' ,
    striped:true,
    loadMsg:' please wait …',
    idField:'id',
    pagination:true,
    rownumbers:true,
    autoRowHeight:true,
    singleSelect:true,
    onLoadSuccess:function(data){
    },
    toolbar: '#tb_activityUser'
       
});
	},90);
	
});
  
  function usericonFormatter(value,row,index){
	 if(value!=""){
		 var url="<%=request.getContextPath()%>/acicon"+value;
		 console.info(url);
  		value='<img alt="'+url+'" src="'+url+'" width="170px" height="100px">';
	 }
  	
		return value;
 }
  </script>
    <table id="activityUser_dg" class="easyui-datagrid"   
        data-options="fitColumns:true,singleSelect:true" style="height: 100%">   
    <thead>   
        <tr>   
            <th data-options="field:'id',hidden:true">id</th>
            <th data-options="field:'userSerialNo',width:20,halign:'center',align:'center'" >编号</th>
            <th data-options="field:'userName',width:50,halign:'center',align:'center'" >姓名</th>
            <th data-options="field:'userSummary',width:100,halign:'center',align:'center'" >简介</th>
            <th data-options="field:'userIconUrl',width:70,halign:'center',align:'center'" formatter="usericonFormatter">图片</th> 
            <th data-options="field:'vote',width:30,halign:'center',align:'center'">得票数</th> 
            <th data-options="field:'activityId',hidden:true">活动id</th> 
        </tr>   
    </thead> 
</table>
<div id="tb_activityUser">
		<form id="activity_form_serach">
				<table style="font-size: 12px;">
					<tr>
						<td class="table_td">
							<font color="white" size="2">活动选择: </font><select  name="activityCode" >
								<option value="0" selected="selected">全部</option>
								<c:forEach items="${aclist}" var="ac">
								<option value="${ac.id}">${ac.activityName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>
				
<a href="javascript:addbankArr()"  class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
<a href="javascript:updatebankArr()"  class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">修改</a>
<a href="javascript:deletebankArr()"  class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">删除</a>
					
<a href="javascript:reloadDatak()"  class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true">刷新</a>
<div style="float: right;padding-right: 80px">
<a href="javascript:bankarrSerach();" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="padding-right: 12px;padding-left: 12px;margin-right: 15px;">查询</a>
<a href="javascript:bankarrReset();" type="reset" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="padding-right: 12px;padding-left: 12px;margin-right: 20px;">重置</a> 
</div>
</form>
</div>

  </body>

