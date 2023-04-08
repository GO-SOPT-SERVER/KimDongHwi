package service;

import domain.Account;
import repository.AccountRepository;
import repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 돈 관리 서비스
 */
public class TransactionService {

    AccountRepository accountRepository = AccountRepository.getInstance();
    MemberRepository memberRepository = MemberRepository.getInstance();
    Scanner sc = new Scanner(System.in);

    public void run(int workCode, Long accountNum) {
        Account myAccount = accountRepository.getAccount(accountNum);
        Long myBalance = myAccount.getBalance();
        if (workCode == 1) {
            if (checkBalance(myBalance)){
                System.out.println("원하시는 금액을 입력해주세요");
                Long withdraw = sc.nextLong();
                List<Long> withdrawResult = withdraw(accountNum, myAccount, myBalance, withdraw);
                if (withdrawResult.size() != 0) {
                    System.out.println("========== 출금 성공 ==========");
                    System.out.println("출금 금액 : " + withdrawResult.get(0));
                    System.out.println("잔액 : " + withdrawResult.get(1));
                }
            }
        } else if (workCode == 2) {
            System.out.println("금액을 넣어주세요.");
            Long deposit = sc.nextLong();
            List<Long> depositResult = deposit(accountNum, myAccount, myBalance, deposit);
            System.out.println("========== 입금 성공 ==========");
            System.out.println("입금 금액 : " + depositResult.get(0));
            System.out.println("잔액 : " + depositResult.get(1));
        } else if (workCode == 3) {
            if (checkBalance(myBalance)){
                transfer(accountNum, myAccount, myBalance);
            }

        }

    }

    private void transfer(Long accountNum, Account myAccount, Long myBalance) {
        System.out.println("송금받을 계좌의 계좌번호를 입력해 주십시오.");
        Long recivAccountNum = sc.nextLong();
        System.out.println("송금하실 금액을 넣어주세요.");
        Long withdraw = sc.nextLong();
        List<Long> withdrawResult = withdraw(accountNum, myAccount, myBalance, withdraw); // 출금
        if (withdrawResult.size() != 0) {
            Account recivAccount = accountRepository.getAccount(recivAccountNum);
            deposit(accountNum, recivAccount, recivAccount.getBalance(), withdraw); //입금
            String recivName = memberRepository.getMember(recivAccount.getHostID()).getName();
            System.out.println("========== 송금 성공 ==========");
            System.out.println("입금 대상자 :: " + recivName);
            System.out.println("입금 금액 :: " + withdraw);
            System.out.println("잔액 :: " + withdrawResult.get(1));
        }
        //withdraw랑 deposit 이용해서 나머지 완성
    }

    private boolean checkBalance(Long myBalance) { // 잔액 0원인지 확인
        System.out.println("현재 잔액 :: " + myBalance);
        if (myBalance ==0L){
            System.out.println("출금 가능 금액이 없습니다.");
            return false;
        }
        return true;
    }

    private List<Long> deposit(Long accountNum, Account myAccount, Long balance, Long deposit) {
        Long summation = deposit + balance;

        Account newAccount = new Account(myAccount.getPassword(), myAccount.getHostID(), summation, accountNum);
        accountRepository.updateAccount(accountNum, newAccount);

        ArrayList<Long> objects = new ArrayList<>();
        objects.add(deposit);
        objects.add(summation);
        return objects;
    }

    private List<Long> withdraw(Long accountNum, Account myAccount, Long balance, Long withdraw) {
        Long remain = balance - withdraw;
        ArrayList<Long> objects = new ArrayList<>();
        if (remain>-1) {
            Account newAccount = new Account(myAccount.getPassword(), myAccount.getHostID(), remain, accountNum);
            accountRepository.updateAccount(accountNum, newAccount);
            objects.add(withdraw);
            objects.add(remain);
        } else {
            System.out.println("잔액이 부족합니다.");
        }
        return objects;
    }

}
