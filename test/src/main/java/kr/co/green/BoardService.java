package kr.co.green;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BoardService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private BoardRowMapper mapper = new BoardRowMapper();
	
	private class BoardRowMapper implements RowMapper<Board> {
		@Override
		public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
			int id = rs.getInt("id");
			String title = rs.getString("title");
			String content = rs.getString("content");
			String reg_date = toDate(rs.getTimestamp("reg_date"));
			return new Board(id, title, content, reg_date);
		}
	}
	
	private String toDate(Timestamp timestamp) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = formatter.format(timestamp.getTime());
		
		return format;
	}
	
	public List<Board> list() {
		return jdbcTemplate.query("select * from board", mapper); //select문을 위해서는 rowMapper가 필요!
	}
	
	public Board showArticle(int id) {
		return jdbcTemplate.queryForObject("select * from board where id = ?", mapper, id);
	}
	
	public int add(Board board) {
		return jdbcTemplate.update("update board (title, content, reg_date) values (?, ?, ?)", board.getTitle(), board.getContent(), Timestamp.from(Instant.now()));
	}
}
