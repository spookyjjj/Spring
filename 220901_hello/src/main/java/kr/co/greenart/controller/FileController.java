package kr.co.greenart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.greenart.model.file.FileRepository;

@Controller
@RequestMapping("/file")
public class FileController {
	@Autowired
	private FileRepository repo;
	
	@GetMapping
	public String fileForm() {
		return "fileForm";
	}
	
	@PostMapping
	public String submit(@RequestParam MultipartFile upload) {
		String filename = upload.getOriginalFilename();
		int result = repo.save(upload);
		return "redirect:file/result";
	}
	
	@GetMapping("/result")
	public String view() {
		return "fileView";
	}
}
