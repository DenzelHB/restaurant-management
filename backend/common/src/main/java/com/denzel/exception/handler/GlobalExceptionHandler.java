package com.denzel.exception.handler;

import com.denzel.exception.BadRequestException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @creation 27/02/2026 23:58
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.exception.handler
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public CustomHttpRequestResponse<?> handlerException(Throwable e) {
        return new CustomHttpRequestResponse<>(100, e.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public CustomHttpRequestResponse<?> badCredentialsException(BadCredentialsException e) {
        String message = "badCredentials".equals(e.getMessage()) ? "Username or password incorrect" : e.getMessage();
        return new CustomHttpRequestResponse<>(300, message);
    }


    @ExceptionHandler(BadRequestException.class)
    public CustomHttpRequestResponse<?> badRequestExceptioin(BadRequestException e) {
        return new CustomHttpRequestResponse<>(500, e.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public CustomHttpRequestResponse<?> accessDeniedException(AccessDeniedException e) {
        return new CustomHttpRequestResponse<>(600, e.getMessage());
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public CustomHttpRequestResponse<?> tokenExpired(ExpiredJwtException e) {
        return new CustomHttpRequestResponse<>(700, "token expired");
    }
}
