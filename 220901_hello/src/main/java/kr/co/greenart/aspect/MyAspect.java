package kr.co.greenart.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {
	private static final Logger logger = LoggerFactory.getLogger(MyAspect.class);
	
//	//관점의 기준이 될 메소드를 넣어주면 됨.. public void(접근제한자, 리턴타입)같은건 걍 *로 처리! (패키지, 클래스, 메소드(파라미터))
//	@Before("execution(* kr.co.greenart.model.file.FileDBRepository.getAllnames(..))")
//	public void printBefore() {
//		logger.info("-- 파일목록을 불러 오기 전에 실행됩니다 --");
//	}
//	//관점의 기준이 될 메소드를 넣어주면 됨
//	@After("execution(* kr.co.greenart.model.file.FileDBRepository.getAllnames(..))")
//	public void printAter() {
//		logger.info("-- 파일목록을 불러 온 후에 실행됩니다 --");
//	}
	
	//둘의 포인트컷이 같으면 하나로 묶어주기 가능~ 걍 선언만 해주고 써먹으면 됨~~
	@Pointcut("execution(* kr.co.greenart.model.file.FileDBRepository.getAllnames(..))")
	public void print() {}
	
	@Before("print()")
	public void printBefore() {
		logger.info("-- 파일목록을 불러 오기 전에 실행됩니다 --");
	}
	
	@After("print()")
	public void printAfter() {
		logger.info("-- 파일목록을 불러 온 후에 실행됩니다 --");
	}
	
	//★within은 특정 타입에 대해서만 작동 -> 이 경우엔 @Repository실행 자체에 등록해 놓은거!!
	@Pointcut("within(@org.springframework.stereotype.Repository *)")
	public void repository() {}
	
	@Around("repository()") //어라운드는 메소드 전후로 전부~ -> 메소드 안에서 jp.proceed()로 전후 구분하면 됨
	public Object loggingTime(ProceedingJoinPoint jp) throws Throwable {
		long start = System.nanoTime();
		logger.info("시작시간: " + start); //jp.proceed() 전!!
		Object proceed =  jp.proceed(); //애가 실제 실행되는 애
		long end = System.nanoTime(); //jp.proceed() 후!!
		logger.info("종료시간: " + end);
		
		logger.info(jp.getSignature() + "메소드의 실행시간: " + (end - start));
		return proceed;
	}
}
