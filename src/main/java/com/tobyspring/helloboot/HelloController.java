package com.tobyspring.helloboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
// @RestController : dispatcherServlet 하고 직접적인 관련은 없음
// @RestController 이 애노테이션을 붙이면, 명시적으로 @ResponseBody 를 붙이지 않아도 됨
@RequestMapping("/hello") // 클래스 레벨에 걸어주면, dispatcherServlet 이 찾을 수 있음
public class HelloController {
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    // dispatcherServlet 이 받을 수 있게끔 어노테이션을 걸어줌
    // method 레벨까지 다 뒤지는건 아니고, 클래스 레벨까지만 뒤짐
    // dispatcherServlet 은 view 를 찾는데, 없으니 404 에러를 뱉음. 그래서 애노테이션을 하나더 추가해줌
    @GetMapping("")
    @ResponseBody
    public String hello(String name) {
        // null 이 아닌 경우 그대로 리턴, null 인 경우 예외처리
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
