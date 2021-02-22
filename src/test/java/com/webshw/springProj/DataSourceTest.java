package com.webshw.springProj;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(SpringJUnit4ClassRunner.class) // 현재 클래스가 Spring-test(JUnit4)와 함께 동작
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}
		) // 위 경로에서 root-context.xml 파일을 찾아 로딩
public class DataSourceTest {
	
	private static final Logger logger = LoggerFactory.getLogger(DataSourceTest.class); // 로그를 기록하기 위해 로거 객체 생성
	
	@Inject // root-context.xml 파일의 bean태그에서 Spring컨테이너에 의해 만들어져 관리되고 있는 DataSource객체를 찾아 주입
	private DataSource ds;
	
	@Test
	public void dataSourceConnection() throws SQLException {
		try(Connection con = ds.getConnection()) {
			logger.info("db 커넥션 성공" + con.toString());
			System.out.println(con.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
