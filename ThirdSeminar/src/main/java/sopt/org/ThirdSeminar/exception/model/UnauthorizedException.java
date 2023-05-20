package sopt.org.ThirdSeminar.exception.model;

import sopt.org.ThirdSeminar.exception.ErrorStatus;

public class UnauthorizedException extends SoptException{
    public UnauthorizedException(ErrorStatus error, String message) {
        super(error, message);
    }
}
