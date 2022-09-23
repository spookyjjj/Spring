package kr.co.greenart.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
	
	//8. bean으로 만든 UserValidator꺼내오기
	//Hibernate Validator Engine쓸거면 이게 필요가 없어짐!!
//	@Autowired 
//	private UserValidator validator;
	
	@Autowired
	private UserService service;
	
	//============get==================
	//기존 form형식 쓴 경우!
//	@GetMapping("/user")
//	public String userForm() {
//		return "userForm";
//	}
	
	//★user유효성검사를 추가 하고 싶다면! 숫자따라가면서 check
	//유효성검사에 집어넣을 user객체를 ModelAttribute안에 심어놓고 시작!
//	@GetMapping("/user")
//	public String userForm(Model model) {
//		model.addAttribute("userinfo", new User("기본값",1)); //1. 폼으로 보내기 전에 model에 user객체를 넣음 -> userForm에서 이어서..
//		return "userForm";
//	}
	//위의 방법을 @ModelAttribute써서도 가능 
	@ModelAttribute("userinfo") //여기 방문할 때 마다 이 과정은 일어남!! 항상 userinfo는 존재 -> 후에 내가 입력한 값으로 덮어씌우는거임~
	public User user() {
		System.out.println("모델초기화과정");
		return new User("이름을 입력하세요", 30);
	}
	@GetMapping("/user") 
	public String userForm(@ModelAttribute("userinfo") User user) { //가진 requestParam으로 User바인딩해서 모델에 넣기
		return "userForm";
	}
	
	//===========post================
	//기존 form형식 쓴 경우! input name을 key로, 입력값을 value로 해서 param 만들어 내보냄
//	@PostMapping("/user")
//	public String submit(@RequestParam String name, @RequestParam int age) {
//		User user = new User(name, age);
//		logger.info("입력정보: " + user.toString());
//		return "redirect:/";
//	}
	//★위의 과정을 spring은 알아서  바인딩 해줌~! -> User객체의 필드명이 input name이 일치하면 입력값을 set해줌 
//	@PostMapping("/user")
//	public String submit(User user) { //이렇게 form으로 입력받은 객체를 넣어주면 끝! 
//		logger.info("입력정보: " + user.toString());
//		return "redirect:/";
//	}
	
	//유효성검사한 case~
	//6. model안의 User객체를 덮어씌운채로 이리 옴 -> 유효성검사를 UserValidator로 할거임! -> UserValidator에서 이어서..
//	@PostMapping("/user")
//	public String submit(@ModelAttribute("userinfo") User user, BindingResult errors) {
//		logger.info("입력정보: " + user.toString());
//		validator.validate(user, errors); //9. 만든 UserValidator 사용~ errors객체는 달라고 하면 준다!!
//		if (errors.hasErrors()) { 
//			return "userForm"; //에러 있으면 다시 form으로 ㄱㄱ
//		}
//		return "redirect:/"; //에러 없으면 다음 단계로 ㄱㄱ
//	}
	//위의 방법처럼 Validate하는 클래스 수동으로 안만들고  Hibernate Validator Engine을 써서 어노테이션으로 쉽게 유효성 검사도 가능!
	//hibernate-validator라이브러리 추가하고 -> 유효성 검사할 User클래스에 어노테이션 붙이고 -> 여기서 @Valid먹이면 끝!
	@PostMapping("/user")
	public String submit(@ModelAttribute("userinfo") @Valid User user, BindingResult errors) { //가진 requestParam으로 User바인딩해서 모델에 넣기
		logger.info("입력정보: " + user.toString());
		if (errors.hasErrors()) {
			return "userForm";
		}
		service.add(user); //에러없는 정보값 받았으면 추가하기 by service
		
		return "redirect:/user/list";
	}
	
	//리스트보기~! by service
	@GetMapping("/user/list")
	public String list(Model model) {
		model.addAttribute("list", service.list());
		return "userlist";
	}
	
}
