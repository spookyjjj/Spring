package kr.co.greenart.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
public class TestModelSession {
	//@SessionAttributes("modelname")는 다른 클래스로 넘어가면 값을 불러오지 못함
	@GetMapping("/testModel")
	public @ResponseBody String testModel(Model model) {
		return new HashMap<>(model.asMap()).toString();
	}
}
