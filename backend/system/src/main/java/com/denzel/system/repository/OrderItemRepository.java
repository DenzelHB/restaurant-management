package com.denzel.system.repository;

import com.denzel.base.BaseRepository;
import com.denzel.system.entity.OrderItem;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @creation 28/02/2026 09:30
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.system.repository
 **/
@Repository
public interface OrderItemRepository extends BaseRepository<OrderItem, Long> {
    List<OrderItem> findByOrderId(Long MenuId);
}
