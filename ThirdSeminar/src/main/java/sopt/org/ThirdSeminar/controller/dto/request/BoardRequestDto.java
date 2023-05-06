package sopt.org.ThirdSeminar.controller.dto.request;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardRequestDto {
    @Email(message = "이메일 형식에 맞지 않습니다")
    private String email;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private Boolean isPublic;
}
