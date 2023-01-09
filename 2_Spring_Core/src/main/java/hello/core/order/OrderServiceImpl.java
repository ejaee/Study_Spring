package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

// Policy 객체가 없으면 정책을 바꿀때마다 OrderService 객체를 바꿔야 한다
// 단일 책밍 원칙이 잘 지켜진 것
public class OrderServiceImpl implements  OrderService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // DIP 위반. 추상에만 의존해야하는데 구체에도 의존
    // OrderServiceImpl 의 소스코드도 변경했으니까 OCP 위반 (OCP: 변경하지않고 확장이 가능)

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy; // 추상에만 의존하도록 변경

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); // 미래 확장성을 위해 member 자체를 넘김. 상황에 따라 다르다
        return new Order(memberId,  itemName, itemPrice, discountPrice);
    }
}
