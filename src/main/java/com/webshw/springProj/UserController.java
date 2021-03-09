package com.webshw.springProj;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import com.webshw.domain.UserVO;
import com.webshw.dto.LoginDTO;
import com.webshw.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Inject
	private UserService service;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public void login() {

	}
	
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public void login(LoginDTO dto, HttpSession session, Model model) throws Exception {
		
		UserVO vo = service.login(dto);
		
		if (vo == null) {
			return;
		}
		
		model.addAttribute("loginMember", vo);
		
		if (dto.isUserCookie()) { // 자동 로그인 체크 했다면...
			int amount = 60 * 60 * 24 * 7; // 일주일의 milliSecond
			
			Date sesLimit = new Date(System.currentTimeMillis() + (amount * 1000));
			
			// 로그인 쿠키 값이 유지되는 시간, sessionID를 로그인한 유저의 정보에 update
			service.keepLogin(dto.getUid(), session.getId(), sesLimit);
			
		}
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		
		UserVO vo = (UserVO)session.getAttribute("loginMember");
		
		if (vo != null) {
			session.removeAttribute("loginMember");
			session.invalidate();
			
			Cookie loginCook = WebUtils.getCookie(request, "ssid");
			
			if (loginCook != null) {
				loginCook.setPath("/");
				loginCook.setMaxAge(0);
				response.addCookie(loginCook);
				service.keepLogin(vo.getUid(), session.getId(), new Date());
			}
			
		}
		return "/user/logout";
	}
	
	
}
