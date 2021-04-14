package com.back.ecom.tp.exception;

import static java.lang.String.valueOf;

public class NotFoundException extends FunctionalException {

    private static final long serialVersionUID = 1L;

    public NotFoundException(String value) {
        super(FunctionalErrorCode.USER_NOT_FOUND, value);
    }


    public NotFoundException(Class<?> entityClass, String email) {
        super(FunctionalErrorCode.NOT_FOUND_ENTITY, entityClass.getSimpleName(), email);
    }

}
