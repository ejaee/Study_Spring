package hello.hellospring.repository;

import static org.assertj.core.api.Assertions.assertThat;

import hello.hellospring.domain.Member;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();
    // MemoryMemberRepository 만 test 하는 거니까

    // 각자의 test 들이 의존적이지 않기 위해 test 후 clear 해준다
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test   // 모든 테스트 순서는 보장이 안된다
            // 모든 테스트는 순서와 상관없이 메서드 별로 따로 동작하도록 설계되어야 한다
            // 순서에 의존적 x
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
//        Assertions.assertEquals(member, result);

        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByname() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); // rename -> shift + f6
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);


    }


}