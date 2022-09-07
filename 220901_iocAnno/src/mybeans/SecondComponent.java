package mybeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SecondComponent {
	//걍 이 필드는 어떤 방식으로든 의존성 처리해라~
	@Autowired
	private FirstComponent need;
	
	public void myServiceMethod() {
		System.out.println("의존성이 필요함");
		need.hello();
	}
	
	//생성자를 통해서 의존성 처리해라~
	//@Autowired
	public SecondComponent(FirstComponent need) {
		super();
		this.need = need;
	}
	//setter를 통해서 의존성 처리해라~
	//@Autowired
	public void setNeed(FirstComponent need) {
		this.need = need;
	}
	
	
}
