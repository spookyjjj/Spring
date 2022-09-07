package kr.co.greenart.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class UserRepositoryMySQL implements UserRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private UserRowMapper mapper = new UserRowMapper();
	
	//내부에서 간략하게 클래스 만듬! <- 안에서만 쓸 클래스라서~ 그래서 굳이 bean으로 등록 안함!
	//만약에 아예 1번만 쓰이고 말 것이라면 이벤트 리스너처럼 익명클래서 만들기도 가능~
	private class UserRowMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		//한 줄에 관해서만 잘 작성을 해놓으면 ok
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int age = rs.getInt("age");
			return new User(id, name, age);
		}
	}

	@Override
	public int add(User user) {
		return jdbcTemplate.update("insert into users (name, age) values (?, ?)",
				user.getName(),
				user.getAge());
	}

	@Override
	public List<User> list() {
		return jdbcTemplate.query("select * from users", mapper); //select문을 위해서는 rowMapper가 필요!
	}
	
}
