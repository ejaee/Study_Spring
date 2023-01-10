package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 실제 동작에 필요한 구현 객체를 생성
// 구현 객체를 생성하고 연결하는 책임은 AppConfig 가 담당하도록 설계
// 이로인해 클라이언트 객체는 실행하는 책임만 담당하게 됨 -> SRP 원칙을 지킴
@Configuration
public class AppConfig { // 역할을 세우고 구현이 그 안에 들어가도록 refactor

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository()); // 생성자 주입
    }

    @Bean
    public MemoryMemberRepository memberRepository() { // refactor
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {    // refactor
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
