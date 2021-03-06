package com.webshw.service;

import java.util.List;

import com.webshw.domain.BoardVO;
import com.webshw.domain.PagingCriteria;
import com.webshw.domain.SearchCriteria;

public interface BoardSercvice {
	public boolean insert(BoardVO vo) throws Exception;
	
	public BoardVO read(int no) throws Exception;
	
	public boolean modify(BoardVO vo) throws Exception;
	
	public boolean remove(int no) throws Exception;
	
	public List<BoardVO> listAll() throws Exception;
	
	public List<BoardVO> listCriteria(PagingCriteria cri) throws Exception;

	public int getTotalBoardCnt() throws Exception;
	
	public List<BoardVO> goSearch(SearchCriteria scri, PagingCriteria cri) throws Exception;

	public int searchTotal(SearchCriteria scri) throws Exception;
}
