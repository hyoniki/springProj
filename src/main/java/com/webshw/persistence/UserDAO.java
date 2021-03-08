package com.webshw.persistence;

import com.webshw.domain.UserVO;
import com.webshw.dto.LoginDTO;

public interface UserDAO {
	public UserVO login(LoginDTO dto) throws Exception;
}
