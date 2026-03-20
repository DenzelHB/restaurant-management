package com.denzel.system.repository;

import com.denzel.base.BaseRepository;
import com.denzel.system.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @creation 28/02/2026 09:29
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.system.repository
 **/
@Repository
public interface RoleRepository extends BaseRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
