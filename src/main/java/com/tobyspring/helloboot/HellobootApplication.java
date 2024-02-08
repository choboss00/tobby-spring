package com.tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
// config 정보, 구성 정보를 가지고 있는 클래스라고 명시 ( spring container 가 이를 보고 bean object 를 만들면 되겠다고 인식함 )
// configuration 이 붙은 클래스는 중요한 정보들을 많이 담고있다고 인식
@Configuration
@ComponentScan // 이 패키지를 기준으로 하위 패키지를 모두 뒤져서 @Component 가 붙은 클래스를 찾아서 bean object 로 등록
public class HellobootApplication {

	@Bean
	public ServletWebServerFactory servletWebServerFactory() {
		return new TomcatServletWebServerFactory();
	}
	
	@Bean
	public DispatcherServlet dispatcherServlet() {
		return new DispatcherServlet();
	}
	
	public static void main(String[] args) {
		// 스프링 컨테이너 + 서블릿 컨테이너 통합
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext() {
			@Override
			protected void onRefresh() {
				super.onRefresh();

				ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
				DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
				
				WebServer webServer = serverFactory.getWebServer(
						servletContext -> {
							servletContext
									.addServlet("dispatcherServlet", dispatcherServlet)
									.addMapping("/*");
						}
				);
				webServer.start();
			}
		};
		// spring container 에게 이 클래스를 등록하라고 명시
		// 이 클래스를 기준으로 하위 패키지를 모두 뒤져서 @Component 가 붙은 클래스를 찾아서 bean object 로 등록
		context.register(HellobootApplication.class);
		// 컨테이너 초기화 ( bean object 생성 )
		context.refresh();

	}

}
