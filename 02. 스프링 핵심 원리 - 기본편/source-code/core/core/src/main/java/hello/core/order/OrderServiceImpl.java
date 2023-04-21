package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();   //DIP를 지키지않음 클라이언트에서 수정해줘야한다.
//    private final DiscountPolicy discountPolicy = new FixDisocuntPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    //필드 주입 (잘 사용하지 않음)
//    @Autowired private MemberRepository memberRepository;
    private MemberRepository memberRepository;
//    @Autowired private DiscountPolicy discountPolicy;  //DIP를 지킴(인터페이스에만 의존)
    private DiscountPolicy discountPolicy;
    /*@Autowired
    //수정자 주입 선택,변경에 용이
    public void setMemberRepository(MemberRepository memberRepository) {
        System.out.println("memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        System.out.println("discountPolicy = " + discountPolicy);
        this.discountPolicy = discountPolicy;
    }*/

    //생성자가 하나일때는 Autowired를 생략해도 자동으로 주입해준다!! 참고!!
    /*@Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        System.out.println("1. OrderServiceImpl.OrderServiceImpl");
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }*/

    //메서드 위에 Autowired ( 잘 사용하지 않음 )
    @Autowired
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }



    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findbyId(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    
    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
