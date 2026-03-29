package com.denzel.system.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.annotations.AnyKeyJavaClass;

/**
 * @creation 22/03/2026 23:57
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.system.enumeration
 **/
@AllArgsConstructor
@Getter
public enum ReservationStatus {

    PENDING("PENDING"),
    CONFIRMED("CONFIRMED"),
    CANCELLED("CANCELLED");

    private final String value;

}
