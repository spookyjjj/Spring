package kr.co.greenart;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import kr.co.greenart.config.RootConfig;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { RootConfig.class })
public class JdbcTemplateTest {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Test
	public void queryForObject() {
		//jdbcTemplate : 커넥션 열고닫고도 없고, rs도 없고, sql Exception대신 런타임 Exception을 던짐
		//이걸로 다오를 대체할 수 있따!
		int result = jdbcTemplate.queryForObject("select 1", int.class); //쿼리문, 리턴객체
		
		assertEquals(1, result);
	}

	@Test
	public void delete() {
		int result = jdbcTemplate.update("delete from users where id=?", 3);
		
		assertEquals(1, result);
	}
	
	@Test
	public void queryForList() {
		//모든 테이블을 가져오기? -> 한 행마다를 리스트에 담고 한 행은 (칼럼-값)쌍으로 맵에 담음
		List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from users");
		assertEquals(2, list.size());
		assertEquals("안녕", list.get(0).get("name"));
	}
}
