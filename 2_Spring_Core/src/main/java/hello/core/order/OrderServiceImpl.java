package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

// Policy 객체가 없으면 정책을 바꿀때마다 OrderService 객체를 바꿔야 한다
// 단일 책밍 원칙이 잘 지켜진 것
public class OrderServiceImpl implements  OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); // 미래 확장성을 위해 member 자체를 넘김. 상황에 따라 다르다
        return new Order(memberId,  itemName, itemPrice, discountPrice);
    }
}
