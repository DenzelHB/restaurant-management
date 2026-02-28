package com.denzel.entity;

import com.denzel.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.security.Permission;

/**
 * @creation 28/02/2026 01:44
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.entity
 **/
@Entity
@Table(name="menu_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItem extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    private String name;

    private String description;

    private BigDecimal price;

    private Boolean available;


}
