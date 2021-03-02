package com.webshw.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.webshw.domain.MessageVO;
import com.webshw.persistence.MessageDAO;
import com.webshw.persistence.PointDAO;

@Service
public class MessageServiceImpl implements MessageService {

	@Inject
	private MessageDAO mdao;
	
	@Inject
	private PointDAO pdao;
	
	@Override
	public void addMessage(MessageVO vo) throws Exception {
		mdao.create(vo);
		pdao.updatePoint(vo.getSender(), 10); // 메시지 발송자에게 10포인트 적립

	}

	@Override
	public MessageVO readMessage(String uid, int mid) throws Exception {
		mdao.updateState(mid); // 메시지를 읽은 상태로 변경
		pdao.updatePoint(uid, 5); // 메시지 수신자에게 5포인트 적립
		return mdao.readMessage(mid); // 메시지 읽어서 반환
	}

}
