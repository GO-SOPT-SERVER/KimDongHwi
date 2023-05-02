package sopt.org.Thridassginment.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE) //해당 생성자의 접근 제어, 외부에서 객체를 생성할 수 없게끔 함
public enum SuccessStatus {

    /*
    user
    각각의 enum type은 signup_success = new signup_success(); 형태의 클래스와 같다
    https://blog.hexabrain.net/393 참고
    따라서 생성자, 필드 및 메서드를 가질 수 있음
    enum type의 ()안에 값을 넣으면 호출시 해당 값을 인자로 하여 생성자 호출

    - SuccessStatus.SIGNUP_SUCCESS ==> HttpStatus필드엔 created, message필드엔 message가 들어가있는 successStatus 객체 반환
     */
    SIGNUP_SUCCESS(HttpStatus.CREATED, "회원가입이 완료되었습니다."),
    POSTING_SUCCESS(HttpStatus.CREATED, "게시물이 등록되었습니다."),
    LOADINGPOSTS_SUCCESS(HttpStatus.OK, "게시물들을 성공적으로 불러왔습니다."),
    LOADINGPOST_SUCCESS(HttpStatus.OK, "게시물을 성공적으로 불러왔습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}