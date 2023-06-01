package sopt.org.ThirdSeminar.controller.dto.request;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class BoardRequestDto {
//    @Email(message = "이메일 형식에 맞지 않습니다")
//    private String email;

    @NotNull
    private MultipartFile thumbnail;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private Boolean isPublic;
}
