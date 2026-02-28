package com.denzel.entity;

import com.denzel.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @creation 28/02/2026 01:50
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.entity
 **/
@Entity
@Table(name="orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime orderDate;

    private String orderStatus;

    private String clientFullName;
    private String clientEmail;
    private String clientPhone;

    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();
}
