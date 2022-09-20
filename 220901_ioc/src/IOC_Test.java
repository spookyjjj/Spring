import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import mybeans.MyBean;
import mybeans.MyLogic;
import mybeans.MyStatefulObj;

public class IOC_Test {
	public static void main(String[] args) {
		//bean정보 담긴 xml읽어오기부터!
		ApplicationContext context = new ClassPathXmlApplicationContext("myconfig.xml");
		
		MyBean bean = context.getBean(MyBean.class);
		bean.hello();		
		//id설정한 것으로 불러오기
		//MyBean beab2 = (MyBean) context.getBean("first"); //다운캐스팅 해도되고
		MyBean bean2 = context.getBean("first", MyBean.class); //인자로 넘겨줘도 됨
		bean2.hello();				
		System.out.println(bean == bean2); //true
		//★똑같은 참조인데 연결하는 선만 여러개인것!
		
		MyLogic logic = context.getBean(MyLogic.class); 
		//<constructor-arg>태그에 ref걸어 놨어서 MyLogic logic = new MyLogic(new MyBean())가 한방에 됨!
		logic.someMethod();
		
		//bean에 클래스이름으로 불러올게 여러개가 걸리면, 아이디값까지 지정해줘야 원하는 애를 불러와줌!!!
		MyStatefulObj third = context.getBean("third", MyStatefulObj.class);
		System.out.println(third.toString()); //[my-name, 100] : bean에서 설정했음

		//설정값 변경시켜주고 다시 불러오면?
		third.setName("new-name"); 
		third.setNumber(200);

		MyStatefulObj onemoretime = context.getBean("third", MyStatefulObj.class);
		System.out.println(onemoretime.toString()); //[new-name, 200]
		System.out.println(third == onemoretime);
		//같은 bean -> 같은 인스턴스 참조니깐 바뀐값으로 된 그 애를 다시 불러오는거임 -> ★싱글턴패턴
		//cf) bean설정에서 scope="prototype"이면 달라고 할 때 마다 새롭게 만들어준다~
		
		
		//클래스 이름으로 여러개 걸린 경우, bean설정 중에 primary attribute가 있는애가 있음 걔를 부름~
		MyStatefulObj primary = context.getBean(MyStatefulObj.class);
		System.out.println(primary.toString());
		System.out.println(third == primary); //false
		//★인스턴스는 bean마다 별개로 관리 된다는것을 알 수 있음!!
		
		
		
	}
}
