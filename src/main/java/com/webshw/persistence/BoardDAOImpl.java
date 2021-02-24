package com.webshw.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.webshw.domain.BoardVO;
import com.webshw.domain.PagingCriteria;

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
	
	@Override
	public List<BoardVO> listBoardPaging(int page) throws Exception {

		if (page <= 0) {
			page = 1;
		}
		
		page = (page - 1) * 10;
		
		return ses.selectList(nameSpace + ".listBoardPaging", page);
	}

	@Override
	public List<BoardVO> listBoardCriteria(PagingCriteria cri) throws Exception {
		return ses.selectList(nameSpace + ".listBoardCriteria", cri);
	}

	@Override
	public int getTotalBoardCnt() throws Exception {
		return ses.selectOne(nameSpace + ".getTotalBoardCnt");
	}

}
