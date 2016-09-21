package com.school.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class Base64 {
	private static final char[] legalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
			.toCharArray();

	/**
	 * 可采用一种用于URL的改进Base64编码，
	 * 它不仅在末尾填充'='号，并将标准Base64中的“+”和“/”分别改成了“-”和“_”，
	 * 这样就免去了在URL编解码和数据库存储时所要作的转换，
	 * 避免了编码信息长度在此过程中的增加，并统一了数据库、表单等处对象标识符的格式。
	 */
	/**
	 * data[]进行编码
	 * 
	 * @param data
	 * @return
	 */
	public static String encode(byte[] data) {
		int start = 0;
		int len = data.length;
		StringBuffer buf = new StringBuffer(data.length * 3 / 2);

		int end = len - 3;
		int i = start;
		int n = 0;

		while (i <= end) {
			int d = ((((int) data[i]) & 0x0ff) << 16)
					| ((((int) data[i + 1]) & 0x0ff) << 8)
					| (((int) data[i + 2]) & 0x0ff);

			buf.append(legalChars[(d >> 18) & 63]);
			buf.append(legalChars[(d >> 12) & 63]);
			buf.append(legalChars[(d >> 6) & 63]);
			buf.append(legalChars[d & 63]);

			i += 3;

			if (n++ >= 14) {
				n = 0;
				buf.append(" ");
			}
		}

		if (i == start + len - 2) {
			int d = ((((int) data[i]) & 0x0ff) << 16)
					| ((((int) data[i + 1]) & 255) << 8);

			buf.append(legalChars[(d >> 18) & 63]);
			buf.append(legalChars[(d >> 12) & 63]);
			buf.append(legalChars[(d >> 6) & 63]);
			buf.append("=");
		} else if (i == start + len - 1) {
			int d = (((int) data[i]) & 0x0ff) << 16;

			buf.append(legalChars[(d >> 18) & 63]);
			buf.append(legalChars[(d >> 12) & 63]);
			buf.append("==");
		}

		return buf.toString();
	}

	private static int decode(char c) {
		if (c >= 'A' && c <= 'Z')
			return ((int) c) - 65;
		else if (c >= 'a' && c <= 'z')
			return ((int) c) - 97 + 26;
		else if (c >= '0' && c <= '9')
			return ((int) c) - 48 + 26 + 26;
		else
			switch (c) {
			case '+':
				return 62;
			case '/':
				return 63;
			case '=':
				return 0;
			default:
				throw new RuntimeException("unexpected code: " + c);
			}
	}
	/**
	 * 按utf-8编码返回
	 * @param s
	 * @return
	 */
	public static String decodeReturnString(String s){
		try {
			return new String(Base64.decode(s),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO 这个出错不了的。。
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * Decodes the given Base64 encoded String to a new byte array. The byte
	 * array holding the decoded data is returned.
	 */

	public static byte[] decode(String s) {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			decode(s, bos);
		} catch (IOException e) {
			throw new RuntimeException();
		}
		byte[] decodedBytes = bos.toByteArray();
		try {
			bos.close();
			bos = null;
		} catch (IOException ex) {
			System.err.println("Error while decoding BASE64: " + ex.toString());
		}
		return decodedBytes;
	}

	private static void decode(String s, OutputStream os) throws IOException {
		int i = 0;

		int len = s.length();

		while (true) {
			while (i < len && s.charAt(i) <= ' ')
				i++;

			if (i == len)
				break;

			int tri = (decode(s.charAt(i)) << 18)
					+ (decode(s.charAt(i + 1)) << 12)
					+ (decode(s.charAt(i + 2)) << 6)
					+ (decode(s.charAt(i + 3)));

			os.write((tri >> 16) & 255);
			if (s.charAt(i + 2) == '=')
				break;
			os.write((tri >> 8) & 255);
			if (s.charAt(i + 3) == '=')
				break;
			os.write(tri & 255);

			i += 4;
		}
	}
	public static void main(String[] args) throws UnsupportedEncodingException{
		String fordecode="测试~测试~aaa嘿嘿嘿~";
		String encoderesult=Base64.encode(fordecode.getBytes());
		//System.out.println(fordecode);
		//System.out.println("编码后:"+encoderesult);
		//System.out.println("编码替换后:"+Base64.convertSafeStringOut(encoderesult));
		String decodeString=Base64.convertSafeStringIn(encoderesult);
		//System.out.println("解码的字符串:"+decodeString);
		//System.out.println("解码后:"+Base64.decodeReturnString(decodeString));
			
	}
	/**
	 * 替换掉+，/为-和_，用以传输给客户端
	 * @param out
	 * @return
	 */
	private static String convertSafeStringOut(String out){
		return out.replace('+', '-').replace('/', '_');
	}
	/**
	 * 替换掉-，_为+和/，用以base64解密
	 * @param in
	 * @return
	 */
	private static String convertSafeStringIn(String in){
		return in.replace('-', '+').replace('_', '/');
	}
}