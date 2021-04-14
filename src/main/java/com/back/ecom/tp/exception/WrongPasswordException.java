package com.back.ecom.tp.exception;

public class WrongPasswordException extends FunctionalException {

    private static final long serialVersionUID = 1L;

    public WrongPasswordException(Class<?> entityClass) {
        super(FunctionalErrorCode.WRONG_PASSWORD, entityClass.getSimpleName());
    }

}