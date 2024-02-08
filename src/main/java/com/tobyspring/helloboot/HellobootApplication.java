package com.tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpHeaders;
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
					servletContext.addServlet("hello", new HttpServlet() {
								@Override
								protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
									String name = req.getParameter("name");

									// 상태 코드, 헤더 ( 컨텐츠 타입 헤더 ), 바디
									resp.setStatus(HttpStatus.OK.value());
									resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
									resp.getWriter().println("hello " + name);
								}
							}
					).addMapping("/hello"); // /hello 로 들어오면 여기서 처리하겠다!
				}
		);
		webServer.start();

	}

}
