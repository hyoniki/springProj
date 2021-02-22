package com.webshw.springProj;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.webshw.domain.BoardVO;
import com.webshw.persistence.BoardDAO;
import com.webshw.service.BoardSercvice;

@RunWith(SpringJUnit4ClassRunner.class) // 현재 클래스가 Spring-test(JUnit4)와 함께 동작
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}
		) // 위 경로에서 root-context.xml 파일을 찾아 로딩
public class serviceTest {

	@Inject
	private BoardSercvice service;
	
//	@Test
//	public void insert() throws Exception {
//		BoardVO vo = new BoardVO();
//		vo.setTitle("서비스 테스트");
//		vo.setContent("테슷흐");
//		vo.setWriter("어드민");
//		if(service.insert(vo)) {
//			System.out.println("글 등록 성공");
//		} else {
//			System.out.println("글 등록 실패");
//		}
//		
//	}

//	@Test
//	public void read() throws Exception {
//		System.out.println(service.read(1));
//	}
	
	@Test
	public void modify() throws Exception {
		
		BoardVO vo = new BoardVO();
		
		vo.setNo(5);
		vo.setTitle("수정되었는가?");
		vo.setContent("그렇지?");
		if(service.modify(vo)) {
			System.out.println("수정 성공");
		}
	}
	
//	@Test
//	public void delete() throws Exception {
//		if(service.remove(2)) {
//			System.out.println("글 삭제 성공");
//		}
//	}
	
//	@Test
//	public void listAll() throws Exception {
//		List<BoardVO> vo = service.listAll();
//		
//		System.out.println(vo.toString());
//	}
	
}
