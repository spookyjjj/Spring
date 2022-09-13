package kr.co.greenart.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //역시 컴포넌트중 하나임. + 모든 컨트롤러 전역에서 적용됨
//특정 컨트롤러에만 영향을 주고 싶다면 ()안에 설정
//1.해당 어노테이션이 붙어있으면 동작(annotations) 2.클래스자체를 넘겨줌(assignableTypes) 3.패키지를 넘겨줌(basePackages)
public class ErrorHandler {
	@ExceptionHandler(value = NullPointerException.class)
	public String nullExcep(Model model, NullPointerException ex) {
		model.addAttribute("error", "서버에서 오류가 발생했습니다. 죄송ㅋㅋㅈㅅ. " + ex.getMessage());
		return "errorpage";
	}
}
