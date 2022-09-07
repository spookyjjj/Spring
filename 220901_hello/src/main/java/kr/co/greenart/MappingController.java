package kr.co.greenart;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mapping")
public class MappingController {
	
	@GetMapping(value = "/??.two", produces = "text/plain; charset=utf-8") //글자수 맞출때 형태
	public @ResponseBody String two(HttpServletResponse resp) {
		//resp.setContentType("text/pain; charset=utf-8");
		return "두글자 매핑";
	}
	
	@GetMapping(value = "/*.do", produces = "text/plain; charset=utf-8") //인코딩해서 resp에 담기
	public @ResponseBody String doURI() {
		return "do로 끝나는 매핑";
	}
	
//	//@GetMapping(value = "/ppp", params = "com") //com이라는 이름의 파라미터가 있어야 진행한다는 소리 (제약)
//	@GetMapping(value = "/ppp", params = "com=val") //com파라미터가 있고 값이 무조건 val이어야 함
//	public @ResponseBody String ppp() {
//		return "ppp";
//	}
	@GetMapping(value = "/ppp", params = "com") //com파라미터가 있고
	public @ResponseBody String ppp(@RequestParam(defaultValue = "default") String com) { //값이 없으면 디폴트값으로ㄱ
		return com;
	}
}
