package kr.co.greenart.model.file;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileRepository {
	public Resource getByName(String fileName); //내려받기 할 때는 리턴타입이 resource
	public List<String> getAllnames();
	public int save(MultipartFile file); //업로드 할 때는 파라미터가 MultipartFile
}
