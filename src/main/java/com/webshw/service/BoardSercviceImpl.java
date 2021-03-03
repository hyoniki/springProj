package com.webshw.service;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.webshw.domain.BoardVO;
import com.webshw.domain.PagingCriteria;
import com.webshw.domain.SearchCriteria;
import com.webshw.persistence.BoardDAO;

@Service
public class BoardSercviceImpl implements BoardSercvice {

	@Inject
	private BoardDAO dao;
	
	@Override
	public boolean insert(BoardVO vo) throws Exception {
		boolean result = false;
		
		if (dao.insert(vo) == 1) {
			result = true;
		}
		
		return result;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED) // 조회수 update 처리된 데이터에 한해 select 되게끔 격리 레벨을 올림
	@Override
	public BoardVO read(int no) throws Exception {
		// 이후에 조회수 증가하는 것을 AOP의 트랜잭션 처리로 마감
		
		dao.updateViewCnt(no);
		return dao.readBoard(no);
	}

	@Override
	public boolean modify(BoardVO vo) throws Exception {
		boolean result = false;
		
		if (dao.update(vo) == 1) {
			result = true;
		}
		
		return result;
	}

	@Override
	public boolean remove(int no) throws Exception {
		boolean result = false;
		
		if(dao.delete(no) == 1) {
			result = true;
		}
		return result;
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		List<BoardVO> lst = dao.listBoard();
		return lst;
	}

	@Override
	public List<BoardVO> listCriteria(PagingCriteria cri) throws Exception {
		return dao.listBoardCriteria(cri);
	}

	@Override
	public int getTotalBoardCnt() throws Exception {
		return dao.getTotalBoardCnt();
	}
	
	@Override
	public List<BoardVO> goSearch(SearchCriteria scri, PagingCriteria cri) throws Exception {
		return dao.goSearch(scri, cri);
	}

	@Override
	public int searchTotal(SearchCriteria scri) throws Exception {
		return dao.searchTotal(scri);
	}
}
