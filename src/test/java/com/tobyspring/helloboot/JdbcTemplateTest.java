package com.tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;

@HellobootTest
public class JdbcTemplateTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void insertAndQuery() {
        jdbcTemplate.update("insert into hello values(?, ?)", "Toby", 1);
        jdbcTemplate.update("insert into hello values(?, ?)", "Spring", 2);

        Long count = jdbcTemplate.queryForObject("select count(*) from hello", Long.class);
        Assertions.assertThat(count).isEqualTo(2);
    }
}
