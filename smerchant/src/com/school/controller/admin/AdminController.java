package com.school.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.school.common.BaseConteroller;
import com.school.form.DataGridForm;
import com.school.form.ServiceSelectReqForm;
import com.school.utils.JsonUtil;

@Controller
@RequestMapping("/admin/adminHandle")
public class AdminController extends BaseConteroller{
	
	
	@RequestMapping("/userBillPage")
	private String userBillPage(){
		return "a/userBillPage";
	}
	
	@RequestMapping("/userBillList")
	private void userBillList(ServiceSelectReqForm serviceSelectReqForm){
		
		//System.out.println(str);
		//System.out.println("调用了"+this.page+1);
		out.print("");
	}

}
