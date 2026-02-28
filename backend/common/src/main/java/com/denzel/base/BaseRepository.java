package com.denzel.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @creation 27/02/2026 22:47
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.base
 **/
@NoRepositoryBean
public interface BaseRepository <ENTITY extends BaseEntity, ID>  extends JpaRepository<ENTITY, ID> {
}
