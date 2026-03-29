package com.denzel.system.securtity.constants;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.security.Key;

/**
 * @creation 11/03/2026 21:26
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.system.securtity.constants
 **/
@Configuration
@Data
public class SecurityConstants {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.token-header}")
    private String tokenHeader;

    @Value("${jwt.token-prefix}")
    private String tokenPrefix;

    @Value("${jwt.claim-authorities}")
    private String claimKeyAuthorities;
}
