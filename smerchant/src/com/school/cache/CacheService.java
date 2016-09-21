package com.school.cache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


/**
 * 缓存用户信息
 * @author acode
 *
 */
@Service(value="cacheService")
public class CacheService {
	
	/**
	 * 获取用户短信码
	 * @param phone 电话
	 * @return
	 */
	@Cacheable(value="phoneMsg")
	public String getCache(String phone){
		
		return "1";
	}
	
	
	/**
	 * 存放短信验证码
	 * @param validateCode
	 */
	@CachePut(value="phoneMsg",key="#phone")
	public String addCache(String phone,String code){
		
		return code;
		
	}
	

	/**
	 * 清空验证码缓存
	 * @param phone
	 */
	@CacheEvict(value="phoneMsg",key="#phone")// 清空accountCache 缓存  
	public void removeCache(String phone){
		
	}
	
	/**
	 * 获取token缓存
	 * @param phone 电话
	 * @return
	 */
	@Cacheable(value="tokenMsg")
	public String getTokenCache(String phone){
		
		return "1";
	}
	
	
	/**
	 * 存放token缓存
	 * @param validateCode
	 */
	@CachePut(value="tokenMsg",key="#phone")
	public String addTokenCache(String phone,String code){
		
		return code;
		
	}
	
	

	
}
