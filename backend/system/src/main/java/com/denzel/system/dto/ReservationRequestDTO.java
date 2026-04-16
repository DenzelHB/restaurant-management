package com.denzel.system.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @creation 22/03/2026 23:36
 * @Author  AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.system.dto
 *
 **/
@Data
public class ReservationRequestDTO {

    @NotNull
    private LocalDate reservationDate;

    @NotNull
    private LocalTime reservationTime;

    @NotNull @Min(1) @Max(10)
    private Integer numberOfPeople;

    @NotNull
    private String clientFullName;

    private String clientEmail;

    @NotNull
    private String clientPhone;

    private String clientComment;
}
