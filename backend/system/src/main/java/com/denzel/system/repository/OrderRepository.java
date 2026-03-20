package com.denzel.system.repository;

import com.denzel.base.BaseRepository;
import com.denzel.system.entity.Order;
import org.springframework.stereotype.Repository;

/**
 * @creation 28/02/2026 09:30
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.system.repository
 **/
@Repository
public interface OrderRepository extends BaseRepository<Order, Long> {
}
