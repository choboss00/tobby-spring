package com.tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@HellobootTest
public class HelloRepositoryTest {

    @Autowired
    HelloRepository helloRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void init() {
        // table 이 비어있는 상태로 시작하기 때문에 DB 테이블을 만들고 초기화하는 작업이 필요함
        jdbcTemplate.execute("create table if not exists hello(name varchar(50) primary key, count int)");
    }

    @Test
    void findHelloFailed() {
        Assertions.assertThat(helloRepository.findHello("Toby")).isNull();
    }

    @Test
    void increaseCount() {
        helloRepository.increaseCount("Toby");
        Assertions.assertThat(helloRepository.countOf("Toby")).isEqualTo(1);
        helloRepository.increaseCount("Toby");
        Assertions.assertThat(helloRepository.countOf("Toby")).isEqualTo(2);
    }

}
