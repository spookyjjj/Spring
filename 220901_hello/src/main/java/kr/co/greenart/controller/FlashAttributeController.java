package kr.co.greenart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/flash")
public class FlashAttributeController {
	@GetMapping("/1")
	public String forward(Model model) {
		model.addAttribute("forwardtest","포워드 테스트");
		return "flashview";
	}
	
	@GetMapping("/2")
	public String view() {
		return "flashview";
	}
	
	@GetMapping("/3")
	public String redirect(Model model) {
		model.addAttribute("redirecttest", "리다이렉트 테스트");
		return "redirect:/flash/2"; 
		//2번으로 이동시켜주는데, model에 담아놓고 리다이렉트 쓰는게 이상하다고 spring이 판단해서
		//자동으로 주소에 'flash/2?redirecttest=리다이렉트+테스트'를 붙여줌
		//-> 따라서 리다이렉트했다 그래도 model에 담은거면 param으로 꺼내올 수 있다~
		//이걸 활용할 수도 있겠지만, 일반적으로 내가 심은 값이 주소에 다 보이니깐 안좋음
	}
}
