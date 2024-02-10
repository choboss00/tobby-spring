package com.tobyspring.helloboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class HellobootApplication {

	private final JdbcTemplate jdbcTemplate;

	public HellobootApplication(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@PostConstruct
	void init() {
		// table 이 비어있는 상태로 시작하기 때문에 DB 테이블을 만들고 초기화하는 작업이 필요함
		jdbcTemplate.execute("create table if not exists hello(name varchar(50) primary key, count int)");
	}

	public static void main(String[] args) {
		SpringApplication.run(HellobootApplication.class, args);
	}

}
