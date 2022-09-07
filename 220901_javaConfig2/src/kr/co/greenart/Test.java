package kr.co.greenart;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

	public static void main(String[] args) {
		//ApplicationContext context = new ClassPathXmlApplicationContext("component-scan.xml");
		ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class); //이걸로 바꿔줌!
		
		MyService service = context.getBean(MyService.class);
		System.out.println(service.numberService());
	}
}
