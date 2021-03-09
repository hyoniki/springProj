package com.webshw.service;

import java.util.Date;

import com.webshw.domain.UserVO;
import com.webshw.dto.LoginDTO;

public interface UserService {
	
	public UserVO login(LoginDTO dto) throws Exception;
	
	// 유저의 세션ID를 체크
	public UserVO checkUserWithSesKey(String sesKey) throws Exception;
	
	// 로그인을 유지하기 위한
	public void keepLogin(String uid, String sesId, Date cookieAge) throws Exception;
	
}
