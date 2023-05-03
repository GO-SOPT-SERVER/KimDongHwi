package sopt.org.Thridassginment.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class PostRequestDto {
    @NotNull
    private Long userId;

    @NotNull
    private String title;

    @NotNull
    private String text;

}
