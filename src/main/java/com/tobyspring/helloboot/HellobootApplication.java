package com.tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

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
		MySpringApplication.run(HellobootApplication.class, args);
	}

}
