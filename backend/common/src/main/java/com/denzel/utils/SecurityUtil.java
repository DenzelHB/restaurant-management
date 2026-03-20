package com.denzel.utils;

import com.denzel.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @creation 20/03/2026 00:20
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.utils
 **/
public class SecurityUtil {
    public static UserDetails getCurrentUser() {
        UserDetailsService userDetailsService = null;
        return userDetailsService.loadUserByUsername(getCurrentUsername());
    }

    public static String getCurrentUsername() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new BadRequestException(HttpStatus.UNAUTHORIZED, "L'état de connexion actuel a expiré");
        }
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userDetails.getUsername();
        }
        throw new BadRequestException(HttpStatus.UNAUTHORIZED,
                "Je ne trouve pas les informations de l'utiisateur actuellement connecté");
    }


}
