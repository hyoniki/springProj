package com.webshw.springProj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonExceptionHandle {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@ExceptionHandler
	public String commonError(Exception ex) {
		
		logger.info(ex.toString());
		
		return "error";
	}
	
	
	
}
