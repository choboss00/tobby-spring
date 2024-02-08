package com.tobyspring.helloboot;

import java.util.Objects;

public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    public String hello(String name) {
        // null 이 아닌 경우 그대로 리턴, null 인 경우 예외처리
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
