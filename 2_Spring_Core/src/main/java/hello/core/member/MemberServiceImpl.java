package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 의존관계에 문제가 있는 코드
    // MemberServiceImpl 가 MemberRepository(추상) 도 의존하고 MemoryMemberRepository(구체) 도 의존한다
    // DIP 를 위반
    private final MemberRepository memberRepository;

    // Component 의 경우 자동으로 스프링 빈으로 등록해야하는데 의존관계를 어떻게 주입할 것인가?
    // AutoAppConfig 는 아무것도 안적혀있다
    // -> @Autowired 를 통해 자동의존과계 주입이 가능하다
    @Autowired // (ac.getBean(MemberRepository.class)처럼 동작한다)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // for test
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
