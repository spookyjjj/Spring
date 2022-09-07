package kr.co.greenart.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private UserRepository repo; //인터페이스 달라하면 구현체를 찾아서 넣어준다
	
	public List<User> list() {
		logger.info("-- 유저목록 불러오기 --");
		return repo.list();
	}
	
	public int add(User user) {
		logger.info("-- 유저 추가하기 --");
		return repo.add(user);
	}
}
