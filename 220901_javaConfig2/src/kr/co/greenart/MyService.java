package kr.co.greenart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Component
@Service //똑같은 컴포넌트인데 걍 이름만 이렇게 티나게 적어놓는거임 -> 로직을 가지고있는 컴포넌트구나
public class MyService {
	@Autowired
	@Qualifier("myset") //구현체 2개중 어느애로 데리고 올거냐 id로 부르는것
	private MyDataRepository repo;
	
	public Iterable<Integer> numberService() {
		return repo.getMyNumbers();
	}
}
