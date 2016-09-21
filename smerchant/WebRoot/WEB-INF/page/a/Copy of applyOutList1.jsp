<%@ page language="java" import="java.util.*" pageEncoding="utf8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'applyOutList.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="/common/easyui.jsp"></jsp:include>
<style type="text/css">
.table_td{
padding-right: 20px;
}
</style>
  </head>
  <script type="text/javascript">
  
  $(function(){
	  
	 $('#applyOut_dg').datagrid({    
    url:'<%=request.getContextPath()%>/admin/cashHandle/applyOutList.do' ,
    striped:true,
    loadMsg:' please wait …',
    idField:'id',
    pagination:true,
    rownumbers:true,
    autoRowHeight:false,
    singleSelect:true,
    onLoadSuccess:function(data){
    },
    toolbar: '#tb'
    
       
}); 
	 //处理
	 $('#btn_handle').bind('click', function(){ 
		 //获取选中的一行
		var row=$('#applyOut_dg').datagrid('getSelected');
		 if(row==null){
			 alert('请选择一行处理');
			 return false;
		 }
		  $('#form_reset').click();
		  $('#p_id').val(row.id);
		 $('#p_name').text(row.realName);
		 $('#p_phone').text(row.phone);
		 $('#p_account').text(row.account);
		 $('#p_type').text(row.accountName);
		 $('#p_address').text(row.accountAddress);
		 $('#p_money').text(row.money);
		 $('#p_status').text(row.status);
		 $('#p_createTime').text(row.createTime);
    	
		 $('#i_id').val(row.id);
		 $('#i_phone').val(row.phone);
		 $('#i_money').val(row.money);
		 $('#i_status').val(row.status);
		 $('#i_account').val(row.account);
		 $('#i_pass').val("0");
		 
		 
		 
        $('#userQpply_dd').show();  
   $('#userQpply_dd').dialog({    
    title: '用户提现信息',    
    width: 700,    
    height: 400,    
    cache: false,    
    //href: 'get_content.php',    
    modal: true,
    buttons:[{
				text:'提交',
				iconCls:'icon-ok',
				handler:function(){
    				goHandle();
				}
			},{
				text:'关闭',
				iconCls:'icon-cancel',
				handler:function(){
				$('#userQpply_dd').dialog('close');
				
				}
			}]
}); 
    });

	 //刷新
$('#btn_refresh').bind('click', function(){    
        $('#applyOut_dg').datagrid('reload'); 
    });
	 
	 $('#siii').combobox({
    panelMaxHeight:'30',
    panelAlign:'right'
}); 
	 
	 
  });
  
 function statusFormatter(value,row,index){
	 switch(value){
	 case 0:
 		value='<span style="color:red">申请中<sapn/>';
  		break;
	case 1:
  		value='<span style="color:#228B22">成功<sapn/>';
  		break;
  	case 2:
  		value='失败';
  		break;
	 }
		return value;
 } 
  function accountTypeFormatter(value,row,index){
	 switch(value){
	 case 0:
 		value='<span >银行卡<sapn/>';
  		break;
	case 1:
  		value='<span >支付宝<sapn/>';
  		break;
  	case 2:
  		value='<span >微信<sapn/>';
  		break;
	 }
		return value;
 } 
  
  function isOk(t){
	  //$('#serialNo').value="";
	  if(t.value==0){
		  $("#i_pass").val(0);
		  $('#serialNo').textbox({   
			  disabled:false,
			  value:""
			}); 
	  }else{
		  $("#i_pass").val(1);
		  $('#serialNo').textbox({   
			  disabled:true,
			  value:""
			}); 
	  }
	  return t.value;
  }
  
 function goHandle(){
	 
	 var chk=0;
	 var msg;
	 var chkObjs = document.getElementsByName("isok");
                for(var i=0;i<chkObjs.length;i++){
                    if(chkObjs[i].checked){
                        chk = chkObjs[i].value;
                        break;
                    }
                }
	 
	 if(chk==0){
		msg="我确定申请通过并且流水号填写正确?" ;
		var v=$('#serialNo').val();
		
		if(v.trim().length==0){
			$.messager.alert('警告','请输入流水号!'); 
			return false;
		}
	 }else{
		msg="我确定驳回此用户提现?";  
	 }
	  $.messager.confirm('确认',msg,function(r){    
    if (r){ 
    	
        
      var data=$("#handle_form").serialize(); 
       $.ajax({
  type: 'POST',
  url: '<%=request.getContextPath()%>/admin/cashHandle/handleApplyOut.do',
  data: data,
  success: function(result){
    alert(result.ret+","+result.msg);
    $('#form_reset').click();
    $('#userQpply_dd').dialog('close');
     $('#applyOut_dg').datagrid('reload'); 
  },
  dataType:'json'
});
       
        
        return true;
    }else{
    	return false;
    }    
		}); 
	 
 } 
 
