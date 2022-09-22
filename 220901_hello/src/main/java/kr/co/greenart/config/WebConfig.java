package kr.co.greenart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.co.greenart.controller.MyInterceptor;

@Configuration //bean설정파일임을 알려주는 어노테이션~! (ServletConfig니깐 controll제어 관련~)
@EnableWebMvc  //★어노테이션 기반의 SpringMVC를 구성할 때 필요한 bean설정을 자동으로 해주는 역할 (= <annotation-driven/>)
@ComponentScan("kr.co.greenart") //해당 클래스에 @Component가 있으면 IoC Container에 자동 bean등록
public class WebConfig implements WebMvcConfigurer {
//servlet-context를 대체하려 만든 클래스
//★WebMvcConfigurer상속? -> @EnableWebMvc에서 제공하는 bean을 커스터마이징 할 수 있는 기능!! -> 둘이 한 세트!!
	
	@Autowired
	private MyInterceptor interceptor;
	
	//<resources mapping>역할
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	//'Resolves views selected by @Controllers to /WEB-INF/views의 .jsp' 기능
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/",".jsp");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor).addPathPatterns("/my/**");
	}
	
	@Bean
	public MultipartResolver multipartResolver() { //빈을 찾는 방식이 type이 아니라 이름이다!! 이름을 꼭 이걸로 해줘야함
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("UTF-8");
		return resolver;
	}
}

	//servlet-context.xml
	//DispatcherServlet이 자동등록한 bean인 HanlderMapping, HandlerAdapter, ViewResolver 설정하기 (multipartResolver는 따로 추가해야함)
	
//<?xml version="1.0" encoding="UTF-8"?>
//<beans:beans xmlns="http://www.springframework.org/schema/mvc"
//	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
//	xmlns:beans="http://www.springframework.org/schema/beans"
//	xmlns:context="http://www.springframework.org/schema/context"
//	xsi:schemaLocation="http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd
//		http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd
//		http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">
//
//	<!-- DispatcherServlet Context: defines this servlet's request-processing infrastructure -->
//	
//	<!-- Enables the Spring MVC @Controller programming model -->
//	<annotation-driven />
//
//	<!-- Handles HTTP GET requests for /resources/** by efficiently serving up static resources in the ${webappRoot}/resources directory -->
//	<resources mapping="/resources/**" location="/resources/" />
//
//	<!-- Resolves views selected for rendering by @Controllers to .jsp resources in the /WEB-INF/views directory -->
//	<beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
//		<beans:property name="prefix" value="/WEB-INF/views/" />
//		<beans:property name="suffix" value=".jsp" />
//	</beans:bean>
//	
//	<context:component-scan base-package="kr.co.greenart" />
//	
//	
//	
//</beans:beans>
