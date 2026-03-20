package com.denzel.system.service.impl;

import com.denzel.base.BaseRepository;
import com.denzel.base.BaseServiceImpl;
import com.denzel.system.entity.Role;
import com.denzel.system.repository.RoleRepository;
import com.denzel.system.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @creation 28/02/2026 09:41
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.system.service.impl
 **/
@Service
@Transactional
public class RoleServiceImpl extends BaseServiceImpl<Role, Long> implements RoleService {

    private final RoleRepository roleRepository;


    public RoleServiceImpl(BaseRepository<Role, Long> repository , RoleRepository roleRepository) {
        super(repository);
        this.roleRepository = roleRepository;
    }
}
