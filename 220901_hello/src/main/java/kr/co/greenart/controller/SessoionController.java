package kr.co.greenart.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping(value="/session", produces="text/plain; charset=utf-8")
@ResponseBody //이걸 쓸 때는 @RequestMapping에 produces="charset=utf-8"를 해줘야 한글이 안 깨짐 or json으로 보내던가
//@SessionAttributes("modelname")
public class SessoionController {
	@GetMapping("/add")
	public String addSessionValue(HttpSession session) {
		session.setAttribute("myname", "myvalue");
		return "세션에 값 설정";
	}
	
	@GetMapping("/check")
	public String getSessionValue(HttpSession session) {
		return (String) session.getAttribute("myname");
	}
	
	@GetMapping("/model")
	public String addModelValue(HttpSession session, Model model) {
		model.addAttribute("modelname","modelvalue");
		return "모델에 값 설정";
		//@SessionAttributes("modelname")로 인해서 session범위에 담기긴 할건데
		//★HttpSession session을 꼭 해줘야함..그래야 session자체가 생성됨!!
	}
	
	@GetMapping("/modelcheck")
	public String getModelValue(Model model) {
		return (String) model.getAttribute("modelname"); //안나오다가 @SessionAttributes 후에는 나옴
	}
	
	//@SessionAttributes는 이렇게 같은 클래스 안에서는 불러와지는데, 다른 클래스에서 부르면 또 안나옴
	@GetMapping("/testModelinclass")
	public @ResponseBody String testModel(Model model) {
		return new HashMap<>(model.asMap()).toString();
	}
	
	@GetMapping("/status")
	public String complete(SessionStatus status) {
		status.setComplete(); //★이걸로 사라지는건 model(session)임! 찐 session은 validate를 써야함
		return "세션 어트리뷰트 삭제되었음";
	}
	
}
