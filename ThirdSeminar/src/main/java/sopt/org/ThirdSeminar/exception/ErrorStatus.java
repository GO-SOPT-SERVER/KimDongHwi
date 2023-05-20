package sopt.org.ThirdSeminar.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorStatus {
    /*
    BAD_REQUEST
     */
    VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    VALIDATION_REQUEST_MISSING_EXCEPTION(HttpStatus.BAD_REQUEST, "요청값이 입력되지 않았습니다."),
    INVALID_PASSWORD_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 비밀번호 입니다."),


    /**
     * 400 BAD REQUEST
     */
    REQUEST_VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 요청입니다"),

    TOKEN_TIME_EXPIRED_EXCEPTION(HttpStatus.UNAUTHORIZED, "만료된 토큰입니다."),

    /*
    CONFLICT
     */
    CONFLICT_EMAIL_EXCEPTION(HttpStatus.CONFLICT, "이미 등록된 이메일입니다."),
    CONFLICT_NICKNAME_EXCEPTION(HttpStatus.CONFLICT, "이미 등록된 닉네임입니다."),

    /**
     * 409 CONFLICT
     */
    ALREADY_EXIST_USER_EXCEPTION(HttpStatus.CONFLICT, "이미 존재하는 유저입니다"),

    /**
     * 404 NOT FOUND
     */
    NOT_FOUND_USER_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다"),

    /*
    SERVER_ERROR
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "예상치 못한 서버 에러가 발생했습니다."),
    BAD_GATEWAY_EXCEPTION(HttpStatus.BAD_GATEWAY, "일시적인 에러가 발생하였습니다.\n잠시 후 다시 시도해주세요!"),
    SERVICE_UNAVAILABLE_EXCEPTION(HttpStatus.SERVICE_UNAVAILABLE, "현재 점검 중입니다.\n잠시 후 다시 시도해주세요!"),
            ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
