import mybeans.MyBean;
import mybeans.MyLogic;

public class Main {
	public static void main(String[] args) {
		MyBean obj = new MyBean();
		obj.hello();
		
		MyLogic logic = new MyLogic(new MyBean());
		logic.someMethod(); //mylogic이 mybean을 의존하고있음!!
	}
}
