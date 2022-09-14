package kr.co.greenart.model.file;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;


@Repository
public class FileSystemRepository implements FileRepository {
	private final File saveFolder = new File("d:\\Temp\\");
	private final Path root = Paths.get("d:\\temp\\");
	

	@Override
	public Resource getByName(String fileName) {
		try {
			//UrlResource url의 형태로 리소스를 나타냄
			return new UrlResource(new File(
							saveFolder.getAbsolutePath()
							+ File.separatorChar
							+ fileName).toURI());
		} catch (MalformedURLException e) {
			return null;
		}
	}

	@Override
	public List<String> getAllnames() {
//		File[] filearr = saveFolder.listFiles(); 
		//listFiles : one for each file or directory in the directory.
		//즉, 폴더 안의 폴더도 반환을 한다. 파일만 보고 싶다면 걸러내는 작업이 필요
		
		//1. 이렇게 순환해서 걸러내던가
//		List<String> list = new ArrayList<>();
//		for (File f : filearr) {
//			if (!f.isDirectory()) {
//				list.add(f.getName());
//			}
//		}
		
		//2. 이렇게 애초에 필터걸어서 데려오던가
		File[] filearr = saveFolder.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return !pathname.isDirectory();
			}
		});
		List<String> list = new ArrayList<>();
		for (File f : filearr) {
			list.add(f.getName());
		}
		
		return list;
		
		//3. 또는 walk메소드 써보기
		//walk메소드: (경로, 어느단계까지 가져올지 옵션값) -> Stream<Path>를 반환
		//자바 8버전에서 잘 쓰이는 Stream! filter메소드가 있어서 함수(람다식)를 건네주면 참인 얘만 골라줌
		//필터는 자기 자신을 반환하는데 조건을 거쳐서~
//		try {
//			return Files.walk(root, 1)
//				.filter(x -> !x.equals(this.root))
//				.filter(x -> !Files.isDirectory(x))
//				.map(y -> y.toString())
//				.collect(Collectors.toList());
//		} catch (IOException e) {
//			e.printStackTrace();
//		} 
		
	}

	@Override
	public int save(MultipartFile file) {
		if (!saveFolder.exists()) {
			saveFolder.mkdir();
		}
		try {
			file.transferTo(new File(saveFolder.getAbsolutePath() +
					File.separatorChar + 
					file.getOriginalFilename()));
			return 1;
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			return 0;
		} 
		
		//이 방법도 사용가능~
//		Path saveFolder = Paths.get("d:\\");
//		try {
//			if (!Files.exists(saveFolder)) {
//				Files.createDirectories(saveFolder);
//			}
//			file.transferTo(saveFolder.resolve(Paths.get(file.getOriginalFilename())).normalize());
//			return 1;
//		} catch (IllegalStateException | IOException e) {
//			e.printStackTrace();
//			return 0;
//		}
		
	}
	
}
