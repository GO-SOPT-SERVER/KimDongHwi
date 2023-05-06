package sopt.org.ThirdSeminar.exception.model;

import lombok.Getter;
import sopt.org.ThirdSeminar.exception.ErrorStatus;

@Getter
public class SoptException extends RuntimeException {

    private final ErrorStatus error;

    public SoptException(ErrorStatus error, String message) {
        super(message);
        this.error = error;
    }

    public int getHttpStatus() {
        return error.getHttpStatusCode();
    }
}