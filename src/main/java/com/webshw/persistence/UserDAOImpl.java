package com.webshw.persistence;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

	@Override
	public UserVO checkUserWithSesKey(String sesKey) throws Exception {
		return ses.selectOne(namespace + ".checkUserWithSeskey", sesKey);
	}

	@Override
	public void keepLogin(String uid, String sesId, Date cookieAge) throws Exception {
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		
		paraMap.put("sesId", sesId);
		paraMap.put("cookieAge", cookieAge);
		paraMap.put("uid", uid);
		
		ses.update(namespace + ".keepLogin", paraMap);
	}

}
