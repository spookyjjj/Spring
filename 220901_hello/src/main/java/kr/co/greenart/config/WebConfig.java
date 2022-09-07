package kr.co.greenart.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan("kr.co.greenart") // 1.Component-scan기능 대체
public class WebConfig implements WebMvcConfigurer {
//기본적으로 있던 servlet-context를 대체하려고 만든 클래스임! -> servlet-context 삭제 가능
	
	// 2.resources mapping기능 대체
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	// 3.'Resolves views selected by @Controllers to /WEB-INF/views의 .jsp' 기능 대체
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/",".jsp");
	}
	
}

//servlet-context.xml의 내용~~
//
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
