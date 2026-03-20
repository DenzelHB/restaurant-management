package com.denzel.system.securtity.dto;

import lombok.Builder;

import java.util.List;

/**
 * @creation 19/03/2026 22:36
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.system.securtity.dto
 **/
@Builder
public class LoginResponse {
    Long id;
    String username;
    String email;
    String phone;
    String password;
    boolean status;
    List<String> roles;
    String token;
}
