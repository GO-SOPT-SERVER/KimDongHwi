package sopt.org.ThirdSeminar.controller.dto.response;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponseDto {
    private Long userId;
    private String nickname;

    public static UserResponseDto of(Long userId, String nickname) {
        return new UserResponseDto(userId, nickname);
    }
}
