package com.tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.*;

public class HelloApiTest {

    @Test
    void helloApi() {
        // http localhost:8080/hello name=Spring
        // API 응답을 호출해서 가져올 수 있음
        TestRestTemplate rest = new TestRestTemplate();
        
        // 웹응답의 모든 요소를 가지고있는 타입
        ResponseEntity<String> resp = rest.getForEntity("http://localhost:9090/app/hello?name={name}", String.class, "Spring");
        
        // status code 200, header(content-type), body(Hello Spring) 가 출력됨
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resp.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
        assertThat(resp.getBody()).isEqualTo("*Hello Spring*");
        
    }

    @Test
    void failsHelloApi() {
        // http localhost:8080/hello name=Spring
        // API 응답을 호출해서 가져올 수 있음
        TestRestTemplate rest = new TestRestTemplate();

        // 웹응답의 모든 요소를 가지고있는 타입
        ResponseEntity<String> resp = rest.getForEntity("http://localhost:9090/app/hello?name=", String.class);

        // status code 500
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
