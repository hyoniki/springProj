package com.webshw.interceptor;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.webshw.domain.UserVO;
import com.webshw.service.UserService;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	private static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	@Inject
	private UserService service;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession ses = request.getSession();
		
		if (ses.getAttribute("loginMember") == null) {
			// 로그인 하지 않음
			
			logger.info("현재 유저는 로그인 하지 않았다");
			
			writeDest(request, ses);
			
			Cookie loginCook = WebUtils.getCookie(request, "ssid");
			if (loginCook != null) {
				// 로그인 쿠키가 있다면(자동 로그인을 체크하고 7일 이내에 로그인 했던 유저)
				UserVO vo = service.checkUserWithSesKey(loginCook.getValue());
				
				logger.info("자동 로그인을 체크하고 7일 이내에 로그인 했던 유저" + vo.toString());
				
				if (vo != null) {
					ses.setAttribute("loginMember", vo); // 로그인 처리
					return true;
				}
			}
			
			response.sendRedirect("/user/login"); // sendRedirect는 get방식으로만 호출 가능, /user/login URI를 GET방식으로 호출 (login.jsp 이동)
			
			return false; // 컨트롤러가 작동 금지
			
		}
		
		return true; // 로그인 했다면, 컨트롤러 정상 작동 되도록 함
	}
	
	/**
	 * @Method Name : writeDest
	 * @작성일 : 2021. 3. 9.
	 * @작성자 : goott6
	 * 변경이력 :
	 * @Method 설명 : 요청된 URI주소와 쿼리스트링을 조합해 세션영역에 바인딩
	 * @param request : 요청 객체
	 * @param ses : 세션 객체
	 */
	private void writeDest(HttpServletRequest request, HttpSession ses) {
		
		String uri = request.getRequestURI(); // 요청된 URI를 얻어옴
		String queryStr = request.getQueryString(); // 쿼리 스트링 얻어옴
		
		if (queryStr == null || queryStr.equals("null")) {
			// 쿼리스트링이 없을 때
			queryStr = "";
		} else {
			queryStr = "?" + queryStr;
		}
		
		if (request.getMethod().toUpperCase().equals("GET")) {
			logger.info("최종 목적지 : " + (uri + queryStr));
			
			ses.setAttribute("dest", uri + queryStr);
		}
		
	}
	
	
}
