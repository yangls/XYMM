
$(function(){
	setTimeout(function(){
		$('#userBill_dg').datagrid({    
    url:path+'/admin/adminHandle/userBillList.do' ,
    striped:true,
    loadMsg:' please wait …',
    idField:'id',
    pagination:true,
    rownumbers:true,
    autoRowHeight:false,
    singleSelect:true,
    onLoadSuccess:function(data){
    },
    toolbar: '#tb_userBill'
       
});
	},150);
	
});
 
  function reloadData(){
	   $('#userBill_dg').datagrid('reload'); 
	    $('#userBill_dg').datagrid('clearSelections');
  }
  
//查询
function userBillSerach(){
	 var  data = formSerial($("#userBill_form_serach")); 
	$('#userBill_dg').datagrid('load',data); 
	$('#userBill_dg').datagrid('clearSelections');
}
//重置
function userBillReset(){
	    $("#userBill_form_serach").form('reset');
	
}