package com.ercin.user.todoapp.exception;


import com.ercin.user.todoapp.enumaration.ReturnCode;
import lombok.Getter;

import static java.util.Objects.requireNonNullElse;

@Getter
public class RestException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final ReturnCode code;
    private final String description;

    public RestException(String message, ReturnCode code, String description) {
        super(message);
        this.code = code;
        this.description = requireNonNullElse(description, code.description());
    }
}
