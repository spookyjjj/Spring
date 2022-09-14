package kr.co.greenart.model.file;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
@Primary
public class FileDBRepository implements FileRepository {
	@Autowired
	private JdbcTemplate jdbc;
	
	@Override
	public Resource getByName(String fileName) {
		byte[] blob = jdbc.queryForObject("select file from files where name=?",
				byte[].class,
				fileName);
		return new ByteArrayResource(blob);
	}

	@Override
	public List<String> getAllnames() {
		return jdbc.queryForList("select name from files", String.class);
	}

	@Override
	public int save(MultipartFile file) {
		try {
			return jdbc.update("insert into files (name, file) values (?, ?)",
					file.getOriginalFilename(),
					file.getBytes());
		} catch (DataAccessException | IOException e) {
			e.printStackTrace();
			return 0;
		}
	}

}
