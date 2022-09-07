package kr.co.greenart;

import static org.junit.Assert.*;

import org.junit.Test;

import kr.co.greenart.model.User;

//테스트하고자하는 메소드를 작성하면 됨! -> @test annotation만 붙이면 됨
//메소드들 끼리는 아무 영향 없이 독립적으로 test
public class MyFirstTest {
	
	@Test
	public void test() {
//		//junit.Assert에 포함된 무조건 실패시키는 메소드임
//		fail("Not yet implemented");
		
		int a = 10;
		int b = 20;
		int sum = a + b;
		assertEquals(30, sum); //기대값, 설정값
	}

	@Test
	public void test2() {
		String abc = "abc";
		String abc2 = abc;
		
		assertSame(abc2, abc); //잠조가 동일하면 pass
	}
	
	@Test
	public void test3() {
		User u = new User();
		
		assertNotNull(u); //not null이면 pass, null이면 fail
	}
}
