package sopt.org.Thridassginment.controller.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostResponseDto {
    private Long postId;
    private String user;
    private String title;

    public static PostResponseDto of(Long postId, String user, String title){
        return new PostResponseDto(postId, user, title);
    }
}
