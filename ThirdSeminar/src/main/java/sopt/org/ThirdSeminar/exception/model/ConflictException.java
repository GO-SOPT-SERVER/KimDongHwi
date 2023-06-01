package sopt.org.ThirdSeminar.exception.model;

import sopt.org.ThirdSeminar.exception.ErrorStatus;

public class ConflictException extends SoptException {
    public ConflictException(ErrorStatus error, String message) {
        super(error, message);
    }
}