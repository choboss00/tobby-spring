package com.tobyspring.config;

import com.tobyspring.config.autoconfig.DispatcherServletConfig;
import com.tobyspring.config.autoconfig.TomcatWebServerConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(MyAutoConfigImportSelector.class) // 이 Selector 는 특별하게 메소드의 리턴값을 가져옴
public @interface EnableMyAutoConfiguration {
}
