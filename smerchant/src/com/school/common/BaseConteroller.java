package com.school.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.school.utils.DESCoder;
import com.school.utils.JsonUtil;
import com.school.utils.StringUtil;
/**
 * controller基类
 * @author Acode
 *
 */
public class BaseConteroller {
	
	protected HttpSession session;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected PrintWriter out;
	protected Model model;
	protected int page=1;
	protected int rows=10;
	
	
	
	//在此controller每个方法执行前被执行
	@ModelAttribute
	public void init(HttpServletRequest request,HttpServletResponse response, HttpSession session,Model model){
		this.request=request;
		this.response=response;
		this.session=session;
		this.model=model;
		
		try {
			response.setCharacterEncoding("utf8");
			response.setContentType("text/html");
			out=response.getWriter();
			//if(out==null){}
		} catch (IOException e) {
			//System.out.println("zheg.....");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 解密
	 * @param params
	 * @return
	 */
	public Map<String,Object> analysis(String decryptString){
		try {
			String jsonString=DESCoder.decryptDES(decryptString);
			//System.out.println("解密为::"+jsonString);
			return JsonUtil.parseMap(jsonString);
		} catch (Exception e) {
			System.out.println("解密失败!");
			e.printStackTrace();
			return null;
		}
	}

}
