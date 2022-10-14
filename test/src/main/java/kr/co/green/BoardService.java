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
			String reg_date = toDateFormat(rs.getTimestamp("reg_date"));
			String mod_date = toDateFormat(rs.getTimestamp("mod_date"));
			return new Board(id, title, content, reg_date, mod_date);
		}
	}
	
	private String toDateFormat(Timestamp timestamp) {
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
		return jdbcTemplate.update("insert into board (title, content, reg_date, mod_date) values (?, ?, ?, ?)", board.getTitle(), board.getContent(), board.getReg_date(), board.getMod_date());
		//return jdbcTemplate.update("insert into board (title, content, reg_date) values (?, ?, ?)", board.getTitle(), board.getContent(), board.getReg_date());
	}
	
	public int update(int id, Board board) {//제목만 바꿀수도, 내용만 바꿀수도있다! null null // null 값 // 값 null // 값 값
		if (board.getTitle() == null && board.getContent() == null) {
			return 0;
		} else if (board.getTitle() == null) {
			return jdbcTemplate.update("update board set content = ?, mod_date = ? where id = ?", board.getContent(), board.getMod_date(), id);
		} else if (board.getContent() == null) {
			return jdbcTemplate.update("update board set title = ?, mod_date = ? where id = ?", board.getTitle(), board.getMod_date(), id);
		} else {
			return jdbcTemplate.update("update board set title = ?, content = ?, mod_date = ? where id = ?", board.getTitle(), board.getContent(), board.getMod_date(), id);
		}
	}

	public int delete(int id) {
		return jdbcTemplate.update("delete from board where id = ?", id);
	}

}
