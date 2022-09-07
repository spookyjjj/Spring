package kr.co.greenart.model.car;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarService {
	@Autowired
	private CarRepository repo;
	
	//자동차의 목록을 전달받아 추가
	@Transactional //트렌젝션을 거는것도 어노테이션으로 가능! with @EnableTransactionManagement
	public int[] bulkInsert(List<Car> list) {
		//1. 하나 넣는 기능으로 포문돌려서 해결하기
//		int[] result = new int[list.size()];
//		for (int i = 0; i < list.size(); i++) {
//			result[i] = repo.add(list.get(i));
//		}
		//2. 배치기능을 이용해서 한방에 해보기
		int[] result = repo.batchInsert(list);
		return result;
	}
	
	@Transactional
	public int delete(int id) {
		return repo.delete(id);
	}

}
