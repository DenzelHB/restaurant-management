package com.denzel.system.service;

import com.denzel.base.BaseService;
import com.denzel.system.dto.ReservationReponseDTO;
import com.denzel.system.dto.ReservationRequestDTO;
import com.denzel.system.entity.Reservation;

import java.time.LocalDate;
import java.util.List;

/**
 * @creation 28/02/2026 09:40
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.system.service
 **/
public interface ReservationService extends BaseService<Reservation, Long> {

        ReservationReponseDTO create(ReservationRequestDTO reservation);
        ReservationReponseDTO getById(Long id);

        List<ReservationReponseDTO> getAll();

        ReservationReponseDTO update(Long id, ReservationRequestDTO reservation);

        void deleteById(Long id);

        ReservationReponseDTO updateStatus(Long id, String status);

        List<ReservationReponseDTO> getByStatus(String status);
        List<ReservationReponseDTO> getDate(LocalDate date);

        List<ReservationReponseDTO> getByClientPhone(String phone);



}
