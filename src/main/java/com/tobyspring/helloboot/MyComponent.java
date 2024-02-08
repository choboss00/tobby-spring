package com.tobyspring.helloboot;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 이 애노테이션이 언제까지 유지될 것 인지 결정
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) // 지정된 Target 위치 설정
@Component
public @interface MyComponent {
}
