package com.back.ecom.tp.exception;

public class InvalidJwtAuthenticationException extends FunctionalException {

    public InvalidJwtAuthenticationException() {
        super(FunctionalErrorCode.INVALID_JWT_TOKEN);
    }

}