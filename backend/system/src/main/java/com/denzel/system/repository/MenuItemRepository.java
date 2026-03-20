package com.denzel.system.repository;

import com.denzel.base.BaseRepository;
import com.denzel.system.entity.MenuItem;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @creation 28/02/2026 09:30
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.system.repository
 **/
@Repository
public interface MenuItemRepository extends BaseRepository<MenuItem, Long> {

    List<MenuItem> findMenuItemById(Long id);
}
