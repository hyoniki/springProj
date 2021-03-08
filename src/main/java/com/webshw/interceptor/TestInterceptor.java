package com.webshw.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.webshw.domain.BoardVO;

public class TestInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		System.out.println("***************** Test Interceptor **********************");
		System.out.println("호출 방식 : " + request.getMethod());
		System.out.println("호출된 URI" + request.getRequestURL());
		System.out.println("접속된 세션: " + request.getSession().getId());
		
		return true; // true를 반환하면 다음 Interceptor나 대상 컨트롤러를 호출하게 됨
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
		System.out.println("***************** Test Interceptor (컨트롤러 다녀 왔음) **********************");
		Map<String, Object> modelMap = modelAndView.getModel();
//		List<BoardVO> lst = (List<BoardVO>) modelMap.get("boardlist");
//		System.out.println(lst.toString());
		
		View view = modelAndView.getView();
		System.out.println(view.getClass().toString() + "를 호출해야 함");
		
	}
	
	
}
