$(function(){
	
	setTimeout(function(){
		applyOutDatagrid();
	},150);
	
	 //处理t
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
		 $('#i_serialno').val(row.serialno);
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

	 $('#siii').combobox({
    panelMaxHeight:'30',
    panelAlign:'right'
});
	
});

//刷新
function applyOutRefresh(){
	
       $('#applyOut_dg').datagrid('clearSelections');
	 $('#applyOut_dg').datagrid('reload'); 
}

function applyOutDatagrid(){
	
	$('#applyOut_dg').datagrid({    
    url:path+'/admin/cashHandle/applyOutList.do' ,
    striped:true,
    loadMsg:' please wait …',
    idField:'id',
    pagination:true,
    rownumbers:true,
    autoRowHeight:false,
    singleSelect:true,
    toolbar: '#tb_applyout'
       
});
	
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
  url: path+'/admin/cashHandle/handleApplyOut.do',
  data: data,
  success: function(result){
    alert(result.msg);
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


//查询
function applyOutSerach(){
	
	  var  data = formSerial($("#applyout_form_serach")); 
	$('#applyOut_dg').datagrid('load',data); 
	$('#applyOut_dg').datagrid('clearSelections');
}
//重置
function applyOutReset(){
	//$('#endTime').datebox('setValue', '');
	//$('#startTime').datebox('setValue', '');
	 // $("#applyout_form_serach")[0].reset(); 
	  $("#applyout_form_serach").form('reset');
	
}