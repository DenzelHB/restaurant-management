package com.denzel.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @creation 27/02/2026 23:29
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.utils
 **/
@AllArgsConstructor
@Getter
public enum CustomHttpStatus {
    SUCCESS(200, "Sucess!");
    private int code;
    private String message;

}
