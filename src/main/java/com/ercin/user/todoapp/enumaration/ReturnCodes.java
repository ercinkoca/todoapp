package com.ercin.user.todoapp.enumaration;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

public enum ReturnCodes implements ReturnCode {
    NO_ERROR(200, "No error", OK),
    UNKNOWN_ERROR(500, "Unknown error is occurred", INTERNAL_SERVER_ERROR),
    BAD_USER_CREDENTIALS(400, "Bad user credentials", BAD_REQUEST),
    USER_NOT_FOUND(404, "User Not Found", BAD_REQUEST),
    USER_ALREADY_DEFINED(400, "User already defined", BAD_REQUEST),
    EMAIL_CANNOT_BLANK(400, "Email Can Not Empty!", BAD_REQUEST),
    PASSWORD_SHOULD_SAME(400, "Password and ConfirmedPassword should be same!", BAD_REQUEST),
    TODO_LIST_NOT_FOUND(404, "Todo List Not Found", BAD_REQUEST),
    INVALID_TOKEN(400, "Invalid Token", BAD_REQUEST),
    PASSWORD_NOT_MATCH(400, "Password Not Match", BAD_REQUEST),
    USER_NOT_VERIFIED(400, "User not verified", UNAUTHORIZED);

    private final int code;
    private final String codeString;
    private final String description;
    private final int httpStatus;

    ReturnCodes(int code, String description, HttpStatus httpStatus) {
        this.code = code;
        this.codeString = String.valueOf(code);
        this.description = description;
        this.httpStatus = httpStatus.value();
    }

    @Override
    public String stringValue() {
        return codeString;
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
        return code == NO_ERROR.code;
    }
}
