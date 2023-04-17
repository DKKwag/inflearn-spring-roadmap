package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();   //DIP를 지키지않음 클라이언트에서 수정해줘야한다.
//    private final DiscountPolicy discountPolicy = new FixDisocuntPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;  //DIP를 지킴(인터페이스에만 의존)

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findbyId(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
