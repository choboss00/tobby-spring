package com.tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@UnitTest // UnitTest 는 메타 애노테이션으로 적용할 수 없음 -> @Target 을 추가해야함
@interface FastUnitTest {

}

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Test // meta - annotation
@interface UnitTest {

}

public class HelloServiceTest {

    @UnitTest
    void simpleHelloTestService() {
        SimpleHelloService simpleHelloService = new SimpleHelloService();

        String name = simpleHelloService.sayHello("Test");

        Assertions.assertThat(name).isEqualTo("Hello Test");
    }

    @Test
    void helloDecorator() {
        HelloDecorator helloDecorator = new HelloDecorator(name -> name);

        String ret = helloDecorator.sayHello("Test");

        Assertions.assertThat(ret).isEqualTo("*Test*");
    }
}
