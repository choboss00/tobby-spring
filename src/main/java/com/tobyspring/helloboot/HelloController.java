package com.tobyspring.helloboot;

public class HelloController {

    public String hello(String name) {
        return "Hello, Spring Boot!" + name;
    }
}
