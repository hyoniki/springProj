package com.webshw.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.webshw.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Inject
	private SqlSession ses;
	private static String nameSpace = "com.webshw.mapper.BoardMapper";
	
	@Override
	public int insert(BoardVO vo) throws Exception {
		
		return ses.insert(nameSpace + ".insertBoard", vo);
		
	}

	@Override
	public BoardVO readBoard(int no) throws Exception {
		// TODO Auto-generated method stub
		return ses.selectOne(nameSpace + ".readBoard", no);
	}

	@Override
	public int update(BoardVO vo) throws Exception {
		return ses.update(nameSpace + ".updateBoard", vo);
	}

	@Override
	public int delete(int no) throws Exception {
		return ses.update(nameSpace + ".deleteBoard", no);
	}

	@Override
	public List<BoardVO> listBoard() throws Exception {
		return ses.selectList(nameSpace + ".listBoard");
	}

}
