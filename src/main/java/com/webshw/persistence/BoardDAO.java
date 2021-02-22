package com.webshw.persistence;

import java.util.List;

import com.webshw.domain.BoardVO;

public interface BoardDAO {
	
	// 글 쓰기
	public int insert(BoardVO vo) throws Exception;
	
	// 상세 글 조회
	public BoardVO readBoard(int no) throws Exception;
	
	// 수정
	public int update(BoardVO vo) throws Exception;
	
	// 삭제
	public int delete(int no) throws Exception;
	
	// 전체 글 조회
	public List<BoardVO> listBoard() throws Exception;
	
}
