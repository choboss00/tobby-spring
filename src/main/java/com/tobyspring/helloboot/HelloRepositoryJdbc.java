package com.tobyspring.helloboot;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class HelloRepositoryJdbc implements HelloRepository {

    private final JdbcTemplate jdbcTemplate;

    public HelloRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Hello findHello(String name) {
        try {
            return jdbcTemplate.queryForObject("select * from hello where name = '" + name + "'",
                    (rs, rowNum) -> new Hello(
                            rs.getString("name"), rs.getInt("count")
                    ));
        } catch (Exception e) {
            return null;
        }
    }


    @Override
    public void increaseCount(String name) {
        Hello hello = findHello(name);
        if (hello == null) {
            jdbcTemplate.update("insert into hello(name, count) values(?, 1)", name);
        } else {
            jdbcTemplate.update("update hello set count = count + 1 where name = ?", name);
        }
    }
}
