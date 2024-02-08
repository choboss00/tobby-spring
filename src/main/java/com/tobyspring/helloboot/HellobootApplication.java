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

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HellobootApplication {

	public static void main(String[] args) {
		// 스프링 컨테이너를 생성
		GenericApplicationContext context = new GenericApplicationContext();
		// bean 등록 ( 오브젝트를 넘기는 것이 아닌 클래스 정보만 넘김 )
		context.registerBean(HelloController.class);
		// 컨테이너 초기화 ( bean object 생성 )
		context.refresh();

		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();

		WebServer webServer = serverFactory.getWebServer(
				servletContext -> {
					// 서블릿 컨텍스트 초기화
					servletContext.addServlet("frontcontroller", new HttpServlet() {
								@Override
								protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
									// 인증, 보안, 다국어 처리, 공통 기능 등등 처리를 앞에서 진행
									// frontcontroller 가 매핑 기능을 담당해야 함 ( mapping )
									if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
										String name = req.getParameter("name");

										HelloController helloController = context.getBean(HelloController.class);
										// binding : data 를 집어넣어서 처리하는 것
										String ret = helloController.hello(name);

										// 상태 코드 ( 기본값 : 200 ), 헤더 ( 컨텐츠 타입 헤더 ), 바디
										resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
										resp.getWriter().println(ret);
									}
									else {
										resp.setStatus(HttpStatus.NOT_FOUND.value());
									}


								}
							}
					).addMapping("/*"); // frontcontroller 서블릿을 모든 요청에 매핑
				}
		);
		webServer.start();

	}

}
