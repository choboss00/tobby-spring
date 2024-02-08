package com.tobyspring.helloboot;

import java.util.Objects;

public class HelloController {

    public String hello(String name) {

        SimpleHelloService simpleHelloService = new SimpleHelloService();
        // null 이 아닌 경우 그대로 리턴, null 인 경우 예외처리
        return simpleHelloService.sayHello(Objects.requireNonNull(name));
    }
}
