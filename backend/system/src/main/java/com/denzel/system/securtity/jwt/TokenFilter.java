package com.denzel.system.securtity.jwt;

import com.denzel.system.securtity.service.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.denzel.system.securtity.constants.SecurityConstants.TOKEN_HEADER;
import static com.denzel.system.securtity.constants.SecurityConstants.TOKEN_PREFIX;

/**
 * @creation 11/03/2026 21:33
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.system.securtity.jwt
 **/
@Component
public class TokenFilter extends OncePerRequestFilter {
    private final UserDetailsServiceImpl userDetailsService;;
    private final TokenProvider tokenProvider;

    public TokenFilter(UserDetailsServiceImpl userDetailsService, TokenProvider tokenProvider) {
        this.userDetailsService = userDetailsService;
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(TOKEN_HEADER);
        String username = null;
        String authToken = null;

        if (header != null && header.startsWith(TOKEN_PREFIX)) {
            authToken = header.replace(TOKEN_PREFIX, "");

            try {
                username = tokenProvider.getUsernameFromToken(authToken);
            } catch (IllegalArgumentException e) {
                 logger.error("Erreur survenu lors de la recuperation du username à partir du token", e);
            } catch (ExpiredJwtException e) {
                logger.warn("Le token a expiré et n'est plus valide", e);
            } catch(SignatureException e){
                logger.error("Authentification echoué, Erreur du username ou password.");
            }
        }else {
            logger.warn("erreur survenu dans le hearder");
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (tokenProvider.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication =  tokenProvider.getAuthentication(authToken,SecurityContextHolder.getContext().getAuthentication(), userDetails);
            }
        }
        filterChain.doFilter(request, response);

    }
}
