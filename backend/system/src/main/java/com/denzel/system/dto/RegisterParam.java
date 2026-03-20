package com.denzel.system.dto;

import lombok.Data;

/**
 * @creation 19/03/2026 23:21
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.system.dto
 **/
@Data
public class RegisterParam {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
}
