package hello.core.singleton;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.plaf.nimbus.State;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

// 싱그톤 패턴 주의할 점
// 상태를 유지하면 안된다
class StatefulServiceTest {

    @Test
    void statefulServiceSinglenton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
            TestConfig.class);

        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // threadA: A 사용자 10000원 주문
        statefulService1.order("userA", 10000);
        // threadB: B 사용자 20000원 주문
        statefulService2.order("userA", 20000);

        // A가 주문하는 도중에 B가 끼어들었다면?
        //ThreadA: A 사용자 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);// 10000이 아닌, 20000이 나온다

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}