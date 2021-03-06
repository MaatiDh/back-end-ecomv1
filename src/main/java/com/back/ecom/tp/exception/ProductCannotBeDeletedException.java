package com.back.ecom.tp.exception;

import java.util.List;

import static java.lang.String.join;

public class ProductCannotBeDeletedException extends FunctionalException {

    private static final long serialVersionUID = 4896893580503414663L;

    public ProductCannotBeDeletedException() {
        super(FunctionalErrorCode.NOT_NULL_ENTITY);
    }

}
