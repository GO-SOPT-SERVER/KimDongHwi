package sopt.org.ThirdSeminar.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sopt.org.ThirdSeminar.common.dto.ApiResponseDto;
import sopt.org.ThirdSeminar.config.jwt.JwtService;
import sopt.org.ThirdSeminar.controller.dto.request.UserLoginRequestDto;
import sopt.org.ThirdSeminar.controller.dto.request.UserRequestDto;
import sopt.org.ThirdSeminar.controller.dto.response.UserLoginResponseDto;
import sopt.org.ThirdSeminar.controller.dto.response.UserResponseDto;
import sopt.org.ThirdSeminar.exception.SuccessStatus;
import sopt.org.ThirdSeminar.service.UserService;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "User", description = "유저 API Document")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "유저 생성 API", description = "유저를 서버에 등록합니다.")
    public ApiResponseDto<UserResponseDto> create(@RequestBody @Valid final UserRequestDto request) {
        return ApiResponseDto.success(SuccessStatus.SIGNUP_SUCCESS, userService.create(request));
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "유저 로그인 API", description = "유저가 서버에 로그인을 요청합니다.")
    public ApiResponseDto<UserLoginResponseDto> login(@RequestBody @Valid final UserLoginRequestDto request) {
        final Long userId = userService.login(request);
        final String token = jwtService.issuedToken(String.valueOf(userId));
        return ApiResponseDto.success(SuccessStatus.LOGIN_SUCCESS, UserLoginResponseDto.of(userId, token));
    }
}
