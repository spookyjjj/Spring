package kr.co.greenart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {
	//"/hello"라는 주소를 get방식으로 요청하면 아래의 메소드가 실행된다~!
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() {
		return "hello";
	}
	
	//"/hi"라는 주소를 get/post방식으로 요청하면 아래의 메소드가 실행된다~!
	@RequestMapping(value = "/hi", method = { RequestMethod.GET, RequestMethod.POST })
	public String hi() {
		return "hello";
	}
	
	//->★★즉, 관련있는 주소들은 하나의 controller에서 메소드로 관리가 가능하게됨!!
}
