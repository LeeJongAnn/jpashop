package jpabook.jpashop.domain.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


// RequiredArgsConstructor로 생성자를 생략하고 위에 부분에 final 키워드를 붙여서
// 코드를 간결하게 만들어준다.                                                   @Service
@Transactional(readOnly = true)
public class MemberService {

    private MemberRepository memberRepository;


    // 생성자 주입을 선택하자
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원 가입
    @Transactional
    public Long join(Member member){

        validateDuplicatedMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicatedMember(Member member) {
        List<Member> findMember = memberRepository.findByName(member.getName());
        if(!findMember.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }




    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long MemberId) {
        return memberRepository.findOne(MemberId);
    }

}
