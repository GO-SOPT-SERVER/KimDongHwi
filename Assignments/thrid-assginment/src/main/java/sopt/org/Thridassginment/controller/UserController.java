package sopt.org.Thridassginment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import sopt.org.Thridassginment.common.dto.ApiResponseDto;
import sopt.org.Thridassginment.controller.dto.request.UserRequestDto;
import sopt.org.Thridassginment.controller.dto.response.UserResponseDto;
import sopt.org.Thridassginment.exception.ErrorStatus;
import sopt.org.Thridassginment.exception.NickNameException;
import sopt.org.Thridassginment.exception.SuccessStatus;
import sopt.org.Thridassginment.exception.EmailException;
import sopt.org.Thridassginment.service.UserService;

import javax.validation.Valid;

@RestController //Controller + responsebody 어노테이션 json형태로 객체 데이터 반환
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/signup")
    @ResponseStatus(HttpStatus.CREATED) //응답의 상태코드 결정
    public ApiResponseDto<UserResponseDto> create(@RequestBody @Valid final UserRequestDto request){
        try {
            return ApiResponseDto.success(SuccessStatus.SIGNUP_SUCCESS, userService.create(request));
        } catch (EmailException e) {
            return NickNameExceptionHandler(ErrorStatus.CONFLICT_EMAIL_EXCEPTION);
        } catch (NickNameException e) {
            return NickNameExceptionHandler(ErrorStatus.CONFLICT_NICKNAME_EXCEPTION);
        }
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    private static ApiResponseDto NickNameExceptionHandler(ErrorStatus conflictNicknameException) {
        return ApiResponseDto.error(conflictNicknameException);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    private static ApiResponseDto EmailExceptionHandler(ErrorStatus conflictEmailException) {
        return ApiResponseDto.error(conflictEmailException);
    }
}
