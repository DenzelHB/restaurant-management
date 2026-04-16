package com.denzel.system.securtity.service;

import com.denzel.system.entity.User;
import com.denzel.system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @creation 11/03/2026 22:04
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.system.securtity.service
 **/
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
         User user = userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("Aucun Utilisateur trouvé avec cet email :" + email));
        return UserDetailsImpl.build(user);
    }
}
