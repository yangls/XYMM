
$(function(){
	setTimeout(function(){
		$('#bankarr_dg').datagrid({    
    url:path+'/admin/bankarrive/getBankArriveList.do' ,
    striped:true,
    loadMsg:' please wait …',
    idField:'id',
    pagination:true,
    rownumbers:true,
    autoRowHeight:false,
    singleSelect:true,
    onLoadSuccess:function(data){
    },
    toolbar: '#tb_bankarr'
       
});
	},150);
	
});
 
  function reloadDatak(){
	   $('#bankarr_dg').datagrid('reload'); 
	    $('#bankarr_dg').datagrid('clearSelections');
  }
  
//查询
function bankarrSerach(){
	 var  data = formSerial($("#userBill_form_serach")); 
	$('#bankarr_dg').datagrid('load',data); 
	$('#bankarr_dg').datagrid('clearSelections');
}
//重置
function bankarrReset(){
	    $("#bankarr_form_serach").form('reset');
	
}


