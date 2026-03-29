package com.denzel.system.securtity.service;

import com.denzel.system.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @creation 11/03/2026 21:51
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.system.securtity.service
 **/
public class UserDetailsImpl implements UserDetails {

    private Long id;

    @Getter
    private final String firstName;
    @Getter
    private final String lastName;
    @Getter
    private final String email;
    @Getter
    private final String phone;

    private final String password;
    private final Boolean enabled;

    private final Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(User user, Collection<? extends GrantedAuthority> authorities) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.enabled = user.getEnabled();
        this.phone = user.getPhone();
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                                                            .map(role -> new SimpleGrantedAuthority(role.getName()))
                                                            .collect(Collectors.toList());
        return new UserDetailsImpl(user, authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public  String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
