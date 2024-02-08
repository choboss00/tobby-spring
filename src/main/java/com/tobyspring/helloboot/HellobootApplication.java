package com.tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
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
		// 추상화했기 때문에, 톰캣이 아닌 다른 WebServer 구현체로 변경할 수 있음
		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		// getWebServer 는 TomcatWebServer 객체를 반환하는데, 이 객체는 WebServer 인터페이스를 상속받았기 때문에 추상화로 인해 받을 수 있음
		WebServer webServer = serverFactory.getWebServer(
				servletContext -> {
					// 서블릿 컨텍스트 초기화
					servletContext.addServlet("frontcontroller", new HttpServlet() {
								@Override
								protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
									// 인증, 보안, 다국어 처리, 공통 기능 등등 처리를 앞에서 진행
									// frontcontroller 가 매핑 기능을 담당해야 함
									if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
										String name = req.getParameter("name");

										// 상태 코드, 헤더 ( 컨텐츠 타입 헤더 ), 바디
										resp.setStatus(HttpStatus.OK.value());
										resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
										resp.getWriter().println("hello " + name);
									}
									else if (req.getRequestURI().equals("/user")) {
										// ...
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
