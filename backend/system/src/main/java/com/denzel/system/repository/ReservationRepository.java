package com.denzel.system.repository;

import com.denzel.base.BaseRepository;
import com.denzel.system.entity.Reservation;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * @creation 28/02/2026 09:31
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.system.repository
 **/
@Repository
public interface ReservationRepository extends BaseRepository<Reservation, Long> {

    List<Reservation> findByStatus(String status);

    List<Reservation> findByReservationDate(LocalDate reservationDate);

    List<Reservation> findByClientPhone(String clientPhone);
    
}
