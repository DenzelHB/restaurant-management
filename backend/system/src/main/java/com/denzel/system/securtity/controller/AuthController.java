package com.denzel.system.securtity.controller;

/**
 * @creation 19/03/2026 22:27
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.system.securtity.controller
 **/

import com.denzel.exception.handler.CustomHttpRequestResponse;
import com.denzel.system.securtity.constants.RestConstant;
import com.denzel.system.securtity.dto.LoginRequest;
import com.denzel.system.securtity.dto.LoginResponse;
import com.denzel.system.securtity.jwt.TokenProvider;
import com.denzel.system.securtity.service.UserDetailsImpl;
import com.denzel.utils.CustomHttpStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(RestConstant.APPLICATION_NAME + RestConstant.RESOURCE_AUTH)
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @PostMapping(path = "/login")
    public CustomHttpRequestResponse<?> authenticate(@RequestBody LoginRequest loginRequest) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = tokenProvider.generateToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = Objects.requireNonNull(userDetails).getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        var response = LoginResponse.builder()
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .phone(userDetails.getPhone())
                .roles(roles)
                .token(token)
                .build();

        return new CustomHttpRequestResponse<>(CustomHttpStatus.SUCCESS, response);
    }
}
