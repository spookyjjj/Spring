package kr.co.greenart.model;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator{ //7. 실질적으로 유효성 검사하는 클래스 -> 만들었으니, userController에다 넣기!

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz); //유저 타입만 검사하겠다고 체크해놓는 과정
	}

	@Override
	public void validate(Object target, Errors errors) { //여기서 유효성 검사 시작!
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty", "오 이름을 입력하세요"); 
		//대상객체`target(User)`의 필드이름`name`이 Empty이거나 Whitespace이면 `errors`에다가 자유롭게 `에러코드`, `에러메시지` 부여
		User user = (User) target;
		if (user.getName().length() > 5) {
			errors.rejectValue("name", "name.toolong", "오 이름이 너무 깁니다");
		}
		if (user.getAge() > 200) {
			errors.rejectValue("age", "age.tooold", "오 나이가 너무 많아요");
		}
		if (user.getAge() > 300) {
			errors.rejectValue("age", "age.toootooold", "오 나이가 너무너무 많아요");
		}
		if (user.getAge() <= 0) {
			errors.rejectValue("age", "age.notborn", "오 나이가 너무 적어요");
		}
	}

}
