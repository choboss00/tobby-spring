package com.tobyspring.helloboot;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class HelloController {
    private final HelloService helloService;
    private final ApplicationContext applicationContext;

    public HelloController(HelloService helloService, ApplicationContext applicationContext) {
        this.helloService = helloService;
        this.applicationContext = applicationContext;

        System.out.println("applicationContext = " + applicationContext);
    }

    // dispatcherServlet 이 받을 수 있게끔 어노테이션을 걸어줌
    // method 레벨까지 다 뒤지는건 아니고, 클래스 레벨까지만 뒤짐
    // dispatcherServlet 은 view 를 찾는데, 없으니 404 에러를 뱉음. 그래서 애노테이션을 하나더 추가해줌
    @GetMapping("/hello")
    public String hello(String name) {
        // null 이 아닌 경우 그대로 리턴, null 인 경우 예외처리
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
