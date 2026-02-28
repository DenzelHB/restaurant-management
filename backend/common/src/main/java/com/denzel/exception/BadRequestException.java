package com.denzel.exception;

import org.springframework.http.HttpStatus;

/**
 * @creation 28/02/2026 00:03
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.exception.handler
 **/
public class BadRequestException extends RuntimeException {

    private Integer status = HttpStatus.BAD_REQUEST.value();

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(HttpStatus status, String msg) {
        super(msg);
        this.status = status.value();
    }
}
