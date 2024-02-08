package com.tobyspring.helloboot;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MySpringApplication {
    public static void run(Class<?> applicationClass, String[] args) {
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
        context.register(applicationClass);
        // 컨테이너 초기화 ( bean object 생성 )
        context.refresh();
    }
}
