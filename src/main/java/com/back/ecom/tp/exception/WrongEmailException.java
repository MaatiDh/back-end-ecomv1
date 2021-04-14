package com.back.ecom.tp.exception;

public class WrongEmailException extends FunctionalException {

    private static final long serialVersionUID = 1L;

    public WrongEmailException() {
        super(FunctionalErrorCode.WRONG_EMAIL);
    }

}