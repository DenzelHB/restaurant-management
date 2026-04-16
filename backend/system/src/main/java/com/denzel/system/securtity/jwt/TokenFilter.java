package com.denzel.system.securtity.jwt;

import com.denzel.system.securtity.constants.SecurityConstants;
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
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


/**
 * @creation 11/03/2026 21:33
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.system.securtity.jwt
 **/
@Component
public class TokenFilter extends OncePerRequestFilter {
    private final UserDetailsServiceImpl userDetailsService;;
    private final TokenProvider tokenProvider;
    private final SecurityConstants securityConstants;

    public TokenFilter(UserDetailsServiceImpl userDetailsService, TokenProvider tokenProvider, SecurityConstants securityConstants) {
        this.userDetailsService = userDetailsService;
        this.tokenProvider = tokenProvider;
        this.securityConstants = securityConstants;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(securityConstants.getTokenHeader());
        String username = null;
        String authToken = null;

        if (header != null && header.startsWith(securityConstants.getTokenPrefix())) {
            authToken = header.replace(securityConstants.getTokenPrefix(), "");

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
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
