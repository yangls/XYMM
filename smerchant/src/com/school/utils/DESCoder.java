package com.school.utils;

import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * DES加密
 * 
 *
 */
public class DESCoder {
	/**
	 * 加密0向量
	 */
	private static byte[] iv = {1,2,3,4,5,6,7,8};
	
	private static final String k = "zxcvzxcv";
	/**
	 * des加密
	 * @param encryptString 需要加密的字符串
	 * @param encryptKey 加密密钥
	 * @return 加密后的密文
	 * @throws Exception
	 */
	public static String encryptDES(String encryptString) throws Exception {
        IvParameterSpec zeroIv = new IvParameterSpec(iv);
        SecretKeySpec key = new SecretKeySpec(k.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
        byte[] encryptedData = cipher.doFinal(encryptString.getBytes("UTF-8"));
        return Base64.encode(encryptedData);
	 }

	/**
	 * DES解密
	 * @param decryptString 待解密的数据
	 * @param decryptKey 密钥
	 * @return 解密后的明文
	 * @throws Exception
	 */
	public static String decryptDES(String decryptString)throws Exception {
		
		if(decryptString.contains("%3D")){
			decryptString=decryptString.replaceAll("%3D","=");
		}
		if(decryptString.contains("%2B")){
			decryptString=decryptString.replaceAll("%2B","+");
		}
		if(decryptString.contains("%2F")){
			decryptString=decryptString.replaceAll("%2F","/");
		}
		if(decryptString.contains("%3F")||decryptString.contains("%3f")){
			decryptString=decryptString.replaceAll("%3F","?");
			decryptString=decryptString.replaceAll("%3f","?");
		}
		
		byte[] byteMi = Base64.decode(decryptString);
		IvParameterSpec zeroIv = new IvParameterSpec(iv);
		SecretKeySpec key = new SecretKeySpec(k.getBytes(), "DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
		byte decryptedData[] = cipher.doFinal(byteMi);
		return new String(decryptedData,"utf-8");
	}
	
	public static void main(String[] args) {
		
		//System.out.println("sdasd");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();

		//String[] toUsers=new String[]{"18788888836","18788888847"};
		//resultMap.put("phone", "18785133333");
		//PacketForm packet=new PacketForm();
		//packet.setFromUser("18785122153");
		//packet.setRedPacketNo("48eec61f-b0b8-432e-973d-7fafa5acab46");
		resultMap.put("phone", "18785122153");
		//resultMap.put("content", "几乎");
		//resultMap.put("token", "18153");
		//resultMap.put("resultToken", "992979");
		//resultMap.put("bankCode", "2");
		resultMap.put("password", "333444");
		resultMap.put("code", "888888");
		String str=JsonUtil.createJsonString(resultMap);
		System.out.println(str);
		try {
			str=DESCoder.encryptDES(str);
		// System.out.println(str);
		 System.out.println("密码:"+DESCoder.decryptDES("XQJejpjODvWcdNTrsO8keIF3DKlw3HepMFPTnJmEC+x4h6IZO3z6yKwt1tpR 9YGEdYjljQg/YKu/y8vdAEUispuAvnivjRbVQH8F1hxA6Bu0vQxWiOAhaJ73 Y/nXQjjQ"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
