package com.tobyspring.config;

import org.springframework.context.annotation.Configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Configuration(proxyBeanMethods = false) // proxyBeanMethods 는 기본값이 true 이며, true 일 경우에는 @Bean 메소드를 호출할 때마다 새로운 프록시 객체를 생성하게 된다.
public @interface MyAutoConfiguration {
}
