package mybeans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope(value="prototype")
@Scope(scopeName=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Primary
public class ThirdComponent {
	private String word;
	private int num;
	
	public ThirdComponent(@Value("vvv") String word, @Value("100") int num) {
		this.word = word;
		this.num = num;
	}
	public void printValue() {
		System.out.println(word);
		System.out.println(num);
	} 
}
