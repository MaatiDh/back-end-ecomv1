package com.back.ecom.tp.exception;

import java.util.List;

import static java.lang.String.join;

public class NullEntityException extends FunctionalException {

    private static final long serialVersionUID = 4896893580503414663L;

    public NullEntityException(Class<?> entityClass) {
        super(FunctionalErrorCode.NOT_NULL_ENTITY, entityClass.getSimpleName());
    }

    public NullEntityException(List<String> fields) {
        super(FunctionalErrorCode.NOT_NULL_FIELDS, join(", ", fields));
    }


}