function myreset(){
	$('#serialNo').textbox({   
			  disabled:false,
			  value:""
			});
} 
  </script>
  <body class="easyui-layout">
    
    <div data-options="region:'north',split:true" style="height:75px;"></div>   
     <div data-options="region:'west',title:'菜单列表',split:true" style="width:150px;">
     
     <div id="aa" class="easyui-accordion" style="width:144px;height:50%;">   
    <div title="提现管理" data-options="iconCls:'icon-save',selected:true" style="overflow:auto;padding:10px;">   
        <a href="#" style="color:#0099FF;">提现申请1</a> <br> <br><br>
        <a href="#" style="color:#0099FF;">提现申请2</a> <br><br><br>
        <a href="#"style="color:#0099FF;">提现申请3</a>  
       
    </div>   
    <div title="平台账户管理" data-options="iconCls:'icon-save'" style="padding:10px;">   
        content2    
    </div>   
       
</div> 
     
     </div> 
    <div data-options="region:'south'" style="height:30px;text-align: center">
    	校园麻麻2016
    </div>   
    <div data-options="region:'center',title:'申请提现列表'" style="padding:5px;background:#eee;">
    
    <table id="applyOut_dg" class="easyui-datagrid"   
        data-options="fitColumns:true,singleSelect:true" style="height: 100%">   
    <thead>   
        <tr>   
            <th data-options="field:'id',hidden:true">id</th>
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
<div id="tb">

				<table style="font-size: 12px;">
					<tr>
						<td class="table_td">
							手机号:
							<input class="easyui-textbox" type="text" name="phone" />
						</td>
						<td class="table_td">
							提现状态:
							<select class="easyui-combobox" style="width: 100px;"
								panelMaxHeight="100">
								<option>
									全部
								</option>
								<option>
									申请中
								</option>
								<option>
									成功
								</option>
								<option>
									失败
								</option>
							</select>
						</td>
						<td class="table_td">
							账号类型:
							<select class="easyui-combobox" style="width: 100px;"
								panelMaxHeight="100">
								<option>
									全部
								</option>
								<option>
									银行卡
								</option>
								<option>
									其他
								</option>
							</select>
						</td>
						<td class="table_td">
							申请时间:
							<input id="dd" type="text" class="easyui-datebox">
							</input>
							~
							<input id="dd" type="text" class="easyui-datebox">
							</input>
						</td>
					</tr>
				</table>
<a id="btn_handle" href="javascript:void(0)"  class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true">处理</a>
<a id="btn_refresh" href="javascript:void(0)"  class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true">刷新</a>
<div style="float: right;padding-right: 80px">
<a id="btn" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="padding-right: 12px;padding-left: 12px;margin-right: 15px;">查询</a>
<a id="btn" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="padding-right: 12px;padding-left: 12px;margin-right: 20px;">重置</a> 
</div>
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
</html>
