<%@ page language="java" import="java.util.*" pageEncoding="utf8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


  
  <body>
  <script type="text/javascript">
  </script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/source/js/userBill.js"></script>
    <table id="userBill_dg" class="easyui-datagrid"   
        data-options="fitColumns:true,singleSelect:true" style="height: 100%">   
    <thead>   
        <tr>   
            <th data-options="field:'id',hidden:true">id</th>
            <th data-options="field:'serialno',width:150,halign:'center',align:'center'">流水号</th> 
            <th data-options="field:'phone',width:100,halign:'center',align:'center'">手机号</th>  
            <th data-options="field:'changeMoney',width:100,halign:'center',align:'center'">变动金额(元)</th>  
            <th data-options="field:'currentMoney',width:100,halign:'center',align:'center'">现有金额(元)</th> 
            <th data-options="field:'actionType',width:100,halign:'center',align:'center'" formatter="actionTypeFormatter">类型</th>
            <th data-options="field:'status',width:100,halign:'center',align:'center'" formatter="statusFormatter">处理状态</th>  
            <th data-options="field:'remark',width:100,halign:'center',align:'center'" >备注说明</th> 
            <th data-options="field:'createTime',width:150,halign:'center',align:'center'">操作时间</th> 
        </tr>   
    </thead> 
</table>
<div id="tb_userBill">
		<form id="userBill_form_serach">
				<table style="font-size: 12px;">
					<tr>
					<td class="table_td">
							流水号:
							<input name="serialno" class="easyui-textbox" type="text" />
						</td>
						<td class="table_td">
							手机号:
							<input name="phone" class="easyui-textbox" type="text" />
						</td>
						<td class="table_td">
							提现状态:
							<select name="status" class="easyui-combobox" style="width: 100px;"
								panelMaxHeight="100">
								<option value="9" selected="selected">全部</option>
								<option value="0">申请中</option>
								<option value="1">成功</option>
								<option value="2">失败</option>
							</select>
						</td>
						<td class="table_td">
							类型:
							<select name="actionType"  class="easyui-combobox" style="width: 100px;"
								panelMaxHeight="100">
								<option value="9">全部</option>
								<option value="1">支出</option>
								<option value="0">收入</option>
							</select>
						</td>
						<td class="table_td">
							申请时间:
							<input id="searchstartTime" name="startTime" type="text" class="easyui-datebox">
							</input>
							~
							<input id="searchendTime" name="endTime" type="text" class="easyui-datebox">
							</input>
						</td>
					</tr>
				</table>
				
<a href="javascript:reloadData()"  class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true">刷新</a>
<div style="float: right;padding-right: 80px">
<a href="javascript:userBillSerach();" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="padding-right: 12px;padding-left: 12px;margin-right: 15px;">查询</a>
<a href="javascript:userBillReset();" type="reset" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="padding-right: 12px;padding-left: 12px;margin-right: 20px;">重置</a> 
</div>
</form>
</div>
  </body>

