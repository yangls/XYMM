package com.school.controller.merchant;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.school.common.BaseConteroller;
import com.school.service.MerchantService;

@Controller
@RequestMapping("/merchant/handle")
public class MerchantController extends BaseConteroller {
	
	@Resource
	private MerchantService merchantService;
	
	
	/**
	 * 申请认证
	 * @param params
	 */
	@RequestMapping("/applyAuthen")
	private void applyAuthen(String params){
	//实现
		
		
		
	}
	
	/**
	 * 查询认证情况
	 * @param params
	 */
	@RequestMapping("/queryAuthen")
	private void queryAuthen(String params){
	//实现
		
		
		
	}
	
	/**
	 * 商家发布兼职
	 * @param params
	 */
	@RequestMapping("/addJob")
	private void addJob(String params){
	//实现
		
		
		
	}
	
	/**
	 * 商家获取兼职列表
	 * @param params
	 */
	@RequestMapping("/getJobList")
	private void getJobList(String params){
	//实现
		
		
		
	}
	/**
	 * 商家获取兼职详情
	 * @param params
	 */
	@RequestMapping("/getJobInfo")
	private void getJobInfo(String params){
	//实现
		
		
		
	}
	
	/**
	 * 商家获取兼职报名人员
	 * @param params
	 */
	@RequestMapping("/getJobStaff")
	private void getJobStaff(String params){
	//实现
		
		
		
	}
	/**
	 * 商家获取报名人员详情
	 * @param params
	 */
	@RequestMapping("/getStaffInfo")
	private void getStaffInfo(String params){
	//实现
		
		
		
	}
	/**
	 * 录用人员
	 * @param params
	 */
	@RequestMapping("/hireStaff")
	private void hireStaff(String params){
	//实现
		
		
		
	}
	
	

}
