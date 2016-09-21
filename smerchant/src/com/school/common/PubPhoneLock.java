package com.school.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 获取公共 map中的对象
 * @author acode
 *
 */
public class PubPhoneLock {
	private static Map<String,String> mapLock= new ConcurrentHashMap<String, String>();
	

	private static Map<String,String> mapmsg= new ConcurrentHashMap<String, String>();
	/**
	 * 通过phone获取公共对象
	 * @param phone
	 * @return
	 */
	public static synchronized String getPhoneLock(String phone){
		String o=null;
		 o=mapLock.get(phone);
			if(!phone.equals(o)){
				//System.out.println(Thread.currentThread().getName()+"map no found。。。。。");
				mapLock.put(phone, phone);
				o=mapLock.get(phone);
			}else{
				//System.out.println(Thread.currentThread().getName()+"map found。。。。。"+o);
			}
		return o;
		}
	
	public static  String getPhonemsg(String phone){
		
		return mapmsg.get(phone);
		}
	public static  void setPhonemsg(String phone,String code){
		
		mapmsg.put(phone,code);
		}
	
	

}
