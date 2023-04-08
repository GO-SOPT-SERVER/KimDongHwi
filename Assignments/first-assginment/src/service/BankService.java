package service;

import domain.Account;
import domain.Member;
import repository.AccountRepository;
import repository.MemberRepository;

import java.util.Scanner;

/**
 * 신규 고객, 계좌 생성
 */
public class BankService {

    MemberRepository memberRepository = MemberRepository.getInstance();
    AccountRepository accountRepository = AccountRepository.getInstance();
    Scanner sc = new Scanner(System.in);
    public void run(int workCode) {
        if (workCode == 1) {
            createMember();
        } else if (workCode == 2){
            createAccount();
        }
    }

    private void createAccount() {
        System.out.println("이름을 입력해주세요");
        String name = sc.next();
        System.out.println("주민번호를 입력해주세요 (* - 부호는 빼고 입력해주세요)"); //validation은 pass
        Long personalNumber = sc.nextLong();
        Long memberId = memberRepository.checkMember(name, personalNumber);
        if (memberId > -1L){
            Long acNum = (long) (Math.random() * 1000);
            if (!accountRepository.duplCheck(acNum)){ //중복 계좌가 없으면
                System.out.println("비밀번호를 입력해 주십시오.");
                String password = sc.next();
                Account account = new Account(acNum, password, memberId);
                accountRepository.saveAccount(acNum, account);
                System.out.println("계좌번호 = " + acNum);
            }
        } else {
            System.out.println("회원의 정보가 없습니다.");
        }
    }

    private void createMember() {
        System.out.println("이름을 입력해주세요");
        String name = sc.next();
        System.out.println("주민번호를 입력해주세요 (* - 부호는 빼고 입력해주세요)"); //validation은 pass
        Long personalNumber = sc.nextLong();
        Member member = new Member(name, personalNumber);
        memberRepository.saveMember(personalNumber, member);
    }
}
