package com.cloopen.rest.demo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.cloopen.rest.sdk.CCPRestSDK.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.school.form.AjaxForm;


public class YunTongXun {
//	public static final String BASE_IP = "https://sandboxapp.cloopen.com:8883";//访问地址

	private static  String APPID ;//应用id

	private static  String APPTOKEN ;//主账号token
	private static  String APPSID ;//主账号id

	static{
		Properties p=new Properties();
		try {
			p.load(YunTongXun.class.getClassLoader().getResourceAsStream("yuntongxun.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//System.out.println("配置文件哪去了？");
			e.printStackTrace();
		}
		APPID=p.getProperty("APPID");
		APPTOKEN=p.getProperty("APPTOKEN");
		APPSID=p.getProperty("APPSID");
	//	org_name=p.getProperty("org_name");
	}
	public static Boolean getVoiceCode(String phone,String code) {
		HashMap result = null;
		CCPRestSDK restAPI = new CCPRestSDK();
		//restAPI.init("sandboxapp.cloopen.com", "8883");
		restAPI.init("app.cloopen.com", "8883");
		// 初始化服务器地址和端口，沙盒环境配置成sandboxapp.cloopen.com，生产环境配置成app.cloopen.com，端口都是8883.
		restAPI.setAccount(APPSID, APPTOKEN);
		// 初始化主账号名称和主账号令牌，登陆云通讯网站后，可在"控制台-应用"中看到开发者主账号ACCOUNT SID和
		// 主账号令牌AUTH TOKEN。
		restAPI.setAppId(APPID);
		// 初始化应用ID，如果是在沙盒环境开发，
		// 请配置"控制台-应用-测试DEMO"中的APPID。如切换到生产环境，请使用自己创建应用的APPID
		// result = restAPI.voiceVerify("验证码内容", "号码","显示的号码","3(播放次数)",
		// "状态通知回调地址", "语言类型", "第三方私有数据");
		result = restAPI.voiceVerify(code, phone,
				"085128785767", "3", "", "", "", "", "");
		//System.out.println("SDKTestVoiceVerify result=" + result);
	//	Log.v("LTT", "SDKTestVoiceVerify result=" + result);
		if ("000000".equals(result.get("statusCode"))) {
			// 正常返回输出data包体信息（map）
			HashMap data = (HashMap) result.get("data");
			Set<String> keySet = data.keySet();
			for (String key : keySet) {
				Object object = data.get(key);
				//System.out.println(key + " = " + object);
//				Log.v("LTT", key + " = " + object);
			}
			return true;
		} else {
			// 异常返回输出错误码和错误信息
			System.out.println("错误码=" + result.get("statusCode") + " 错误信息= "
					+ result.get("statusMsg"));
//			Log.v("LTT",
//					"错误码=" + result.get("statusCode") + " 错误信息= "
//							+ result.get("statusMsg"));
		}
		return false;
	}
	public static AjaxForm getSmsCode(String phone, String message) {
		AjaxForm ajaxForm=new AjaxForm();
		HashMap result = null;
		CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
		//restAPI.init("sandboxapp.cloopen.com", "8883");
		restAPI.init("app.cloopen.com", "8883");
		// 初始化服务器地址和端口，沙盒环境配置成sandboxapp.cloopen.com，生产环境配置成app.cloopen.com，端口都是8883.
		restAPI.setAccount(APPSID, APPTOKEN);
		// 初始化主账号名称和主账号令牌，登陆云通讯网站后，可在"控制台-应用"中看到开发者主账号ACCOUNT SID和
		// 主账号令牌AUTH TOKEN。
		restAPI.setAppId(APPID);
 
		result = restAPI.sendTemplateSMS(phone,"72111",new String[]{message,"5"});
		//System.out.println("SDKTestSMSVerify result=" + result);
	//	Log.v("LTT", "SDKTestVoiceVerify result=" + result);
		if ("000000".equals(result.get("statusCode"))) {
			// 正常返回输出data包体信息（map）
			HashMap data = (HashMap) result.get("data");
			Set<String> keySet = data.keySet();
			for (String key : keySet) {
				Object object = data.get(key);
				//System.out.println(key + " = " + object);
//				Log.v("LTT", key + " = " + object);
			}
			ajaxForm.setMsg("发送完成");
			
		} else {
			ajaxForm.setRet(1);
			ajaxForm.setMsg("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
			
			// 异常返回输出错误码和错误信息
			System.out.println("错误码=" + result.get("statusCode") + " 错误信息= "
					+ result.get("statusMsg"));
//			Log.v("LTT",
//					"错误码=" + result.get("statusCode") + " 错误信息= "
//							+ result.get("statusMsg"));
		}
		return ajaxForm;
	}
	
	public static void main(String[] args) {
		YunTongXun.getSmsCode("18785122153", "8789");
	}
}