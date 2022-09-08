package kr.co.greenart.controller.car;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.greenart.model.car.Car;
import kr.co.greenart.model.car.CarService;

@Controller
@RequestMapping("/car")
//@ResponsBody //여기에 어노테이션 붙이면 해당 클래스의 모든 내용이 응답바디로 날라간다!!
public class CarController {
	@Autowired
	private CarService service;
	
	//이게 기본 꼬라지 -> view이름을 string으로 리턴하여 거기로 forward시킴
//	@GetMapping
//	public String view() {
//		return "view name";
//	}
	
	//forward말고 일반 텍스트를 응답 바디에 넣어 보내고 싶으면?
//	1. public @ResponseBody String view() {}
//	2. public ResponseEntity<String> view() {}
	
	//그럼 car목록을 불러와서 응답바디에 담아 보내고 싶다~ 실습
	//Get이든 Post든 @ResponseBody에 한글을 담으면 깨짐! mapping을통해 CarController오는 과정에서 인코딩 유지X
	//-> @RequestMapping에 produces="컨텐트타입; charset=utf-8"를 해줘야 한글이 안 깨짐 or json으로 보내던가
//	@GetMapping
//	public @ResponseBody String viewAll() {
//		return service.list().toString();
//	}
	
	//응답바디에 그냥 텍스트 말고 json포멧으로 보내주고 싶다면? -> 잭슨 라이브러리 추가
	@GetMapping
	public @ResponseBody List<Car> viewAll() {
		//ObjectMapper mapper = new ObjectMapper();
		//mapper.writeValueAsString(value);
		//원래 이렇게 넣었어야 했는데 Spring에서는 자동으로 jackson이랑 연동이 되서 걍 이렇게만 넣으면 됨
		return service.list();
	}
	
	@GetMapping("/{id}")
	public @ResponseBody Car carById(@PathVariable int id) {
		return service.getById(id);
	}
	//없는 id입력하면 -> EmptyResultDataAccessException발생. try catch처리하면 깔끔~
	
	@PostMapping //post는 test가 힘드니 postman사용ㄱㄱ
	public ResponseEntity<String> add(@RequestBody Car car) {
		service.add(car);
		return ResponseEntity.ok("{ \"result\" : \"ok\" }");
	}
	
	@PutMapping //get, post 말고도 put등등 많다~! 즉, 매핑방법으로 애들을 구분해줄 수 있다!
	public ResponseEntity<String> update(@RequestBody Car car) {
		service.update(car);
		return ResponseEntity.ok("{ \"result\" : \"ok\" }");
	}
	
	@DeleteMapping("/{id}") //get, post 말고도 delete등등 많다~! 즉, 매핑방법으로 애들을 구분해줄 수 있다!
	public ResponseEntity<String> delete(@PathVariable int id) {
		service.delete(id);
		return ResponseEntity.ok("{ \"result\" : \"ok\" }");
	}
}
