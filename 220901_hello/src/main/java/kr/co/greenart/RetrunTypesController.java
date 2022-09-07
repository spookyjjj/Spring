package kr.co.greenart;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/returns")
public class RetrunTypesController {
	
	@GetMapping("/viewname")
	public String viewname() {
		return "hello";
	}
	
	@GetMapping("/respbody")
	public @ResponseBody String body() {
		return "body 내용";
	}
	
	@GetMapping("/mv")
	//public ModelAndView mv(ModelAndView mv) { //파라미터로 받으면 걍 mv라는 비어있는 ModelAndView 객체 줌
	public ModelAndView mv() { //ModelAndView는 모델과 뷰를 같이 담을 수 있는 객체! 넘 좋음
		ModelAndView mv = new ModelAndView();
		mv.addObject("result","모델앤뷰 객체로 설정"); //모델값으로 넣겠다는 소리
		mv.setViewName("plusresult"); //보낼 page이름
		return mv;
	}
	
	@GetMapping("/respentity")
	//public ResponseEntity<String> entity(ResponseEntity<String> en) { //얘도 마찬가지로 이렇게 가능
	public ResponseEntity<String> entity() {
//		String body = "바디 내용입니다";
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Content-Type", "text/plain; charset=utf-8");
//		ResponseEntity<String> en = new ResponseEntity<>(body, headers, HttpStatus.OK);
//		return en;
		//똑같은 내용을 이렇게도 가능하다~~!!
		//return ResponseEntity.ok().header("Content-Type", "text/plain; charset=utf-8").body("바디 내용입니다");
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, "text/plain; charset=utf-8")
				.body("바디 내용입니다");
	}
	
	@GetMapping("/redirect")
	public String redirect() {
		return "redirect:/"; //찐으로 진짜 걍 redirect:하고 경로적으면 리다이렉트
	}
}
