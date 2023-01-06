package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em; // jpa 는 em 으로 모두 동작한다 spring 이 자동으로 만들어준다

    // 받은 것을 생성자로 주입받는다
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;

    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
            .setParameter("name", name)
            .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
            .getResultList();
    }
}

// ctrl t -> 검색 -> inline 활용하자 같으면 합쳐준다
