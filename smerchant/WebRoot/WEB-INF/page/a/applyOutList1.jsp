
	<%@ page language="java" import="java.util.*" pageEncoding="utf8"%>
	<body class="easyui-layout">
<style type="text/css">
.table_td{
padding-right: 8px;
}
</style>
  <script type="text/javascript">
  </script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/source/js/applyOut.js"></script>
  
    <table id="applyOut_dg" class="easyui-datagrid"   
        data-options="fitColumns:true,singleSelect:true" style="height: 100%">   
    <thead>   
        <tr>   
            <th data-options="field:'id',hidden:true">id</th>
            <th data-options="field:'serialno',width:200,halign:'center',align:'center'">流水号</th>
            <th data-options="field:'phone',width:100,halign:'center',align:'center'">手机号</th>  
            <th data-options="field:'realName',width:100,halign:'center',align:'center'">申请者</th>  
            <th data-options="field:'account',width:170,halign:'center',align:'center'">提现账号</th> 
            <th data-options="field:'money',width:100,halign:'center',align:'center'">提现金额(元)</th> 
            <th data-options="field:'accountName',width:100,halign:'center',align:'center'">账号归属</th> 
            <th data-options="field:'accountType',width:100,halign:'center',align:'center'" formatter="accountTypeFormatter">账号类型</th> 
            <th data-options="field:'accountAddress',width:100,halign:'center',align:'center'">账号地址</th> 
            <th data-options="field:'status',width:100,halign:'center',align:'center'" formatter="statusFormatter">状态</th>  
            <th data-options="field:'remark',width:100,halign:'center',align:'center'" >备注</th> 
            <th data-options="field:'createTime',width:170,halign:'center',align:'center'">申请时间</th> 
        	<th data-options="field:'serialAccount',width:100,halign:'center',align:'center'">提现流水</th>  
        </tr>   
    </thead> 
</table>
<div id="tb_applyout">
			<form id="applyout_form_serach">
				<table style="font-size: 12px;">
					<tr>
						<td class="table_td">
							手机号:
							<input name="phone" class="easyui-textbox" type="text"  />
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
							账号类型:
							<select name="accountType" class="easyui-combobox" style="width: 100px;"
								panelMaxHeight="100" >
								<option value="9" selected="selected">全部</option>
								<option value="0">银行卡</option>
								<option value="1">其他</option>
							</select>
						</td>
						<td class="table_td">
							申请时间:
							<input id="startTime" name="startTime"  type="text" class="easyui-datebox" data-options="editable:false">
							</input>
							~
							<input id="endTime" name="endTime" type="text" class="easyui-datebox" data-options="editable:false">
							</input>
						</td>
					</tr>
				</table>
				</form>
<a id="btn_handle" href="javascript:void(0)"  class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true">处理</a>
<a  href="javascript:applyOutRefresh();"  class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true">刷新</a>
<div style="float: right;padding-right: 80px">
<a  href="javascript:applyOutSerach();" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="padding-right: 12px;padding-left: 12px;margin-right: 15px;">查询</a>
<a  href="javascript:applyOutReset();" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="padding-right: 12px;padding-left: 12px;margin-right: 20px;">重置</a> 
</div>
</div>
    <div id="userQpply_dd" style="display:none;">  
    <form id="handle_form">
    <table style="width: 100%; height: 100%; background-color:#F0F8FF">
    <tr>
    <td style="font-size: 12px;">
    	<p>姓名:  <span id="p_name"></span></p>
    	<p>手机号: <span id="p_phone"></span></p>
    	<p>提现账号:<span id="p_account"></span></p>
    	<p>账号类型:<span id="p_type"></span></p>
    	<p>账号地址:<span id="p_address"></span></p>
    	<p>提现金额:<span id="p_money"></span></p>
    	<p></p>
    	<p>信息状态:<span id="p_status"></span></p>
    	<p>申请时间:<span id="p_createTime"></span></p>
    </td>
    <td style="width: 60%;  background-color:white;">
    	<div style="width: 100%; height: 100% ; text-align: center; ">
    	<font size="5">操作</font>
    	<hr>
    	<div>
    	<input name="id" id="i_id" type="hidden">
    	<input name="serialno" id="i_serialno" type="hidden">
    	<input name="phone" id="i_phone" type="hidden">
    	<input name="money" id="i_money" type="hidden">
    	<input name="status" id="i_status" type="hidden">
    	<input name="account" id="i_account" type="hidden">
    	<input name="ispass" id="i_pass"  value="0" type="hidden">
    	<table style="margin-top: 20px;">
    	<tr>
    		<td>处理:</td>
    		<td style="padding: 10px;">
    		<label><input name="isok" type="radio" value="0" onchange="isOk(this);" checked="checked"/>批准
    		<label><input name="isok" type="radio" value="1" onchange="isOk(this);" style="margin-left: 30px" />驳回</label>
    		 </td>
    	</tr>
    	<tr>
    		<td>处理流水:</td>
    		<td style="padding: 10px;">
    		<input name ="serialAccount" class="easyui-textbox" id="serialNo" style="width:300px" disabled="disabled"> 
    		</td>
    	</tr>
    	<tr>
    	<td>备注说明:</td>
    		<td style="padding: 10px;">
    		<textarea name="remark" rows="3" cols="25" title="操作说明">处理银行:</textarea> 
    		</td>
    	</tr>
    	</table>
    	<input id="form_reset" type="reset" value="重置" onclick="myreset();">
    	</form>
    	
    	</div>
    	
    	
    	</div>
    </td>
    </tr>
    </table> 
</div>
  </body>
