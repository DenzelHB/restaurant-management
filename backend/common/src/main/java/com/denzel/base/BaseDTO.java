package com.denzel.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @creation 27/02/2026 22:48
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.base
 **/
@Data
@SuperBuilder
public class BaseDTO implements Serializable {

    private String createdBy;
    private String updatedBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createdTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updatedTime;
}
