package sopt.org.ThirdSeminar.exception.model;

import sopt.org.ThirdSeminar.exception.ErrorStatus;

public class NotFoundException extends SoptException {
    public NotFoundException(ErrorStatus error, String message) {
        super(error, message);
    }
}
