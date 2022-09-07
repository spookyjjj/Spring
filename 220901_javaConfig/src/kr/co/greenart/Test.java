package kr.co.greenart;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("component-scan.xml");
//		MyDataRepository repo = context.getBean(MyDataRepository.class); //나는 인터페이스 달라고했는데
//		System.out.println(repo.getMyNumbers()); //구현체를 찾아서 메소드 실행시킴!!!!!
		
		//그렇다면 인터페이스에는 구현체가 여러개 일 수 있는데 어케 대처하느냐?!
		//1. @Primary붙은놈꺼 쓰기 2. id로 가져오기
//		MyDataRepository repo = context.getBean("mylist", MyDataRepository.class); //나는 인터페이스 달라고했는데
//		System.out.println(repo.getMyNumbers());
		
		MyService service = context.getBean(MyService.class);
		System.out.println(service.numberService());
	}
}
