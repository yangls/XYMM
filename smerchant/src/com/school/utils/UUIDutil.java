package com.school.utils;

import java.util.UUID;

public class UUIDutil {
	
	/**
	 * 创建uuid
	 * @return
	 */
	public static String createUuid(){
		 UUID uuid = UUID.randomUUID();
		
		return uuid.toString().replaceAll("-", "");
	}
	
	public static void main(String[] args) {
		
		
	}

}
