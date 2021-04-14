package com.back.ecom.tp.exception;

public class AlreadyExistException extends FunctionalException {

    private static final long serialVersionUID = 1L;

    public AlreadyExistException(String msg) {
        super(FunctionalErrorCode.ALREADY_EXISTE_EXCEPTION,msg);
    }

}