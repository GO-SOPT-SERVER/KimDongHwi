package sopt.org.Thridassginment.common.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sopt.org.Thridassginment.common.dto.ApiResponseDto;
import sopt.org.Thridassginment.exception.ErrorStatus;


@RestControllerAdvice // ControllerAdvice + ResponseBody를 합친 어노테이션
//ControllerAdvice는 모든 Controller에 대한 전역적으로 발생할 수 있는 예외를 처리할 수 있는 어노테이션
public class ControllerExceptionAdvice {

    /*
     * 400 BAD_REQUEST
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class) //해당 exception이 발생하면 아래 메서드 처리
    protected ApiResponseDto handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        return ApiResponseDto.error(ErrorStatus.VALIDATION_REQUEST_MISSING_EXCEPTION);
    }

//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(NullPointerException.class)
//    protected ApiResponseDto handleNullPointerException(final NullPointerException e) {
//        return ApiResponseDto.error(ErrorStatus.NULL_POINTER_EXCEPTION);
//    }

//    @ResponseStatus(HttpStatus.CONFLICT)
//    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
//    protected ApiResponseDto handleConstraintViolationException(final SQLIntegrityConstraintViolationException e) {
//        System.out.println(e.getClass());
//        return ApiResponseDto.error(ErrorStatus.CONFLICT_EMAIL_EXCEPTION);
//    }
}


