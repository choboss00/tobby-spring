package com.tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HellobootApplication {

	public static void main(String[] args) {
		// 스프링 컨테이너를 생성
		GenericWebApplicationContext context = new GenericWebApplicationContext();
		// bean 등록 ( 오브젝트를 넘기는 것이 아닌 클래스 정보만 넘김 )
		context.registerBean(HelloController.class);
		context.registerBean(SimpleHelloService.class);
		// 컨테이너 초기화 ( bean object 생성 )
		context.refresh();

		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();

		WebServer webServer = serverFactory.getWebServer(
				servletContext -> {
					servletContext
							.addServlet("dispatcherServlet", new DispatcherServlet(context))
							.addMapping("/*"); // frontcontroller 서블릿을 모든 요청에 매핑
				}
		);
		webServer.start();

	}

}
