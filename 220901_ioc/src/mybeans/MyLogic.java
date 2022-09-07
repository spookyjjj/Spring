package mybeans;

public class MyLogic {
	private MyBean mybean;
	
	public void someMethod() {
		System.out.println("다음 작업을 수행하기 위해 객체 의존성이 필요합니다.");
		mybean.hello();
	}
	
	//의존성 처리를 위해 생성자를 만들거나 세터를 설정
	public MyLogic(MyBean mybean) {
		this.mybean = mybean;
	}

//	public void setMybean(MyBean mybean) {
//		this.mybean = mybean;
//	}
	
}
