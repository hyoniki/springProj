package com.webshw.persistence;

import com.webshw.domain.MessageVO;

public interface MessageDAO {
	
	public void create(MessageVO vo) throws Exception;
	
	public MessageVO readMessage(int mid) throws Exception;
	
	public void updateState(int mid) throws Exception;
	
}
