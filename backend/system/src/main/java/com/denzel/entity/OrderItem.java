package com.denzel.entity;

import com.denzel.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.security.Permission;

/**
 * @creation 28/02/2026 01:51
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.entity
 **/
@Entity
@Table(name="order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "menu_item_id")
    private MenuItem menuItem;

    private Integer quantity;

    private BigDecimal price;

}
