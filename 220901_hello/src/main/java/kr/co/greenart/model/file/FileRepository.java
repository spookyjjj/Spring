package kr.co.greenart.model.file;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileRepository {
	public Resource getByName(String fileName);
	public List<String> getAllnames();
	public int save(MultipartFile file);
}
