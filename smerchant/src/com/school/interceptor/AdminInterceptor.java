package com.school.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AdminInterceptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj) throws Exception {
		//System.out.println("没有感动过");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		HttpSession session=request.getSession();
		if(session.getAttribute("user")==null){
			//PrintWriter out=response.getWriter();
			//out.print("请求无效，请重新登陆");
			//out.close();
			response.sendRedirect(request.getContextPath()+"/xymm/index.do");
			
			return false;
		}
		////System.out.println("en"+session.getAttribute("user").toString());
		return true;
		
	}

}
