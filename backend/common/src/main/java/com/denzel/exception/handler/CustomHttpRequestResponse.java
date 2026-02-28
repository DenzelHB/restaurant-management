package com.denzel.exception.handler;

import com.denzel.utils.CustomHttpStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * @creation 27/02/2026 23:28
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.exception.handler
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomHttpRequestResponse <T> implements Serializable {
    private int code;
    private String message;
    private T content;

    public CustomHttpRequestResponse(CustomHttpStatus customHttpStatus, T content) {
        this.code = customHttpStatus.getCode();
        this.message = customHttpStatus.getMessage();
        this.content = content;
    }

    public CustomHttpRequestResponse(int code, String message){
        this.code = code;
        this.message = message;
    }
}
