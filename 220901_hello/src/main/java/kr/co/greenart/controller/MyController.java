package kr.co.greenart.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/my")
public class MyController {
	private static final Logger logger = LoggerFactory.getLogger(MyController.class);
	
	@GetMapping
	public String myView() {
		logger.info("사용자의 요청을 로깅합니다");
		return "myview";
	}
	
	@GetMapping("/{var}")
	public String myPathVar(@PathVariable String var) {
		logger.info("사용자의 요청을 로깅합니다");
		return "myview";
	}
	
	@GetMapping("/burn")
	public String burnAttr(HttpSession session) {
		logger.info("사용자의 요청을 로깅합니다");
		session.setAttribute("burn", "읽고 삭제하시오");
		return "myview";
	}
	
	//에러 핸들러 적용을 위해 nullpointexception발생시키려고 만든거~
	@GetMapping("/null")
	public String nullExcep() {
		throw new NullPointerException("임의로 발생시킨 널 예외");
	}
}
