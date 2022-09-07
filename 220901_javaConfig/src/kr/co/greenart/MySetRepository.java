package kr.co.greenart;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//@Component("myset")
@Repository("myset") //똑같은 컴포넌트인데 걍 이름만 이렇게 티나게 적어놓는거임 -> 자료에 접근하는 컴포넌트구나
@Primary
public class MySetRepository implements MyDataRepository{
	@Override
	public Iterable<Integer> getMyNumbers() { 
		Set<Integer> set = new HashSet<>();
		set.add(6);
		set.add(7);
		set.add(8);
		set.add(9);
		set.add(10);
		return set; //list는 Iterater적용가능하니깐 Iterable
	}
}
