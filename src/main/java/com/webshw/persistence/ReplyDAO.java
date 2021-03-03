package com.webshw.persistence;

import java.util.List;

import com.webshw.domain.ReplyVO;

public interface ReplyDAO {
	
	public void create(ReplyVO vo) throws Exception;
	
	public List<ReplyVO> read(int bno) throws Exception;
	
	public void update(ReplyVO vo) throws Exception;
	
	public void delete(int no) throws Exception;
	
	// 댓글 삭제 시 게시판 목록 출력할 때 댓글 수를 출력하기 위함
	public int getBno(int no) throws Exception;
}
