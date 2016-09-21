package com.school.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.util.Assert;

import com.alibaba.fastjson.serializer.PropertyFilter;
/**
 * 自定义属性过滤器
 */
public class ExcludeNamePropertyFilter  implements PropertyFilter{
	
	private List<String> excludeNames;
	

	public ExcludeNamePropertyFilter(List<String> excludeNames) {
		Assert.notNull(excludeNames);
		this.excludeNames = excludeNames;
	}
	public ExcludeNamePropertyFilter(String [] excludeNames){
		this.excludeNames = Collections.unmodifiableList(Arrays.asList(excludeNames));
	}

	public boolean apply(Object source, String name, Object value) {
		if(excludeNames.contains(name)){
			return false;
		}
		return true;
	}
}
