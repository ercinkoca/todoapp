package com.ercin.user.todoapp.dto;


import com.ercin.user.todoapp.enumaration.ReturnCode;
import com.ercin.user.todoapp.enumaration.ReturnCodes;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@EqualsAndHashCode
@Getter
@Setter
@RequiredArgsConstructor
public class RestResponseStatus implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final int code;
    private final String description;

    public static RestResponseStatus ok() {
        return new RestResponseStatus(ReturnCodes.NO_ERROR);
    }

    public RestResponseStatus(String code, String description) {
        this(Integer.parseInt(code), description);
    }

    public RestResponseStatus(ReturnCode returnCode) {
        this(returnCode.intValue(), returnCode.description());
    }
}
