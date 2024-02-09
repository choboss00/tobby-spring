package com.tobyspring.helloboot;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class HelloControllerTest {

    @Test
    void helloController() {
        // test stop
        // 의존관계 주입을 이용해서 고립된 테스트를 작성하는 것이 목표 ( 단위 테스트 )
        // 어떤 환경 ( 서버 배포 상황, 스프링 컨테이너 안에서 동작하지 않고 ) 에 의존하지 않는 것
        HelloController helloController = new HelloController(name -> name);

        String ret = helloController.hello("Test");

        assertThat(ret).isEqualTo("Test");
    }

    @Test
    void failsHelloController() {
        HelloController helloController = new HelloController(name -> name);

        assertThatThrownBy(() -> helloController.hello(null))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> helloController.hello(""))
                .isInstanceOf(IllegalArgumentException.class);

    }

}
