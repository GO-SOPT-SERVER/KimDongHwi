package sopt.org.SecondAssginment.controller.post.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import sopt.org.SecondAssginment.domain.User;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostRequestDTO {
    private User user;
    private String title;
    private String text;
}
