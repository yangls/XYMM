package com.school.controller.merchant;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.school.bean.merchant.MerChantInfo;
import com.school.cache.CacheService;
import com.school.common.BaseConteroller;
import com.school.form.AjaxForm;
import com.school.form.ServiceReqForm;
import com.school.service.MerchantService;
import com.school.utils.DESCoder;
import com.school.utils.JsonUtil;
import com.school.utils.StringUtil;
import com.school.utils.UUIDutil;

@Controller
@RequestMapping("/merchant")
public class MerchantLoginController extends BaseConteroller {
	
	@Resource
	private MerchantService merchantService;
	
	@Resource
	private CacheService cacheService; 
	
	
	@RequestMapping("/login")
	private void login(String params){
		String str = null;
		//解析信息
		System.out.println("go..."+params);
		Map<String,Object> map=analysis(params);
		
		AjaxForm ajaxForm=new AjaxForm();
		ajaxForm.setMsg("用户名或密码错误");
		//获取phone,password,code短信码
		try{
		ServiceReqForm serviceReqForm=JsonUtil.parseObject(JsonUtil.createJsonString(map), ServiceReqForm.class);
		String phone=serviceReqForm.getPhone();
		MerChantInfo merChantInfo=merchantService.login(phone,DESCoder.encryptDES(serviceReqForm.getPassword()));
		
		if(merChantInfo!=null){
			String token=UUIDutil.createUuid();
			cacheService.addTokenCache(phone, token);
			ajaxForm.setMsg(token);
		}else{
			ajaxForm.setRet(1);
			
		}
		str=JsonUtil.createJsonString(ajaxForm);
		//System.out.println("加密前："+str);
		
			str=DESCoder.encryptDES(str);
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally{
			//System.out.println("加密："+str);
			out.print(str);
			out.close();
		}
		
		
	}

	@RequestMapping("/register")
	private void register(String params){
		String str = null;
		//解析信息
		Map<String,Object> map=analysis(params);
		AjaxForm ajaxForm=new AjaxForm();
		//获取phone,password,code短信码
		ServiceReqForm serviceReqForm=JsonUtil.parseObject(JsonUtil.createJsonString(map), ServiceReqForm.class);
		try {
		if(StringUtil.isNotEmpty(serviceReqForm.getPhone())&&StringUtil.isNotEmpty(serviceReqForm.getCode())&&StringUtil.isNotEmpty(serviceReqForm.getPassword())){
			//判断验证码是否正确
			String phone=serviceReqForm.getPhone();
			String code=cacheService.getCache(phone);
			String password=serviceReqForm.getPassword();
			if(code.equals(serviceReqForm.getCode())){
				MerChantInfo merChantInfo=merchantService.findByphone(phone);
				if(merChantInfo==null){
					
					merChantInfo=new MerChantInfo(phone,DESCoder.encryptDES(password));
					merchantService.save(merChantInfo);
					ajaxForm.setMsg("注册完成");
				}else{
					ajaxForm.setRet(1);
					ajaxForm.setMsg(phone+"用户已存在");
				}
				
			}else{
				ajaxForm.setRet(1);
				ajaxForm.setMsg("验证码不正确");
			}
			
			
		}else{
			ajaxForm.setRet(1);
			ajaxForm.setMsg("参数不全");
			
		}
		str=JsonUtil.createJsonString(ajaxForm);
		
			str=DESCoder.encryptDES(str);
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally{
			out.print(str);
			out.close();
		}
		
	}
	
	
}
