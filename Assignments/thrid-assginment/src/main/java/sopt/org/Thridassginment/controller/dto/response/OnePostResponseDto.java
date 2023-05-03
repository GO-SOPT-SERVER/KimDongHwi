package sopt.org.Thridassginment.controller.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OnePostResponseDto {
    private Long postId;
    private String user;
    private String title;
    private String text;
    private LocalDateTime postTime;

    public static OnePostResponseDto of(Long postId, String user, String title, String text, LocalDateTime postTime){
        return new OnePostResponseDto(postId, user, title, text, postTime);
    }
}
