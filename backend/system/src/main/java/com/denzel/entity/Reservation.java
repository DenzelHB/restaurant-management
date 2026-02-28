package com.denzel.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @creation 28/02/2026 02:00
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.entity
 **/
@Entity
@Table(name="reservation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate reservationDate;
    private LocalTime reservationTime;
    private Integer numberOfPeople;
    private String status;

    private String ClientFullName;
    private String ClientEmail;
    private String ClientPhone;

    private String clientComment;
}
