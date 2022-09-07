package kr.co.greenart;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import kr.co.greenart.model.car.Car;
import kr.co.greenart.model.car.CarRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { kr.co.greenart.config.RootConfig.class })
public class CarRepoTest {
	@Autowired
	private CarRepository repo;
	
	//이것부터 되는지 확인하기
	@Test
	public void initTest() {
		assertNotNull(repo);
	}
	
	@BeforeClass
	public static void addTestData() {
		//테스트 전에 환경 만들고 싶을때 이걸 씀 static하게 선언 필요
//		Random r = new Random();
//		repo.add(new Car(0, "테스트자료" + r.nextInt(10000), r.nextInt(10000)));
//		repo.add(new Car(0, "테스트자료" + r.nextInt(10000), r.nextInt(10000)));
//		repo.add(new Car(0, "테스트자료" + r.nextInt(10000), r.nextInt(10000)));
	} 
	
	@Test
	public void create() {
		Random r = new Random();
		Car car = new Car();
		car.setModel("새 모델" + r.nextInt(10000));
		car.setPrice(1000);
		
		int result = repo.add(car);
		assertEquals(1, result);
	}
	
	@Test
	public void read() {
		List<Car> list = repo.getAll();
		
		assertNotNull(list);
	}
	
	@Test
	public void update() {
		int result = repo.update(new Car(1, "변경", 300));
		
		assertEquals(1, result);
	}
}
