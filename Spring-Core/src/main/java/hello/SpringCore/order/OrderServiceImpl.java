package hello.SpringCore.order;

import hello.SpringCore.discount.DiscountPolicy;
import hello.SpringCore.discount.FixDiscountPolicy;
import hello.SpringCore.member.Member;
import hello.SpringCore.member.MemberRepository;
import hello.SpringCore.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository(); //DIP 위반
    private MemberRepository memberRepository;
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); //DIP 위반(인터페이스에만 의존해야하는 것을 위반) : 인터페이스 뿐 아니라 구체 클래스도 함께 의존하므로
    private DiscountPolicy discountPolicy;  //DIP 지킴

    @Autowired //생성자 1개면 생략 가능
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy){
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
