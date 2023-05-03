package sopt.org.SecondAssginment.controller.account;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sopt.org.SecondAssginment.controller.account.dto.AccountRequestDTO;
import sopt.org.SecondAssginment.controller.user.dto.request.RegisterRequestDTO;
import sopt.org.SecondAssginment.domain.Account;
import sopt.org.SecondAssginment.service.AccountService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/create")
    public String register(@RequestBody final RegisterRequestDTO request) {
        Long accountNum = accountService.register(request);
//        System.out.println(accountList.get(accountNum.intValue() - 1).toString());
        return "고객님의 생성 계좌는 "+ accountNum +" 입니다.";
    }

    @GetMapping("/myAccount")
    public String search(@RequestParam final Long accountNum) {
        try {
            Account account = accountService.getAccount(accountNum);
            return "성함: " + account.getUser().getName() + " 잔액: " + account.getDeposit();
        } catch (IndexOutOfBoundsException e) {
            return "찾으시는 계좌가 존재하지 않습니다.";
        }
    }

    @PutMapping("/{accountId}/edit")
    public String change(@PathVariable final Long accountId, @RequestBody final RegisterRequestDTO update){
        accountService.changeAccount(accountId, update);
        return "업데이트 된 계좌 정보 반환";
    }

    @DeleteMapping("/{accountId}/edit")
    public String delete(@PathVariable final Long accountId){
        accountService.deleteAccount(accountId);
        return accountId + " 계좌가 성공적으로 삭제되었습니다.";
    }
}
