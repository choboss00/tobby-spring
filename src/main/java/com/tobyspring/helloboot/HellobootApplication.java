package com.tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
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
public class HellobootApplication {
	@Bean // bean object 등록
	public HelloController helloController(HelloService helloService) {
		return new HelloController(helloService);
	}
	
	@Bean
	public HelloService helloService() {
		return new SimpleHelloService();
	}
	
	public static void main(String[] args) {
		// 스프링 컨테이너 + 서블릿 컨테이너 통합
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext() {
			@Override
			protected void onRefresh() {
				super.onRefresh();

				ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();

				WebServer webServer = serverFactory.getWebServer(
						servletContext -> {
							servletContext
									.addServlet("dispatcherServlet", new DispatcherServlet(this))
									.addMapping("/*"); // frontcontroller 서블릿을 모든 요청에 매핑
						}
				);
				webServer.start();
			}
		};
		// spring container 에게 이 클래스를 등록하라고 명시
		context.register(HellobootApplication.class);
		// 컨테이너 초기화 ( bean object 생성 )
		context.refresh();

	}

}
