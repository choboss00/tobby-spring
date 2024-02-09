package com.tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloServiceTest {

    @Test
    void simpleHelloTestService() {
        SimpleHelloService simpleHelloService = new SimpleHelloService();

        String name = simpleHelloService.sayHello("Test");

        Assertions.assertThat(name).isEqualTo("Hello Test");
    }
}
