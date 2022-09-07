package kr.co.greenart;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RequestHandling {
	private final static Logger logger = LoggerFactory.getLogger(RequestHandling.class);
	
//	@RequestMapping(value = "/req", method = RequestMethod.GET)
//	public String req(HttpServletRequest request) { //요청을 달라하면 DispatcherServlet에서 넘겨줌~
//		String param = request.getParameter("param");
//		logger.info(param);
//		return "뷰이름";
//	}
	
	@RequestMapping(value = "/req", method = RequestMethod.GET)
	public String req(@RequestParam int param, @RequestParam int param2, Model model) { //요청을 달라하면 DispatcherServlet에서 넘겨줌~
		logger.info(String.valueOf(param + param2));
		//이전까지는 request객체에 심어서 전해줄 값을 전달했었음..
		//spring에서는 model을 사용하자 -> 범위를 내가 지정해서 입맛대로 조정가능함!
		model.addAttribute("result", param + param2);
		return "plusresult";
	}
}
