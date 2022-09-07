package kr.co.greenart.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.greenart.model.User;
import kr.co.greenart.model.UserService;
import kr.co.greenart.model.UserValidator;

@Controller
public class UserController {
	private final static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	//Hibernate Validator Engine쓸거면 이게 필요가 없어짐!!
//	@Autowired //9. bean으로 만든 UserValidator꺼내오기
//	private UserValidator validator;
	
	@Autowired
	private UserService service;
	
//	@GetMapping("/user")
//	public String userForm() {
//		return "userForm";
//	}
	//★user유효성검사를 위한 흐름~~~
//	@GetMapping("/user")
//	public String userForm(Model model) {
//		model.addAttribute("user", new User("기본값",1)); //1. 폼으로 보내기 전에 model에 user객체를 보냄 -> userForm에서 이어서..
//		return "userForm";
//	}
	@ModelAttribute("user") //해당 컨트롤러 안에서(UserController) requestMapping할때 쓸 model 설정가능
	public User user() {
		return new User("기본값", 1);
	}
	@GetMapping("/user") //@ModelAttribute와 한 세트~!
	public String userForm(@ModelAttribute("user") User user) {
		return "userForm";
	}
	
	
//	@PostMapping("/user")
//	public String submit(@RequestParam String name, @RequestParam int age) {
//		User user = new User(name, age);
//		logger.info("입력정보: " + user.toString());
//		return "redirect:/";
//	}
//	@PostMapping("/user")
//	public String submit(User user) { //★입력값을 바로 바인딩 해줌~! 너무너무너무 핀한 기능 
//		logger.info("입력정보: " + user.toString());
//		return "redirect:/";
//	}
//	//UserValidator라는 클래스를 만들어서 유효성 검사를 했다면!!
//	@PostMapping("/user")
//	public String submit(@ModelAttribute("user") User user, BindingResult errors) { //7. 계속 model안의 User객체를 전달하고 전달받고 -> validator클래스생성
//		logger.info("입력정보: " + user.toString());
//		validator.validate(user, errors); //8. errors객체는 달라고 하면 준다!! BindingResult. 에러있으면 입력폼으로ㄱ없으면 진행ㄱ
//		if (errors.hasErrors()) {
//			return "userForm";
//		}
//		return "redirect:/";
//	}
	//만약 Hibernate Validator Engine을 써서 user클래스에 어노테이션으로 유효성 검사를 했다면, 이렇게만 해도됨
	@PostMapping("/user")
	public String submit(@ModelAttribute("user") @Valid User user, BindingResult errors) { 
		logger.info("입력정보: " + user.toString());
		if (errors.hasErrors()) {
			return "userForm";
		}
		service.add(user); //에러없는 정보값 받았으면 추가하기
		
		return "redirect:/user/list";
	}
	
	//리스트보기~! by service
	@GetMapping("/user/list")
	public String list(Model model) {
		model.addAttribute("list", service.list());
		return "userlist";
	}
	
}
