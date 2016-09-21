package com.school.controller.admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.school.bean.AdminUser;
import com.school.common.BaseConteroller;
import com.school.form.AjaxForm;
import com.school.service.AdminUserService;
import com.school.utils.AuthImageUtil;
import com.school.utils.JsonUtil;
import com.school.utils.MD5Coder;
import com.school.utils.StringUtil;

@Controller
@RequestMapping("/xymm")
public class LoginCotroller extends BaseConteroller {

	
	
	@Resource
	private AdminUserService adminUserService;

	@RequestMapping("/index")
	private String index() {

		return "../../login";

	}

	@RequestMapping("/login")
	private String login(String user, String password,String yzm) {
		
		if(session.getAttribute("rand")==null){
			return "../../login";
		}
		String yzm1=session.getAttribute("rand").toString();

		if(!yzm1.equals(yzm)){
			request.setAttribute("msg", "验证码错误");
		}else{
			if (StringUtil.isNotEmpty(user) && StringUtil.isNotEmpty(password)) {
				password = MD5Coder.GetMD5Code(password);
				password = MD5Coder.GetMD5Code(password);
				AdminUser adminUser = adminUserService.userLogin(user, password);
				if (adminUser != null) {
					session.setAttribute("user", user);
					return "a/home";
				}
			}
			request.setAttribute("msg", "用户名或密码错误");
		}
		
		
		return "../../login";

	}

	@RequestMapping("/userValidation")
	private void userValidation(String password) {
		AjaxForm ajaxForm=new AjaxForm();
		//System.out.println("come!");
		password = MD5Coder.GetMD5Code(password);
		password = MD5Coder.GetMD5Code(password);
		AdminUser adminUser = adminUserService.userLogin(session
				.getAttribute("user").toString(), password);
		if (adminUser == null) {
			ajaxForm.setRet(1);
		}
		//System.out.println(JsonUtil.createJsonString(ajaxForm));
		out.print(JsonUtil.createJsonString(ajaxForm));
		out.flush();
	}

	
	@RequestMapping("/authImageServlet")
	private void authImageServlet(HttpServletRequest request, HttpServletResponse response) {
		AuthImageUtil.authImageServlet(request, response,session);
        
		
	}
	
	  
	
	@RequestMapping("/init")
	private void init() {

		try {
			AdminUser adminUser = new AdminUser("admin",
					"1cc39ffd758234422e1f75beadfc5fb2");
			adminUserService.save(adminUser);
			out.print("初始化完成");
		} catch (Exception e) {
			// TODO: handle exception
			out.print("初始化失败!:" + e.getMessage());
		}

	}

}
