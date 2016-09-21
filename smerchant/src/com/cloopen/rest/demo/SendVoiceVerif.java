package com.cloopen.rest.demo;

import java.util.HashMap;
import java.util.Set;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.school.form.AjaxForm;

public class SendVoiceVerif {
	
	private static CCPRestSDK restAPI = new CCPRestSDK();
	
	public static AjaxForm sendMsg(String phone,String msg){
		AjaxForm ajaxForm=new AjaxForm();
		HashMap<String, Object> result = null;

		restAPI.init("sandboxapp.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setAccount("aaf98f8952a572be0152ad174a2e0cfc", "7519d299dde24ec8ba08621dd2cb8cbf");// 初始化主帐号和主帐号TOKEN
		restAPI.setAppId("8a48b55152a56fc20152ad178e810b8e");// 初始化应用ID
		result = restAPI.voiceVerify(msg, phone,"","3","", "zh", "","","");
			//System.out.println("PHONE:"+phone);
			//System.out.println("msg:"+msg);
		//System.out.println("SDKTestVoiceVerify result=" + result);
		if("000000".equals(result.get("statusCode"))){
			//正常返回输出data包体信息（map）
			HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
			Set<String> keySet = data.keySet();
			for(String key:keySet){
				Object object = data.get(key);
				//System.out.println(key +" = "+object);
			}
			ajaxForm.setMsg("发送完成");
		}else{
			ajaxForm.setRet(1);
			ajaxForm.setMsg("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
			//异常返回输出错误码和错误信息
			//System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
		}
		
		return ajaxForm;
		
	}

}
