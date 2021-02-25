package com.webshw.service;

import java.util.List;

import com.webshw.domain.ReplyVO;

public interface ReplyService {
	
	public void addReply(ReplyVO vo) throws Exception;
	
	public List<ReplyVO> listReply(int bno) throws Exception;
	
	public void modifyReply(ReplyVO vo) throws Exception;
	
	public void removeReply(int no) throws Exception;
	
}
