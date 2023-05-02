package sopt.org.SecondAssginment.service;

import org.springframework.stereotype.Service;
import sopt.org.SecondAssginment.controller.account.dto.AccountRequestDTO;
import sopt.org.SecondAssginment.controller.post.dto.PostRequestDTO;
import sopt.org.SecondAssginment.controller.user.dto.request.RegisterRequestDTO;
import sopt.org.SecondAssginment.domain.Account;
import sopt.org.SecondAssginment.domain.Post;
import sopt.org.SecondAssginment.domain.User;

import static sopt.org.SecondAssginment.SecondSeminarApplication.*;
import static sopt.org.SecondAssginment.SecondSeminarApplication.userList;

@Service
public class AccountService {
    public Long register(RegisterRequestDTO request) {

        User newUser = new User(
                request.getGender(),
                request.getName(),
                request.getContact(),
                request.getAge()
        );
        userList.add(newUser);
        newUser.setId((long) userList.size());

        Account account = new Account(newUser);

        // 데이터베이스에 저장
        accountList.add(account);
        account.setId((long) accountList.size());

        return account.getId();
    }

    public Account getAccount(Long id) {
        return accountList.get(Math.toIntExact(id)-1);
    }

    public void changeAccount(Long id, RegisterRequestDTO request) {
        int setId = id.intValue() -1;
        Long deposit = accountList.get(setId).getDeposit();
        User newUser = new User(
                request.getGender(),
                request.getName(),
                request.getContact(),
                request.getAge()
        );
        Account account = new Account(newUser, deposit);
        accountList.set(setId, account);
    }

    public void deleteAccount(Long id) {
        accountList.remove(id);
    }
}
