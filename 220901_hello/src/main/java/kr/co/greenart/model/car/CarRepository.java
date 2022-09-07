package kr.co.greenart.model.car;

import java.util.List;

public interface CarRepository {
	//전체 행 목록 조회
	public List<Car> getAll();
	//아이디로 검색하여 하나의 행 조회
	public Car getById(int id);
	//하나의 행 추가
	public int add(Car car);
	//자동차 목록 추가하기
	public int[] batchInsert(List<Car> list);
	//하나의 행 수정
	public int update(Car car);
	//하나의 행 삭제
	public int delete(int id);
}
