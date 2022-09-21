package kr.co.greenart.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:/kr/co/greenart/config/mysql.properties")
//@ComponentScan("kr.co.greenart.model.car") //test때 ComponentScan으로 불러오려고~
@EnableTransactionManagement //트렌젝션관리를 시작하겠다~! -> 등록할 관리자 스펙은 수기작성 후 bean등록 필요
@EnableAspectJAutoProxy //aspect관리를 시작하겠다~! -> 최상위 패키지에 있는 클래스에 annotation을 적용해서 AOP를 찾을 수 있게 도와줌
public class RootConfig {
//★root-context.xml을 대신해서 만든 클래스 -> root-context.xml은 삭제해도 됨
	
	@Value("${jdbc.drivername}")
	private String drivername;
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(drivername);
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		
		return ds;
	}
	
	@Bean
	@Autowired
	public JdbcTemplate jdbcTemplate(DataSource ds) { 
	//파라미터 부분에 필요한거 적어놓고(여기서는 DataSource) autowired하면 알아서 가져와줌!!
	//즉, 필드, 생성자, setter, 메소드 위에 적으면 알아서 가져온다 (setter도 결국은 메소드였음!)
		return new JdbcTemplate(ds);
	}
	
	@Bean
	public PlatformTransactionManager txManager(DataSource ds) {
	//트렌젝션 관리할 매니저임~
		return new DataSourceTransactionManager(ds);
	}
	
	@Bean
	@Autowired
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource ds) {
	//rowMapper부분 간략화 하기위해 사용 -> 실제쓰는건 CarRepositoryNamed에서
		return new NamedParameterJdbcTemplate(ds);
	}
}

// root-context.xml의 내용~~
//
//<?xml version="1.0" encoding="UTF-8"?>
//<beans xmlns="http://www.springframework.org/schema/beans"
//	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
//	xsi:schemaLocation="http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd">
//	
//	<!-- Root Context: defines shared resources visible to all other web components -->
//		
//</beans>
