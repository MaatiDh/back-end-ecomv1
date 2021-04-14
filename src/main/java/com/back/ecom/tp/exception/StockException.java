package com.back.ecom.tp.exception;

public class StockException extends FunctionalException {

    private static final long serialVersionUID = 1L;

    public StockException() {
        super(FunctionalErrorCode.OUT_OF_STOCK);
    }

}