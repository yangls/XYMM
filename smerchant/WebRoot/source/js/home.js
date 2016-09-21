$(function(){
	setInterval(function(){
		$('#time_home').text(new Date().toLocaleString());
	},1000);
	
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
function actionTypeFormatter(value,row,index){
	 switch(value){
	 case 0:
 		value='<span >收入<sapn/>';
  		break;
	case 1:
  		value='<span >支出<sapn/>';
  		break;
  	case 2:
  		value='失败';
  		break;
	 }
		return value;
 } 
function padFlagFormatter(value,row,index){
	 switch(value){
	 case 0:
 		value='<span >未设置<sapn/>';
  		break;
	case 1:
  		value='<span >已设置<sapn/>';
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
 function bankTypeFormatter(value,row,index){
	 switch(value){
	 case 1:
 		value='<span >中国工商银行<sapn/>';
  		break;
	case 2:
  		value='<span >中国建设银行<sapn/>';
  		break;
  	case 3:
  		value='<span >中国农业银行<sapn/>';
  		break;
  	case 4:
  		value='<span >中国邮政储蓄银行<sapn/>';
  		break;
  	case 5:
  		value='<span >中国银行<sapn/>';
  		break;
  	case 6:
  		value='<span >招商银行<sapn/>';
  		break;
  	case 7:
  		value='<span >浦发银行<sapn/>';
  		break;
  	case 8:
  		value='<span >交通银行<sapn/>';
  		break;
	 }
		return value;
 }
 function formSerial(form){
	var o = {};  
        $.each(form.serializeArray(), function(index) {  
            if (o[this['name']]) {  
                o[this['name']] = o[this['name']] + "," + this['value'];  
            } else {  
                o[this['name']] = this['value'];  
            }  
        });  
        return o; 
}