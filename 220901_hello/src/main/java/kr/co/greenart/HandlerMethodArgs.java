package kr.co.greenart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/handle")
public class HandlerMethodArgs {
	
	@GetMapping("/cookie")
	public @ResponseBody String args(@CookieValue(name = "JSESSIONID") String jessionid) {
		return jessionid;
	}
	
	@GetMapping("/header")
	public @ResponseBody String head(@RequestHeader("Accept") String accept) { //리퀘스트 헤더 항목 중 하나
		return accept;
	}
	
	@GetMapping("/{path1}/{path2}") //주소 자체를 하나의 변수처럼 사용하는 방법!!
	public @ResponseBody String paths(@PathVariable String path1, @PathVariable String path2) {
		return path1 + path2;
	}
}
