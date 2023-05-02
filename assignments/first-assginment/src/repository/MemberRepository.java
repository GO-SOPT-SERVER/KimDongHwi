package repository;

import domain.Member;

import java.util.HashMap;
import java.util.Map;

public class MemberRepository {
    private static final Map<Long, Member> memberRepository = new HashMap<>();

    private static MemberRepository instance;

    private MemberRepository() {
    }

    public static MemberRepository getInstance() {
        if (instance == null){
            instance = new MemberRepository();
        }
        return instance;
    }

    public void saveMember(Long personalNumber, Member member) {
        member.setId(personalNumber);
        memberRepository.put(personalNumber, member);
    }

    public Member getMember(Long id) {
        return memberRepository.get(id);
    }

    public Long checkMember(String name, Long personalNumber) {
        try {
            Member member = memberRepository.get(personalNumber);
            if (member.getName().equals(name)) {
                return member.getId();
            }
        } catch (NullPointerException e) {
            System.out.println("ERROR :: 유효하지 않은 회원입니다.");
        }
        return -1L;
    }

    public boolean duplMemberCheck(Long personalNum) {return memberRepository.containsKey(personalNum);}
}
