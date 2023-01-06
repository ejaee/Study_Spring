package hello.hellospring.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.apache.catalina.realm.JAASMemoryLoginModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository); // test 에서 java 코드의 repo 를 사용하기 위해 d.i 를 사용
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("spring1");

        // when
        Long saveId = memberService.join(member); // 회원가입을 했을 때

        // then 저장을 한게 repository 에 있는게 맞는지 확인
        Member findMember = memberService.findOne(saveId).get();

        assertThat(member.getName()).isEqualTo(findMember.getName());

    }
    // 위 test 는 너무 단순하다
    // join 의 validate check 부분을 확인해야 한다

    @Test
    void 회원가입_중복_예외사항() {
        // given
        Member member1 = new Member();
        member1.setName("spring1");

        Member member2 = new Member();
        member2.setName("spring1");

        // when
        memberService.join(member1);
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
            () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

/*      test 코드에 try catch 넣기 좀 그렇다 -> assertThrows 로 대체
        try {
            memberService.join(member2);
            fail("에외가 발생해야 합니다");
        } catch (IllegalArgumentException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        opt + command + /
*/


        // then


    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}