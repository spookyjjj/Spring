package kr.co.greenart.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//xml파일을 java파일로 옮기기 위한 여정~~start
public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		//기본적으로 있던 root-context.xml를 대신해서 만든 클래스를 쓸거란 소리~
		return new Class[] { RootConfig.class }; 
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		//기본적으로 있던 servlet-context를 대신해서 만든 클래스를 쓸거란 소리~
		return new Class[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		//기본적으로 있던 web.xml의 <servlet-mapping> <url-pattern>/</url-pattern>부분 대체
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		encodingFilter.setForceEncoding(true);
		return new Filter[] {encodingFilter};
	}

}

//Web.xml의 내용~~
//
//<?xml version="1.0" encoding="UTF-8"?>
//<web-app version="2.5" xmlns="http://java.sun.com/xml/ns/javaee"
//	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
//	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee https://java.sun.com/xml/ns/javaee/web-app_2_5.xsd">
//
//	<!-- The definition of the Root Spring Container shared by all Servlets and Filters -->
//	<context-param>
//		<param-name>contextConfigLocation</param-name>
//		<param-value>/WEB-INF/spring/root-context.xml</param-value>
//	</context-param>
//	
//	<!-- Creates the Spring Container shared by all Servlets and Filters -->
//	<listener>
//		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
//	</listener>
//
//	<!-- Processes application requests -->
//	<servlet>
//		<servlet-name>appServlet</servlet-name>
//		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
//		<init-param>
//			<param-name>contextConfigLocation</param-name>
//			<param-value>/WEB-INF/spring/appServlet/servlet-context.xml</param-value>
//		</init-param>
//		<load-on-startup>1</load-on-startup>
//	</servlet>
//		
//	<servlet-mapping>
//		<servlet-name>appServlet</servlet-name>
//		<url-pattern>/</url-pattern>
//	</servlet-mapping>
//
//</web-app>
