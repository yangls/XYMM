package com.school.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonUtil {
	
	private JsonUtil(){}
	/**
	 * 把一个对象转换成json字符串
	 * @param object
	 * @return
	 */
	public static final String createJsonString(Object object){
		String jsonString = JSON.toJSONString(object,SerializerFeature.DisableCircularReferenceDetect);
		return jsonString;
	}

	/**
	 * 把一个json字符串解析成一个对象
	 * @param <T> 
	 * @param jsonString
	 * @param cls 对象的类型
	 * @return
	 */
	public final static <T> T parseObject(String jsonString,Class<T> cls){
		T t = JSON.parseObject(jsonString,cls);
		return t;
	}
	/**
	 * 把一个json字符串解析成一个对象数组
	 * @param <T>
	 * @param jsonString
	 * @param cls
	 * @return
	 */
	public final static <T> List<T> parseArray(String jsonString,Class<T> cls){
		List<T> list = null;
		list = JSON.parseArray(jsonString,cls);
		return list;
	}
	/**
	 * 把一个json字符串转换成一个map的集合
	 * @param jsonString
	 * @return
	 */
	public final static List<Map<String, Object>> parseMapList(String jsonString){
		List<Map<String,Object>> list = JSON.parseObject(jsonString,
				new TypeReference<List<Map<String,Object>>>(){});
		return list;
	}
	/**
	 * 把一个json字符串转换成一个map
	 * @param jsonString
	 * @return
	 */
	public final static Map<String, Object> parseMap(String jsonString){
		Map<String,Object> map = JSON.parseObject(jsonString,
				new TypeReference<Map<String,Object>>(){});
		return map;
	}
	/**
	 * 把对象转为JSON字符串(过滤某些字段)
	 * @param obj
	 * @param propertyFilter
	 * @return
	 */
	public static final String createJsonString(Object obj,PropertyFilter propertyFilter){
		SerializeWriter sw = new SerializeWriter();
		JSONSerializer serializer = new JSONSerializer(sw);
		serializer.getPropertyFilters().add(propertyFilter);
		serializer.write(obj);
		return sw.toString();
	}
}
