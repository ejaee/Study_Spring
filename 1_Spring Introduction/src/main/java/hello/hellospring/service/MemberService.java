package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

//@Service
@Transactional // jpa 에서 데이터를 저장, 변경할때 적어줘야한다 -> 회원가입에서만 하니까 그곳에만 적어줘도 무방
public class MemberService {

    private final MemberRepository memberRepository;

//    @Autowired // 스프링이 MemberService 를 생성할 때 레포지토리를 넣어준다
    public MemberService(MemberRepository memberRepository) {  // d.i
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 x
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        Optional 을 바로 빼는것이 좋지 않다
//        result.ifPresent(m -> {
//            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
//        });
//        result.get() member 를 바로 빼도 되지만 권장하지 않는다
//        result.orElseGet() 를 주로 쓴다 / 있으면 값을 꺼내고 없으면 명시한 메서드를 실행한다

//        위 조건을 모두 고려한 코드는 다음과 같다
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalArgumentException("이미 존재하는 회원입니다.");
            });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();

    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    /**
     * 서비스 메서드의 이름을 보면
     * 단순히 값을 빼고 넣는 레포지토리와는 다르게
     * 비즈니스 로직에 가깝다는 느낌을 받는다
     * 그래서 메서드가 비즈니스에 의존적으로 쓰인다
     */

}
