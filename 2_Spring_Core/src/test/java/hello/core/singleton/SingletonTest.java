package hello.core.singleton;

import static org.assertj.core.api.Assertions.*;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonTest {

    // 호출할 때 마다 새로운 객체가 만들어진다
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void    pureContainer() {
        AppConfig appConfig = new AppConfig();
        //1. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        //2. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        //참조 값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isNotSameAs(memberService2);
        //해결 방안은 객체를 딱 1개를 생성하고 공유하도록 설계한다
    }

    @Test
    @DisplayName("싱글톤 패턴을 사용한 객체 사용")
    void    singletonService(){
        SingletonService instance1 = SingletonService.getInstance();
        SingletonService instance2 = SingletonService.getInstance();

        System.out.println("instance1 = " + instance1);
        System.out.println("instance2 = " + instance2);

        assertThat(instance1).isSameAs(instance1);
        // isSameAs == 객체
        // isEqual == equal 메서드
    }
}
