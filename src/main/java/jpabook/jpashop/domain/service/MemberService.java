package jpabook.jpashop.domain.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    //회원 가입
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
