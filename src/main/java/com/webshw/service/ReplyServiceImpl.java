package com.webshw.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webshw.domain.ReplyVO;
import com.webshw.persistence.BoardDAO;
import com.webshw.persistence.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Inject
	public ReplyDAO rdao;
	
	@Inject
	public BoardDAO bdao;
	
	@Transactional
	@Override
	public void addReply(ReplyVO vo) throws Exception {
		rdao.create(vo); // 댓글 insert
		bdao.updateReply(vo.getBno(), 1); // 댓글이 달린 부모글에 댓글 수 1증가
	}

	@Override
	public List<ReplyVO> listReply(int bno) throws Exception {
		return rdao.read(bno);
	}

	@Override
	public void modifyReply(ReplyVO vo) throws Exception {
		rdao.update(vo);
	}

	@Transactional
	@Override
	public void removeReply(int no) throws Exception {
		int bno = rdao.getBno(no); // 삭제되는 댓글의 부모글을 찾아오기
		rdao.delete(no); // 댓글 삭제
		bdao.updateReply(bno, -1); // 삭제된 부모글의 댓글 수 1감소
	}

}
