package com.denzel.config;

import com.denzel.utils.SecurityUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @creation 15/07/2025 21:55
 * @project SCHOOL GN
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.hansactech.commonservice.annotation.rest
 **/
@Service(value = "denzel")
public class AuthorityConfig {

    public Boolean check(String ...permissions){
        List<String> denzelPermissions = SecurityUtil.getCurrentUser().getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        return denzelPermissions.contains("admin") || Arrays.stream(permissions).anyMatch(denzelPermissions::contains);
    }
}
