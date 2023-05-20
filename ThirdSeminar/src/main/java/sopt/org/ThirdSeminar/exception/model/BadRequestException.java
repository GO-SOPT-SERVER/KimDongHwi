package sopt.org.ThirdSeminar.exception.model;

import sopt.org.ThirdSeminar.exception.ErrorStatus;

public class BadRequestException extends SoptException {
    public BadRequestException(ErrorStatus error, String message) {
        super(error, message);
    }
}
