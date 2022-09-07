package kr.co.greenart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//★spring bean Configuration file (xml파일) 날리고 이걸로 대체할거임!!!
//->내가 만든 클래스가 아닌경우에는 annotation을 붙일 수 없으므로 이런방식을 사용함
//그 예로, List와 Set으로 실습해볼거임~~
//ClassPathXmlApplicationContext("component-scan.xml") -> AnnotationConfigApplicationContext(MyConfig.class);

@Configuration //★★설정을 가진 component다~~
@ComponentScan("kr.co.greenart")
public class MyConfig {
	//메소드의 형태로 bean을 등록하여야 한다~!
	@Bean
	public List<Integer> myList() { //myList라는 이름의 bean이 생기는데 List<Integer>타입을 찾아서 return을 반환
		return new ArrayList<>(Arrays.asList(1,2,3,4,5));
	}
	
	@Bean
	public Set<Integer> mySet() { //mySet라는 이름의 bean이 생기는데 Set<Integer>타입을 찾아서 return을 반환
		return new HashSet<>(Arrays.asList(6,7,8,9,10));
	}

}
