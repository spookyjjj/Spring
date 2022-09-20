package kr.co.greenart;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//흐름~~
//"/print" get요청 -> text 입력가능 (view)form.jsp로 forward
//form.jsp submit시 "/print" post요청 -> 입력한 text그래도 볼 수 있는 (view)print.jsp로 forward

@Controller
@RequestMapping(value = "/print") //메소드들이 다 /print공통이면 위로 빼서 정의 가능!!
public class PrintController {
	
	@GetMapping
	//@RequestMapping(value = "/print", method = RequestMethod.GET)
	public String goForm() {
		return "form";
	}
	
	@PostMapping
	//@RequestMapping(value = "/print", method = RequestMethod.POST)
	public String goPrint(HttpServletRequest req, Model model) { //요청을 달라하면 DispatcherServlet에서 넘겨줌~
		//req.setCharacterEncoding("UTF-8"); //★이미 찐 req는 dispatcherServlet 한번 거쳐서 오는거라서 여기서는 안먹음 필터ㄱㄱ 
		String text = (String) req.getParameter("text"); 
		model.addAttribute("text", text);
		return "print";
	}
//	@PostMapping
//	//@RequestMapping(value = "/print", method = RequestMethod.POST)
//	public String goPrint(@RequestParam String text ,Model model) {
//		model.addAttribute("text", text);
//		return "print";
//	}
	
	@GetMapping("/sub") //클래스단위로 /print가 있으니깐 얘는 /print/sub이됨
	public @ResponseBody String sub() { //응답바디로 저 string을 보내겠다는 소리임 response에서 프린터 가져오고 이 과정이 한큐에~!
		return "<html><body><h1>you are here</h1></body></html>";
	}
}
