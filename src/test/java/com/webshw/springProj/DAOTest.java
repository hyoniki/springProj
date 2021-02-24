package com.webshw.springProj;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.webshw.domain.BoardVO;
import com.webshw.domain.PagingCriteria;
import com.webshw.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class) // 현재 클래스가 Spring-test(JUnit4)와 함께 동작
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}
		) // 위 경로에서 root-context.xml 파일을 찾아 로딩
public class DAOTest {
	
	@Inject
	private BoardDAO dao;
	
//	@Test
//	public void insertBoard() throws Exception {
//		BoardVO vo = new BoardVO();
//		vo.setTitle("제목");
//		vo.setContent("내용");
//		vo.setWriter("작성자");
//		
//		int i = dao.insert(vo);
//		
//		if (i == 1) {
//			System.out.println("글 등록 성공");
//		}
//		
//	}
//	
//	@Test
//	public void readBoard() throws Exception {
//		
//		System.out.println(dao.readBoard(1).toString());
//		
//	}
//	
//	@Test
//	public void update() throws Exception {
//		BoardVO vo = new BoardVO();
//		
//		vo.setNo(1);
//		vo.setTitle("제목 변경");
//		vo.setContent("내용 변경");
//		
//		if (dao.update(vo) == 1) {
//			System.out.println("글 수정 성공");
//		}
//	}
//	
//	@Test
//	public void delete() throws Exception {
//		
//		int i = dao.delete(3);
//		
//		if (i == 1) {
//			System.out.println("글 삭제 성공");
//		}
//		
//	}
//	
//	@Test
//	public void listBoard() throws Exception {
//		
//		List<BoardVO> lst = dao.listBoard();
//		
//		System.out.println(lst.toString());
//		
//	}
//	
//	@Test
//	public void listBoardPaging() throws Exception {
//		
//		List<BoardVO> lst = dao.listBoardPaging(3);
//		
//		System.out.println(lst.toString());
//	}
	
	@Test
	public void testListCriteria() throws Exception {
		PagingCriteria cri = new PagingCriteria();
		cri.setPage(1);
		cri.setPerPageNum(20);
		
		System.out.println(dao.listBoardCriteria(cri).toString());
	}
}
