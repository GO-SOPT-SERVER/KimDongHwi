package sopt.org.SecondAssginment.controller.user.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisterRequestDTO {
    private String gender;

    private String name;

    private String contact;

    private int age;

    public RegisterRequestDTO() {
    }
}

