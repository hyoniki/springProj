package com.webshw.service;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.webshw.domain.UserVO;
import com.webshw.dto.LoginDTO;
import com.webshw.persistence.UserDAO;

@Service
public class UserServiceImpl implements UserService {

	@Inject
	private UserDAO dao;
	
	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		return dao.login(dto);
	}

	@Override
	public UserVO checkUserWithSesKey(String sesKey) throws Exception {
		return dao.checkUserWithSesKey(sesKey);
	}

	@Override
	public void keepLogin(String uid, String sesId, Date cookieAge) throws Exception {
		dao.keepLogin(uid, sesId, cookieAge);
	}

}
