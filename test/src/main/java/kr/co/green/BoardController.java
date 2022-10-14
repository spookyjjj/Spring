package kr.co.green;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
		//★위의 Board 객체는 빈생성자로 만든다음  @RequestBody있는 내용으로 setting해주는 흐름이다~
		//따라서 빈생성자에 현재시간 넣어놓던지, 여기서 현재시간으로 따로 setting해주던지!
//		board.setReg_date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//		System.out.println(board);
		return service.add(board);
	}
	
	@PatchMapping("/{id}")
	public int update(@PathVariable int id, @RequestBody Board board) { //수정하기
//		System.out.println(board);
		return service.update(id, board);
	}
	
	@DeleteMapping("/{id}")
	public int delete(@PathVariable int id) { //삭제하기
		return service.delete(id);
	}
}