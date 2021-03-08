package com.webshw.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.webshw.domain.UserVO;
import com.webshw.dto.LoginDTO;

@Repository
public class UserDAOImpl implements UserDAO {
	
	private static String namespace = "com.webshw.mapper.UserMapper";
	
	@Inject
	private SqlSession ses;
	
	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		return ses.selectOne(namespace + ".login", dto);
	}

}
