/*
 *  Copyright (c) 2014 The CCP project authors. All Rights Reserved.
 *
 *  Use of this source code is governed by a Beijing Speedtong Information Technology Co.,Ltd license
 *  that can be found in the LICENSE file in the root of the web site.
 *
 *   http://www.yuntongxun.com
 *
 *  An additional intellectual property rights grant can be found
 *  in the file PATENTS.  All contributing project authors may
 *  be found in the AUTHORS file in the root of the source tree.
 */
package com.cloopen.rest.sdk;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import ytx.org.apache.http.HttpEntity;
import ytx.org.apache.http.HttpResponse;
import ytx.org.apache.http.client.methods.HttpGet;
import ytx.org.apache.http.client.methods.HttpPost;
import ytx.org.apache.http.client.methods.HttpRequestBase;
import ytx.org.apache.http.entity.BasicHttpEntity;
import ytx.org.apache.http.impl.client.DefaultHttpClient;
import ytx.org.apache.http.message.AbstractHttpMessage;
import ytx.org.apache.http.util.EntityUtils;

import com.cloopen.rest.sdk.utils.CcopHttpClient;
import com.cloopen.rest.sdk.utils.DateUtil;
import com.cloopen.rest.sdk.utils.EncryptUtil;
import com.cloopen.rest.sdk.utils.LoggerUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CCPRestSDK {

	private static final int Request_Get = 0;

	private static final int Request_Post = 1;

	private static final String VoiceVerify = "Calls/VoiceVerify";

	private String SERVER_IP;
	private String SERVER_PORT;
	private String ACCOUNT_SID;
	private String ACCOUNT_TOKEN;
	public String App_ID;
	private BodyType BODY_TYPE = BodyType.Type_XML;

	public enum BodyType {
		Type_XML, Type_JSON;
	}

	public enum AccountType {
		Accounts, SubAccounts;
	}

	/**
	 * 初始化服务地址和端口
	 * 
	 * @param serverIP
	 *            必选参数 服务器地址
	 * @param serverPort
	 *            必选参数 服务器端口
	 */
	public void init(String serverIP, String serverPort) {
		if (isEmpty(serverIP) || isEmpty(serverPort)) {
			LoggerUtil.fatal("初始化异常:serverIP或serverPort为空");
			throw new IllegalArgumentException("必选参数:"
					+ (isEmpty(serverIP) ? " 服务器地址 " : "")
					+ (isEmpty(serverPort) ? " 服务器端口 " : "") + "为空");
		}
		SERVER_IP = serverIP;
		SERVER_PORT = serverPort;
	}

	/**
	 * 初始化主帐号信息
	 * 
	 * @param accountSid
	 *            必选参数 主帐号
	 * @param accountToken
	 *            必选参数 主帐号TOKEN
	 */
	public void setAccount(String accountSid, String accountToken) {
		if (isEmpty(accountSid) || isEmpty(accountToken)) {
			LoggerUtil.fatal("初始化异常:accountSid或accountToken为空");
			throw new IllegalArgumentException("必选参数:"
					+ (isEmpty(accountSid) ? " 主帐号" : "")
					+ (isEmpty(accountToken) ? " 主帐号TOKEN " : "") + "为空");
		}
		ACCOUNT_SID = accountSid;
		ACCOUNT_TOKEN = accountToken;
	}

	/**
	 * 初始化应用Id
	 * 
	 * @param appId
	 *            必选参数 应用Id
	 */
	public void setAppId(String appId) {
		if (isEmpty(appId)) {
			LoggerUtil.fatal("初始化异常:appId为空");
			throw new IllegalArgumentException("必选参数: 应用Id 为空");
		}
		App_ID = appId;
	}


	/**
	 * 发起语音验证码请求
	 * 
	 * @param verifyCode
	 *            必选参数 验证码内容，为数字和英文字母，不区分大小写，长度4-8位
	 * @param to
	 *            必选参数 接收号码
	 * @param displayNum
	 *            可选参数 显示主叫号码，显示权限由服务侧控制
	 * @param playTimes
	 *            可选参数 循环播放次数，1－3次，默认播放1次
	 * @param respUrl
	 *            可选参数 语音验证码状态通知回调地址，云通讯平台将向该Url地址发送呼叫结果通知
	 * @param lang
	 *            可选参数 语言类型
	 * @param userData
	 *            可选参数 第三方私有数据
	 * @param welcomePrompt
	 *            可选参数 欢迎提示音，在播放验证码语音前播放此内容（语音文件格式为wav）           
	 * @param playVerifyCode
	 *            可选参数 语音验证码的内容全部播放此节点下的全部语音文件
	 * @return
	 */
	public HashMap<String, Object> voiceVerify(String verifyCode, String to,
			String displayNum, String playTimes, String respUrl, String lang,
			String userData,String welcomePrompt,String playVerifyCode) {
		HashMap<String, Object> validate = accountValidate();
		if (validate != null)
			return validate;
		if ((isEmpty(verifyCode)) || (isEmpty(to)))
			throw new IllegalArgumentException("必选参数:"
					+ (isEmpty(verifyCode) ? " 验证码内容 " : "")
					+ (isEmpty(to) ? " 接收号码 " : "") + "为空");
		CcopHttpClient chc = new CcopHttpClient();
		DefaultHttpClient httpclient = null;
		try {
			httpclient = chc.registerSSL(SERVER_IP, "TLS",
					Integer.parseInt(SERVER_PORT), "https");
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new RuntimeException("初始化httpclient异常" + e1.getMessage());
		}
		String result = "";
		try {
			HttpPost httppost = (HttpPost) getHttpRequestBase(1, VoiceVerify);
			String requsetbody = "";
			if (BODY_TYPE == BodyType.Type_JSON) {
				JsonObject json = new JsonObject();
				json.addProperty("appId", App_ID);
				json.addProperty("verifyCode", verifyCode);
				json.addProperty("to", to);
				if (!(isEmpty(displayNum)))
					json.addProperty("displayNum", displayNum);

				if (!(isEmpty(playTimes)))
					json.addProperty("playTimes", playTimes);

				if (!(isEmpty(respUrl)))
					json.addProperty("respUrl", respUrl);
				if (!(isEmpty(lang)))
					json.addProperty("lang", lang);
				if (!(isEmpty(userData)))
					json.addProperty("userData", userData);
				if (!(isEmpty(welcomePrompt)))
					json.addProperty("welcomePrompt", welcomePrompt);
				if (!(isEmpty(playVerifyCode)))
					json.addProperty("playVerifyCode", playVerifyCode);

				requsetbody = json.toString();
			} else {
				StringBuilder sb = new StringBuilder(
						"<?xml version='1.0' encoding='utf-8'?><VoiceVerify>");
				sb.append("<appId>").append(App_ID).append("</appId>")
						.append("<verifyCode>").append(verifyCode)
						.append("</verifyCode>").append("<to>").append(to)
						.append("</to>");
				if (!(isEmpty(displayNum)))
					sb.append("<displayNum>").append(displayNum)
							.append("</displayNum>");

				if (!(isEmpty(playTimes)))
					sb.append("<playTimes>").append(playTimes)
							.append("</playTimes>");

				if (!(isEmpty(respUrl)))
					sb.append("<respUrl>").append(respUrl).append("</respUrl>");
				if (!(isEmpty(lang)))
					sb.append("<lang>").append(lang).append("</lang>");
				if (!(isEmpty(userData)))
					sb.append("<userData>").append(userData)
							.append("</userData>");
				if (!(isEmpty(welcomePrompt)))
					sb.append("<welcomePrompt>").append(welcomePrompt)
							.append("</welcomePrompt>");
				if (!(isEmpty(playVerifyCode)))
					sb.append("<playVerifyCode>").append(playVerifyCode)
							.append("</playVerifyCode>");

				sb.append("</VoiceVerify>").toString();
				requsetbody = sb.toString();
			}

			LoggerUtil.info("voiceVerify Request body = : " + requsetbody);
			BasicHttpEntity requestBody = new BasicHttpEntity();
			requestBody.setContent(new ByteArrayInputStream(requsetbody
					.getBytes("UTF-8")));
			requestBody.setContentLength(requsetbody.getBytes("UTF-8").length);
			httppost.setEntity(requestBody);
			HttpResponse response = httpclient.execute(httppost);

			HttpEntity entity = response.getEntity();
			if (entity != null)
				result = EntityUtils.toString(entity, "UTF-8");

			EntityUtils.consume(entity);
		} catch (IOException e) {
			e.printStackTrace();
			LoggerUtil.error(e.getMessage());
			return getMyError("172001", "网络错误");
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtil.error(e.getMessage());
			return getMyError("172002", "无返回");
		} finally {
			if (httpclient != null)
				httpclient.getConnectionManager().shutdown();
		}

		LoggerUtil.info("voiceVerify response body = " + result);

		try {
			if (BODY_TYPE == BodyType.Type_JSON) {
				return jsonToMap(result);
			} else {
				return xmlToMap(result);
			}
		} catch (Exception e) {

			return getMyError("172003", "返回包体错误");
		}
	}



	private HashMap<String, Object> jsonToMap(String result) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		JsonParser parser = new JsonParser();
		JsonObject asJsonObject = parser.parse(result).getAsJsonObject();
		Set<Entry<String, JsonElement>> entrySet = asJsonObject.entrySet();
		HashMap<String, Object> hashMap2 = new HashMap<String, Object>();

		for (Map.Entry<String, JsonElement> m : entrySet) {
			if ("statusCode".equals(m.getKey())
					|| "statusMsg".equals(m.getKey()))
				hashMap.put(m.getKey(), m.getValue().getAsString());
			else {
				if ("SubAccount".equals(m.getKey())
						|| "totalCount".equals(m.getKey())
						|| "smsTemplateList".equals(m.getKey())
						|| "token".equals(m.getKey())
						|| "callSid".equals(m.getKey())
						|| "state".equals(m.getKey())
						|| "downUrl".equals(m.getKey())) {
					if (!"SubAccount".equals(m.getKey())
							&& !"smsTemplateList".equals(m.getKey()))
						hashMap2.put(m.getKey(), m.getValue().getAsString());
					else {
						try {
							if ((m.getValue().toString().trim().length() <= 2)
									&& !m.getValue().toString().contains("[")) {
								hashMap2.put(m.getKey(), m.getValue()
										.getAsString());
								hashMap.put("data", hashMap2);
								break;
							}
							if (m.getValue().toString().contains("[]")) {
								hashMap2.put(m.getKey(), new JsonArray());
								hashMap.put("data", hashMap2);
								continue;
							}
							JsonArray asJsonArray = parser.parse(
									m.getValue().toString()).getAsJsonArray();
							ArrayList<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String, Object>>();
							for (JsonElement j : asJsonArray) {
								Set<Entry<String, JsonElement>> entrySet2 = j
										.getAsJsonObject().entrySet();
								HashMap<String, Object> hashMap3 = new HashMap<String, Object>();
								for (Map.Entry<String, JsonElement> m2 : entrySet2) {
									hashMap3.put(m2.getKey(), m2.getValue()
											.getAsString());
								}
								arrayList.add(hashMap3);
							}
							hashMap2.put(m.getKey(), arrayList);
						} catch (Exception e) {
							JsonObject asJsonObject2 = parser.parse(
									m.getValue().toString()).getAsJsonObject();
							Set<Entry<String, JsonElement>> entrySet2 = asJsonObject2
									.entrySet();
							HashMap<String, Object> hashMap3 = new HashMap<String, Object>();
							for (Map.Entry<String, JsonElement> m2 : entrySet2) {
								hashMap3.put(m2.getKey(), m2.getValue()
										.getAsString());
							}
							hashMap2.put(m.getKey(), hashMap3);
							hashMap.put("data", hashMap2);
						}

					}
					hashMap.put("data", hashMap2);
				} else {

					JsonObject asJsonObject2 = parser.parse(
							m.getValue().toString()).getAsJsonObject();
					Set<Entry<String, JsonElement>> entrySet2 = asJsonObject2
							.entrySet();
					HashMap<String, Object> hashMap3 = new HashMap<String, Object>();
					for (Map.Entry<String, JsonElement> m2 : entrySet2) {
						hashMap3.put(m2.getKey(), m2.getValue().getAsString());
					}
					if (hashMap3.size() != 0) {
						hashMap2.put(m.getKey(), hashMap3);
					} else {
						hashMap2.put(m.getKey(), m.getValue().getAsString());
					}
					hashMap.put("data", hashMap2);
				}
			}
		}
		return hashMap;
	}

	/**
	 * @description 将xml字符串转换成map
	 * @param xml
	 * @return Map
	 */
	private HashMap<String, Object> xmlToMap(String xml) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xml); // 将字符串转为XML
			Element rootElt = doc.getRootElement(); // 获取根节点
			HashMap<String, Object> hashMap2 = new HashMap<String, Object>();
			ArrayList<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String, Object>>();
			for (Iterator i = rootElt.elementIterator(); i.hasNext();) {
				Element e = (Element) i.next();
				if ("statusCode".equals(e.getName())
						|| "statusMsg".equals(e.getName()))
					map.put(e.getName(), e.getText());
				else {
					if ("SubAccount".equals(e.getName())
							|| "TemplateSMS".equals(e.getName())
							|| "totalCount".equals(e.getName())
							|| "token".equals(e.getName())
							|| "callSid".equals(e.getName())
							|| "state".equals(e.getName())
							|| "downUrl".equals(e.getName())) {
						if (!"SubAccount".equals(e.getName())&&!"TemplateSMS".equals(e.getName())) {
							hashMap2.put(e.getName(), e.getText());
						} else if ("SubAccount".equals(e.getName())){

							HashMap<String, Object> hashMap3 = new HashMap<String, Object>();
							for (Iterator i2 = e.elementIterator(); i2
									.hasNext();) {
								Element e2 = (Element) i2.next();
								hashMap3.put(e2.getName(), e2.getText());
							}
							arrayList.add(hashMap3);
							hashMap2.put("SubAccount", arrayList);
						}else if ("TemplateSMS".equals(e.getName())){

							HashMap<String, Object> hashMap3 = new HashMap<String, Object>();
							for (Iterator i2 = e.elementIterator(); i2
									.hasNext();) {
								Element e2 = (Element) i2.next();
								hashMap3.put(e2.getName(), e2.getText());
							}
							arrayList.add(hashMap3);
							hashMap2.put("TemplateSMS", arrayList);
						}
						map.put("data", hashMap2);
					} else {

						HashMap<String, Object> hashMap3 = new HashMap<String, Object>();
						for (Iterator i2 = e.elementIterator(); i2.hasNext();) {
							Element e2 = (Element) i2.next();
							// hashMap2.put(e2.getName(),e2.getText());
							hashMap3.put(e2.getName(), e2.getText());
						}
						if (hashMap3.size() != 0) {
							hashMap2.put(e.getName(), hashMap3);
						} else {
							hashMap2.put(e.getName(), e.getText());
						}
						map.put("data", hashMap2);
					}
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
			LoggerUtil.error(e.getMessage());
		} catch (Exception e) {
			LoggerUtil.error(e.getMessage());
			e.printStackTrace();
		}
		return map;
	}

	private HttpRequestBase getHttpRequestBase(int get, String action)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return getHttpRequestBase(get, action, AccountType.Accounts);
	}

	private HttpRequestBase getHttpRequestBase(int get, String action,
			AccountType mAccountType) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		String timestamp = DateUtil.dateToStr(new Date(),
				DateUtil.DATE_TIME_NO_SLASH);
		EncryptUtil eu = new EncryptUtil();
		String sig = "";
		String acountName = "";
		String acountType = "";
		if (mAccountType == AccountType.Accounts) {
			acountName = ACCOUNT_SID;
			sig = ACCOUNT_SID + ACCOUNT_TOKEN + timestamp;
			acountType = "Accounts";
		} else {

		}
		String signature = eu.md5Digest(sig);

		String url = getBaseUrl().append("/" + acountType + "/")
				.append(acountName).append("/" + action + "?sig=")
				.append(signature).toString();
		
		LoggerUtil.info(getmethodName(action) + " url = " + url);
		HttpRequestBase mHttpRequestBase = null;
		if (get == Request_Get)
			mHttpRequestBase = new HttpGet(url);
		else if (get == Request_Post)
			mHttpRequestBase = new HttpPost(url);
		
		setHttpHeader(mHttpRequestBase);
		

		String src = acountName + ":" + timestamp;

		String auth = eu.base64Encoder(src);
		mHttpRequestBase.setHeader("Authorization", auth);
		return mHttpRequestBase;
	}

	private String getmethodName(String action) {
		if (action.equals(VoiceVerify)) {
			return "voiceVerify";
		} else {
			return "";
		}
	}

	private void setHttpHeader(AbstractHttpMessage httpMessage) {
		if (BODY_TYPE == BodyType.Type_JSON) {
			httpMessage.setHeader("Accept", "application/json");
			httpMessage.setHeader("Content-Type",
					"application/json;charset=utf-8");
		} else {
			httpMessage.setHeader("Accept", "application/xml");
			httpMessage.setHeader("Content-Type",
					"application/xml;charset=utf-8");
		}
	}

	private StringBuffer getBaseUrl() {
		StringBuffer sb = new StringBuffer("https://");
		sb.append(SERVER_IP).append(":").append(SERVER_PORT);
		sb.append("/2013-12-26");
		return sb;
	}

	private boolean isEmpty(String str) {
		return (("".equals(str)) || (str == null));
	}

	private HashMap<String, Object> getMyError(String code, String msg) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("statusCode", code);
		hashMap.put("statusMsg", msg);
		return hashMap;
	}

	private HashMap<String, Object> accountValidate() {
		if ((isEmpty(SERVER_IP))) {
			return getMyError("172004", "IP为空");
		}
		if ((isEmpty(SERVER_PORT))) {
			return getMyError("172005", "端口错误");
		}
		if ((isEmpty(ACCOUNT_SID))) {
			return getMyError("172006", "主帐号为空");
		}
		if ((isEmpty(ACCOUNT_TOKEN))) {
			return getMyError("172007", "主帐号TOKEN为空");
		}
		if ((isEmpty(App_ID))) {
			return getMyError("172012", "应用ID为空");
		}
		return null;
	}

	private void setBodyType(BodyType bodyType) {
		BODY_TYPE = bodyType;
	}
}