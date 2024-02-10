package com.tobyspring.helloboot;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

// decorator 는 HelloService 를 상속받으면서, HelloService 를 구현한 또 다른 프로젝트를 의존해야 함
@Service
@Primary // 우선적으로 이 빈을 사용하겠다는 뜻
public class HelloDecorator implements HelloService {

    private final HelloService helloService;

    public HelloDecorator(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public String sayHello(String name) {
        return "*" + helloService.sayHello(name) + "*";
    }

    @Override
    public int countOf(String name) {
        return helloService.countOf(name);
    }
}
