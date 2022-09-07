package kr.co.greenart;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//@Component("mylist")
@Repository("mylist") //똑같은 컴포넌트인데 걍 이름만 이렇게 티나게 적어놓는거임 -> 자료에 접근하는 컴포넌트구나
public class MyListRepository implements MyDataRepository{
	@Autowired
	private List<Integer> list;
	
	@Override
	public Iterable<Integer> getMyNumbers() { 
		return list; //list는 Iterater적용가능하니깐 Iterable
	}
}
