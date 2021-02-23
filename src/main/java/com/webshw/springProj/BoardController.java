package com.webshw.springProj;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webshw.domain.BoardVO;
import com.webshw.service.BoardSercvice;

@Controller
@RequestMapping("/board/*") // board/ 의 하위 모든 URI에 대해 현재 클래스가 동작함을 의미
public class BoardController {
	
	@Inject
	private BoardSercvice service;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String registerGet(BoardVO vo, Model model) {
		// 글 등록 페이지 호출
		
		logger.info("/register... get 호출");
		return "registerBoard";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerPost(BoardVO vo, Model model) throws Exception {
		
		logger.info("/register... post 호출");
		logger.debug(vo.toString());
		
		if(service.insert(vo)) {
			model.addAttribute("result", "success");
		}
		
		return "/board/success";
	}
	
	
	
}
