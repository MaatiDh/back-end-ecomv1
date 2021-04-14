package com.back.ecom.tp.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static java.lang.String.format;

/**
 * This enum represents the functional error codes.
 */
@Getter
public enum FunctionalErrorCode {

    BAD_REQUEST(0, HttpStatus.BAD_REQUEST, "Bad Request"),
    NOT_FOUND_ENTITY(1, HttpStatus.NOT_FOUND, "No record of type %s and with id %s is present in the database"),
    USER_NOT_FOUND(2, HttpStatus.NOT_FOUND, "Login inexist : %s"),
    NOT_NULL_ENTITY(3, HttpStatus.BAD_REQUEST, "%s Entity is required."),
    NOT_NULL_FIELD(4, HttpStatus.BAD_REQUEST, "The following field is required: %s"),
    NOT_NULL_FIELDS(6, HttpStatus.BAD_REQUEST, "The following fields are required : %s"),
    INVALID_JWT_TOKEN(13, HttpStatus.UNAUTHORIZED, "Token JWT expired ou invalid"),
    WRONG_PASSWORD(16, HttpStatus.BAD_REQUEST, "Password incorrect"),
    AUTHORIZATION_FAILED(18, HttpStatus.FORBIDDEN, "Authorization failed for access token request."),
    ALREADY_EXISTE_EXCEPTION(21, HttpStatus.BAD_REQUEST,"%s already exist."),
    USER_EMAIL_NOT_FOUND(29, HttpStatus.NOT_FOUND, "Not such a user with email %s exist in database"),
    OUT_OF_STOCK(33, HttpStatus.BAD_REQUEST, "produit n'a pas de stock"),
    WRONG_EMAIL(35, HttpStatus.BAD_REQUEST, "email incorrect"),
    VALUE_MUST_NOT_CHANGED(36, HttpStatus.BAD_REQUEST,"valeur ne peut etre changé : %s"),
    CANNOT_BE_DELETED(3, HttpStatus.BAD_REQUEST, "article ne peut pas etre supprimé."),

    ;


    private final String code;
    private final HttpStatus httpStatus;
    private final String messageTemplate;

    FunctionalErrorCode(int code, HttpStatus httpStatus, String messageTemplate) {
        this.code = format("%03d", code);
        this.httpStatus = httpStatus;
        this.messageTemplate = messageTemplate;
    }
}
