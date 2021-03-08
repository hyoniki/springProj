package com.webshw.service;

import com.webshw.domain.UserVO;
import com.webshw.dto.LoginDTO;

public interface UserService {
	public UserVO login(LoginDTO dto) throws Exception;
}
