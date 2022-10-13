package kr.co.green;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@GetMapping
	public List<Board> list() { //목록보기	
		return service.list();
	}
	
	@GetMapping("/{id}")
	public Board showArticle(@PathVariable int id) { //상세보기
		return service.showArticle(id);
	}
	
	@PostMapping
	public int add(@RequestBody Board board) { //추가하기
		System.out.println(board);
		return service.add(board);
	}
	
	@PatchMapping
	public String update() { //수정하기
		
		return "df";
	}
	
	@DeleteMapping
	public String delete() { //삭제하기
		
		return "df";
	}
}