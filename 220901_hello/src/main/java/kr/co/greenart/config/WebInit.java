package kr.co.greenart.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//xml파일을 java파일로 옮기기 위한 여정~~start
public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {
//★Web.xml의 내용을 옮겨오기!!
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		//root-context.xml -> RootConfig.class로 대체
		return new Class[] { RootConfig.class }; 
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		//servlet-context.xml -> WebConfig.class로 대체
		return new Class[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		//<servlet-mapping>태그 대체~ <url-pattern>/</url-pattern>부분
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

//	//root-context.xml연결파트
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
//	//servlet-context.xml연결파트
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
//	//위의 DispatcherServlet 맵핑
//	<servlet-mapping>
//		<servlet-name>appServlet</servlet-name>
//		<url-pattern>/</url-pattern>
//	</servlet-mapping>

