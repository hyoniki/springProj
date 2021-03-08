package com.webshw.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.webshw.domain.UserVO;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	private static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		logger.info("로그인 하기 전......................................");
		
		HttpSession ses = request.getSession();
		System.out.println("세션 1 : " + ses.toString());
		
		if (ses.getAttribute("loginMember") != null) {
			ses.removeAttribute("loginMemer");
		}
		
		return true;
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
		HttpSession ses = request.getSession();
		System.out.println("세션 2 : " + ses);
		
		ModelMap modelMap = modelAndView.getModelMap();
		
		UserVO vo = (UserVO)modelMap.get("loginMember");
		
		if (vo != null) {
			
			logger.info("로그인 성공!!");
			
			
			ses.setMaxInactiveInterval(20 * 3600);
			ses.setAttribute("loginMember", vo);
			
			System.out.println("테슷흐흐흐흐 :" + ses.getAttribute("loginMember").toString());
			
			response.sendRedirect("/");
		}
		
	}
	
	
	
	
}
