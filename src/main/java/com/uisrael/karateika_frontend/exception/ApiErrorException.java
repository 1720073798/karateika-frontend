package com.uisrael.karateika_frontend.exception;

import java.util.Map;

public class ApiErrorException extends RuntimeException {
    private final Map<String, String> errors;

    public ApiErrorException(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
