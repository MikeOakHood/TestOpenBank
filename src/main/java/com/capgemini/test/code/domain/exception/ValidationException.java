package com.capgemini.test.code.domain.exception;

import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException {
    private final String field;

    public ValidationException(String field, String message) {
        super(message);
        this.field = field;
    }

}
