package com.denzel.system.repository;

import com.denzel.base.BaseRepository;
import com.denzel.system.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @creation 28/02/2026 09:29
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.system.repository
 **/
@Repository
public interface UserRepository extends BaseRepository<User, Long> {
    Optional<User> findByEmail(String username);

    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);
}
