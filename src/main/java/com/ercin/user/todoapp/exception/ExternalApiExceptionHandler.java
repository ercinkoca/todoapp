package com.ercin.user.todoapp.exception;


import com.ercin.user.todoapp.dto.BaseRestResponse;
import com.ercin.user.todoapp.dto.RestResponseStatus;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.ercin.user.todoapp.enumaration.ReturnCodes.UNKNOWN_ERROR;


@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(basePackages = "com.ercin.user.todoapp.controller")
public class ExternalApiExceptionHandler {


    @ExceptionHandler(RestException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public BaseRestResponse handleRestException(RestException ex) {
        return new BaseRestResponse(new RestResponseStatus(ex.getCode().intValue(), ex.getDescription()));
    }

    @ExceptionHandler(ServiceRuntimeException.class)
    public BaseRestResponse handleServiceRuntimeException(HttpServletResponse response, ServiceRuntimeException ex) {
        final var returnCode = ex.getCode();
        final var description = ex.getDescription();
        response.setStatus(returnCode.httpStatus());
        return new BaseRestResponse(new RestResponseStatus(returnCode.intValue(), description));
    }

    @ExceptionHandler(AuthenticationException.class)
    public BaseRestResponse handleAuthenticationException(HttpServletResponse response, ServiceRuntimeException ex) {
        final var returnCode = ex.getCode();
        final var description = ex.getDescription();
        response.setStatus(returnCode.httpStatus());
        return new BaseRestResponse(new RestResponseStatus(returnCode.intValue(), description));
    }


    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseRestResponse handleAllException(Exception ex) {
        return new BaseRestResponse(new RestResponseStatus(UNKNOWN_ERROR));
    }


}
