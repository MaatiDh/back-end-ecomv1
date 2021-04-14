package com.back.ecom.tp.exception;

public class ValueCannotChangeException extends FunctionalException {

    private static final long serialVersionUID = 1L;

    public ValueCannotChangeException(String msg) {
        super(FunctionalErrorCode.VALUE_MUST_NOT_CHANGED,msg);
    }

}