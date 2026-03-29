package com.denzel.system.mapper;

import com.denzel.system.dto.ReservationReponseDTO;
import com.denzel.system.dto.ReservationRequestDTO;
import com.denzel.system.entity.Reservation;
import com.denzel.system.enumeration.ReservationStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @creation 22/03/2026 23:54
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.system.mapper
 **/
@Component
public class ReservationMapper {

    public Reservation toEntity(ReservationRequestDTO reservationRequestDTO) {
        Reservation reservation = new Reservation();
        reservation.setReservationDate(reservationRequestDTO.getReservationDate());
        reservation.setReservationTime(reservationRequestDTO.getReservationTime());
        reservation.setNumberOfPeople(reservationRequestDTO.getNumberOfPeople());
        reservation.setClientFullName(reservationRequestDTO.getClientFullName());
        reservation.setClientComment(reservationRequestDTO.getClientComment());
        reservation.setClientPhone(reservationRequestDTO.getClientPhone());
        reservation.setClientEmail(reservationRequestDTO.getClientEmail());

        reservation.setStatus(ReservationStatus.PENDING.getValue());

        return reservation;

    }

    public ReservationReponseDTO toDto(Reservation reservation) {

        return  ReservationReponseDTO.builder()
                .id(reservation.getId())
                .reservationDate(reservation.getReservationDate())
                .reservationTime(reservation.getReservationTime())
                .numberOfPeople(reservation.getNumberOfPeople())
                .clientFullName(reservation.getClientFullName())
                .clientEmail(reservation.getClientEmail())
                .clientPhone(reservation.getClientPhone())
                .status(reservation.getStatus())
                .clientComment(reservation.getClientComment())
                .build();
    }
}
