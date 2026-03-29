package com.denzel.system.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @creation 22/03/2026 23:44
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.system.dto
 **/
@Data
@Builder
public class ReservationReponseDTO {

    private Long id;

    private LocalDate reservationDate;
    private LocalTime reservationTime;
    private Integer numberOfPeople;
    private String status;

    private String clientFullName;
    private String clientEmail;
    private String clientPhone;

    private String clientComment;
}
