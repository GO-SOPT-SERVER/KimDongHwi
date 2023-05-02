package sopt.org.SecondSeminar.controller.post.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import sopt.org.SecondSeminar.domain.User;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostRequestDTO {
    private String gender;
    private String name;
    private String contact;
    private int age;
    private String title;
    private String text;
}
