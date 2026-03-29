package com.denzel.system.securtity.config;

import com.denzel.system.securtity.jwt.JwtAuthenticationEntryPoint;
import com.denzel.system.securtity.jwt.TokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

/**
 * @creation 02/02/2026 22:28
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.system.securtity
 **/
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig  {

    private final JwtAuthenticationEntryPoint authenticationErrorHandler;
    private final TokenFilter tokenFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(List.of("http://localhost:4200"));
                    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE","PATCH", "OPTIONS"));
                    config.setAllowedHeaders(List.of("*"));
                    config.setAllowCredentials(true);

                    return config;
                }))                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers(
                                        "/index.html",
                                        "/restaurant/api/v1/reservations/create-reservation",
                                        "/restaurant/api/v1/user/create-user",
                                        "/restaurant/api/v1/auth/login", "/restaurant/api/v1/auth/**",
                        "/**/*.js", "/**/*.html", "/**/*.css",
                        "/**/*.svg", "/**/*.ico", "/**/*.woff",
                        "/**/*.woff2", "/**/*.ttf"
                ).permitAll()
                .anyRequest().authenticated()
                    )
                    .exceptionHandling(ex -> ex
                .authenticationEntryPoint(authenticationErrorHandler)
        )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
                        .httpStrictTransportSecurity(HeadersConfigurer.HstsConfig::disable)
                )
                .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}