package com.ercin.user.todoapp.enumaration;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class SimpleReturnCode implements ReturnCode {
    private final int code;
    private final String description;
    private final int httpStatus;

    public SimpleReturnCode(int code, String description, HttpStatus httpStatus) {
        this(code, description, httpStatus.value());
    }

    @Override
    public String stringValue() {
        return String.valueOf(code);
    }

    @Override
    public int intValue() {
        return code;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public int httpStatus() {
        return httpStatus;
    }

    @Override
    public boolean isSuccess() {
        return code == ReturnCodes.NO_ERROR.intValue();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o instanceof ReturnCode) {
            return ((ReturnCode) o).intValue() == code;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return code;
    }

    @Override
    public String toString() {
        return "ReturnCode{" + code + ',' + description + '}';
    }
}
