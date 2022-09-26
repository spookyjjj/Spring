package kr.co.greenart.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class MyInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(MyInterceptor.class);
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("=== 1. 핸들러가 처리 전 ===");
		
		HttpSession session = request.getSession(false); //이미 생성된 세션이 없다면 null을 반환
		if (session != null) {
			session.removeAttribute("burn"); //세션값 지우고, 
		}
		request.setAttribute("burn", "요청객체에 새로운 어트리뷰트 생성"); //request에 값 담음!
		
		return true;
		//return을 false를 주면 요청흐름이 여기서 끊김 <- 로그인필터로 대체가능 
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("=== 3. 핸들러 처리 후, 뷰 생성 전 ===");
		
		String value = (String) request.getAttribute("burn");
		value += ". 핸들러 처리 후 어트리뷰트 변경";
		request.setAttribute("burn", value);
		//ModelAndView가 있기 때문에 여기서 모델을 보면서 뷰를 만든다! 즉, 후처리가 가능하다~
	}
	
	//핸들러 전,후가 main이고 뷰 생성 후 인 애가 추가되는 느낌~
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("=== 5. 뷰 생성 후 ===");
		
		HttpSession session =  request.getSession(false);
		session.removeAttribute("burn");
	}
	
}
