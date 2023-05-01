package sopt.org.SecondAssginment.controller.account.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import sopt.org.SecondAssginment.domain.User;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountRequestDTO {

    private User user;
}
