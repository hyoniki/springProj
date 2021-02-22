package com.webshw.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.webshw.domain.BoardVO;
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

	@Override
	public BoardVO read(int no) throws Exception {
		// 이후에 조회수 증가하는 것을 AOP의 트랜잭션 처리로 마감
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

}
