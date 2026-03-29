package com.denzel.system.dto;

import lombok.Data;

/**
 * @creation 19/03/2026 23:20
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.system.dto
 **/
@Data
public class PasswordUpdateDTO {
    private String oldPassword;
    private String newPassword;
}
