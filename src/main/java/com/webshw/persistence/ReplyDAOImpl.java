package com.webshw.persistence;

import java.util.List;

import javax.inject.Inject;
import javax.xml.stream.events.Namespace;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.webshw.domain.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
	
	@Inject
	private SqlSession ses;
	
	private static final String ns = "com.webshw.mapper.ReplyMapper";
	
	@Override
	public void create(ReplyVO vo) throws Exception {
		ses.insert(ns + ".create", vo);
		
	}

	@Override
	public List<ReplyVO> read(int bno) throws Exception {
		return ses.selectList(ns + ".read", bno);
	}

	@Override
	public void update(ReplyVO vo) throws Exception {
		ses.update(ns + ".update", vo);
		
	}

	@Override
	public void delete(int no) throws Exception {
		ses.delete(ns + ".delete", no);
		
	}

	@Override
	public int getBno(int no) throws Exception {
		return ses.selectOne(ns + ".getbno", no);
	}

}
