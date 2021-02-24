package com.webshw.springProj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CommonExceptionHandle {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@ExceptionHandler
	public ModelAndView commonError(Exception ex) {
		
		logger.info(ex.toString());
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("error");
		// "error"를 ViewResolver 객체에 전송하여 servlet-content.xml 파일에 /WEB-INF/views/ 이거에 의해 error.jsp를 찾게 만든다
		
		mav.addObject("exception", ex); // mav를 ex 객체 바인딩
		
		return mav;
		
	}
	
	
	
}
