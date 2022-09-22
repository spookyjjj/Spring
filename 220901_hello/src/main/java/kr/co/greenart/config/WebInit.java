package kr.co.greenart.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//xml파일을 java파일로 옮기기 위한 여정~~start ★Web.xml의 내용을 옮겨오기!!
public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {
//AbstractAnnotationConfigDispatcherServletInitializer 상속?
//-> WebApplication이 초기화되는 시점에 DispatcherServlet를 등록할건데 Annotation기반으로 할거라는거~
	
	//root-context.xml -> RootConfig.class로 대체
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { RootConfig.class }; 
	}
	
	//servlet-context.xml -> WebConfig.class로 대체
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebConfig.class };
	}
	
	//<servlet-mapping>태그 대체~ <url-pattern>/</url-pattern>부분
	@Override
	protected String[] getServletMappings() {
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

//<<Web.xml>>
//web.xml에 기술된 내용을 바탕으로 sevletContainer를 초기화함! -> 여따가 Ioc Container쓰겠다고 설정해야함
//리스너(ContextLoaderListener), 서블릿매핑(DispatcherServlet), 필터 등록 ㄱㄱ

//ContextLoaderListener는 ServletContext의 라이프 사이클을 인지해서 ApplicationContext를 ServletContext에 추가/삭제
//필요한 설정파일 위치과 class type은 context-param으로 등록
//
//	<listener>
//		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
//	</listener>

//context-param에서 name으로 설정할 수 있는 애들?
//contextConfigLocation : IoC Container의 설정파일의 위치(빈 등록 등) -> 설정파일은 xml도 되고 java class도 되고~
//contextClass : 어떤 class type으로 bean을 등록할지 (디폴트로 annotaion이 설정되어있어서 생략가능)
//
//	<context-param>
//		<param-name>contextConfigLocation</param-name>
//		<param-value>/WEB-INF/spring/root-context.xml</param-value>
//	</context-param>
//	<context-param>
//		<param-name>contextClass</param-name>
//		<param-value>org.springframework.web.context.support.AnnotaionConfigWebApplicationContext</param-value>
//	</context-param>

//"/"로 접속한 모든 요청을 DispatcherServlet으로 보냄
//이때서야 contextConfigLocation에 servlet-context가 등록된다
//-> root-context의 bean과, servlet-context의 bean의 차이가 여기서 옴
//
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

