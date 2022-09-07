package mybeans;

import org.springframework.stereotype.Component;

//@Component(value="firstComp")
@Component("firstComp")
public class FirstComponent {
	public void hello() {
		System.out.println("어노테이션으로 bean 설정하기 연습");
	}
}
