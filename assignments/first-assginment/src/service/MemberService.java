package service;

import repository.AccountRepository;

import java.util.Scanner;

/**
 *  초기 유저 계좌 세팅
 */
public class MemberService {
    AccountRepository accountRepository = AccountRepository.getInstance();
    Scanner sc = new Scanner(System.in);

    public Long run() { //초기 세팅
        System.out.println("계좌번호와 비밀번호를 입력해주세요.");
        System.out.println("계좌번호: ");
        Long accountNum = sc.nextLong();
        System.out.println("비밀번호: ");
        String password = sc.next();
        return accountRepository.accountCheck(accountNum, password);
    }

}
