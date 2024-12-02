package com.ercin.user.todoapp.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public class BaseRestResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final RestResponseStatus status;

    public static BaseRestResponse ok() {
        return new BaseRestResponse(RestResponseStatus.ok());
    }
}
