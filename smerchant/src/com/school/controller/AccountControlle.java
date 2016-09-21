package com.school.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cloopen.rest.demo.YunTongXun;
import com.school.cache.CacheService;
import com.school.common.BaseConteroller;
import com.school.form.AjaxForm;
import com.school.form.ServiceReqForm;
import com.school.utils.CreateSerialNo;
import com.school.utils.DESCoder;
import com.school.utils.JsonUtil;
/**
 * 公共信息
 * @author acode
 *
 */
@Controller
@RequestMapping("/account")
public class AccountControlle extends BaseConteroller{

	@Resource
	private CacheService cacheService; 
	
	/**
	 * 获取验证码请求
	 * @param params
	 */
	@RequestMapping("/getMsgReq")
	public void getMsgReq(String params){
		AjaxForm ajaxForm=new AjaxForm();
		ajaxForm.setRet(1);
		//解析信息
		Map<String,Object> map=analysis(params);
		ServiceReqForm serviceReqForm=JsonUtil.parseObject(JsonUtil.createJsonString(map), ServiceReqForm.class);
		if(serviceReqForm.getPhone()==null){
			ajaxForm.setMsg("参数不全");
		}else{
			
			String msg=CreateSerialNo.createVoiceMsg();
			//System.out.println(msg);
			//msg="888888";
			
			ajaxForm=YunTongXun.getSmsCode(serviceReqForm.getPhone(), msg);
			cacheService.addCache(serviceReqForm.getPhone(), msg);
		}
		
		String str=JsonUtil.createJsonString(ajaxForm);
		System.out.println("加密前："+str);
		try {
			str=DESCoder.encryptDES(str);
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally{
			System.out.println("加密后："+str);
			out.print(str);
			out.close();
		}
	
	}
}
