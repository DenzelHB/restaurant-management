package com.denzel.system.securtity.jwt;

import com.denzel.exception.handler.CustomHttpRequestResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @creation 11/03/2026 21:28
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.system.securtity.jwt
 **/
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        int code = HttpStatus.UNAUTHORIZED.value();
        response.setStatus(code);
        response.setContentType("application/json;charset=UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(new CustomHttpRequestResponse<>(HttpStatus.UNAUTHORIZED.value(), "Your login status has expired, please log in again"));
        response.getWriter().write(jsonResponse);
    }
}
