package repository;

import domain.Account;
import java.util.HashMap;
import java.util.Map;

public class AccountRepository {
    private static final Map<Long, Account> accountRepository = new HashMap<>();

    private static AccountRepository instance;

    private AccountRepository() {
    }

    public static AccountRepository getInstance() {
        if (instance == null){
            instance = new AccountRepository();
        }
        return instance;
    }
    public void saveAccount(Long accountNum, Account account) { // 계좌 생성
        accountRepository.put(accountNum, account);
    }

    public Long accountCheck(Long accountNum, String password) { //계좌 유무 확인
        try {
            Account account1 = accountRepository.get(accountNum);
            if (account1.getPassword().equals(password)) {
                return accountNum;
            }
        } catch (NullPointerException e) {
            System.out.println("ERROR :: 유효하지 않은 계좌입니다.");
        }
        return -1L;
    }

    public boolean duplCheck(Long accountNum) { //계좌 번호 중복 확인
        return accountRepository.containsKey(accountNum);
    }

    public Account getAccount(Long key) {
        return accountRepository.get(key);
    }

    public void updateAccount(Long key, Account account) {
        accountRepository.replace(key, account);
    }

}
