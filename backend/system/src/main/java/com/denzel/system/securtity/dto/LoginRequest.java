package com.denzel.system.securtity.dto;

import lombok.Data;

/**
 * @creation 19/03/2026 22:34
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.system.securtity.dto
 **/
@Data
public class LoginRequest {
    private String username;
    private String password;
}
