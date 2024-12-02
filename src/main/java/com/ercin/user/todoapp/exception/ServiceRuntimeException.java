package com.ercin.user.todoapp.exception;


import com.ercin.user.todoapp.enumaration.ReturnCode;
import lombok.Getter;

import java.io.Serial;

import static java.util.Objects.requireNonNullElse;

@Getter
public class ServiceRuntimeException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    private final ReturnCode code;
    private final String description;

    public ServiceRuntimeException(ReturnCode returnCode) {
        this(returnCode.description(), returnCode, null);
    }

    public ServiceRuntimeException(String error, ReturnCode returnCode) {
        this(error, returnCode, null);
    }

    public ServiceRuntimeException(String error, ReturnCode returnCode, String description) {
        super(error);
        this.code = returnCode;
        this.description = requireNonNullElse(description, returnCode.description());
    }
}
