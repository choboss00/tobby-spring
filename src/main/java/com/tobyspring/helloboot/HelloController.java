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

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    // dispatcherServlet 이 받을 수 있게끔 어노테이션을 걸어줌
    // method 레벨까지 다 뒤지는건 아니고, 클래스 레벨까지만 뒤짐
    // dispatcherServlet 은 view 를 찾는데, 없으니 404 에러를 뱉음. 그래서 애노테이션을 하나더 추가해줌
    @GetMapping("/hello")
    public String hello(String name) {

        if (name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException();
        }

        return helloService.sayHello(name);
    }

    @GetMapping("/count")
    public String count(String name) {
        return "name " + helloService.countOf(name);
    }
}
