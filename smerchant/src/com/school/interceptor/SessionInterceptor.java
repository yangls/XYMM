package com.school.interceptor;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.school.utils.DESCoder;
import com.school.utils.JsonUtil;
import com.school.utils.StringUtil;

public class SessionInterceptor implements HandlerInterceptor {

	// 完成后调用
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
	}

	// 完成后最后调用
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2, ModelAndView modelAndView)
			throws Exception {
	}

	// 进来时判断
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object object) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		Map<String, Object> map = new HashMap<String, Object>();
		PrintWriter out = response.getWriter();
		String str = request.getParameter("params");
		if (StringUtil.isNotEmpty(str)) {
			map = JsonUtil.parseMap(DESCoder.decryptDES(str));
			Object token = map.get("token");
			Object phone = map.get("phone");
			if (token != null && phone != null) {
				map.put("ret", 1);
				map.put("msg", "参数不全");
				//System.out.println("通过验证");
				return true;

			}
		}
		//System.out.println("没有通过验证");
		map.put("ret", 1);
		map.put("msg", "参数不全");
		str = DESCoder.encryptDES(JsonUtil.createJsonString(map));
		out.print(str);
		out.close();
		//response.sendRedirect(request.getContextPath());
		return false;
	}

}
