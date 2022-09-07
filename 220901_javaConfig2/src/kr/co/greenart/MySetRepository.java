package kr.co.greenart;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//@Component("myset")
@Repository("myset") //똑같은 컴포넌트인데 걍 이름만 이렇게 티나게 적어놓는거임 -> 자료에 접근하는 컴포넌트구나
@Primary
public class MySetRepository implements MyDataRepository{
	@Autowired
	private Set<Integer> set;
	
	@Override
	public Iterable<Integer> getMyNumbers() { 
		return set; //list는 Iterater적용가능하니깐 Iterable
	}
}
