package com.webshw.interceptor;

import javax.servlet.http.Cookie;
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
		
		if (ses.getAttribute("loginMember") != null) { // 이전 로그인 정보가 세션에 남아있다면
			ses.removeAttribute("loginMemer"); // 로그인 정보 삭제
		}
		
		return true; // 이후 인터셉터 또는 컨트롤러에게 제워권을 넘김
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
		HttpSession ses = request.getSession();
		
		ModelMap modelMap = modelAndView.getModelMap();
		
		UserVO vo = (UserVO)modelMap.get("loginMember");
		
		if (vo != null) {
			
			logger.info("로그인 성공!!");
			logger.info("로그인 정보" + vo.toString());
			
			ses.setAttribute("loginMember", vo);
			
			// 쿠키 처리
			if (request.getParameter("userCookie") != null) { // 자동 로그인이 체크되었을 때
				logger.info("쿠키 처리...");
				
				Cookie loginCook = new Cookie("ssid", ses.getId()); // ssid라는 이름으로 세션id 값 저장
				loginCook.setPath("/");
				loginCook.setMaxAge(60 * 60 * 24 * 7); // 일주일 동안
				response.addCookie(loginCook);
			}
			
			String dest = (String)ses.getAttribute("dest");
			
			response.sendRedirect((dest != null)? dest : "/");
		}
		
	}
	
	
	
	
}
