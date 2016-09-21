package com.school.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CreateSerialNo {

	static SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyyMMddHHmmss");
	static Random rand = new Random();
	/**
	 * 创建一个随机号年月日时分秒加随机数
	 * @return
	 */
	public static String createNo(){
		Date nowTime = new Date();
		  
		  String retStrFormatNowDate = sdFormatter.format(nowTime);
		  int randNum = rand.nextInt(999999990)+1000000000;
		
		return retStrFormatNowDate+randNum;
		
	}
	
	public static String createVoiceMsg(){
		String msg="";
		  for(int i=0;i<6;i++){
			  msg+=rand.nextInt(10);
		  }
		
		return msg;
		
	}
	public static void main(String[] args) {
		 //System.out.println(createVoiceMsg());
		  
	}
}
