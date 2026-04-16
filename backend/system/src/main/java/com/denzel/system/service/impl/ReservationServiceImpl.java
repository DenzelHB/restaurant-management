package com.denzel.system.service.impl;

import com.denzel.base.BaseService;
import com.denzel.base.BaseServiceImpl;
import com.denzel.system.dto.ReservationReponseDTO;
import com.denzel.system.dto.ReservationRequestDTO;
import com.denzel.system.entity.Reservation;
import com.denzel.system.enumeration.ReservationStatus;
import com.denzel.system.mapper.ReservationMapper;
import com.denzel.system.repository.ReservationRepository;
import com.denzel.system.service.ReservationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @creation 28/02/2026 09:41
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.system.service.impl
 **/
@Service
public class ReservationServiceImpl extends BaseServiceImpl<Reservation, Long> implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    public ReservationServiceImpl(ReservationRepository reservationRepository, ReservationMapper reservationMapper) {
        super(reservationRepository);
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
    }

    @Override
    public ReservationReponseDTO create(ReservationRequestDTO reservationRequestDTO) {

        Reservation reservation = reservationMapper.toEntity(reservationRequestDTO);

        return reservationMapper.toDto(reservationRepository.save(reservation));
    }

    @Override
    public ReservationReponseDTO getById(Long id) {
        return reservationRepository.findById(id).map(reservationMapper::toDto).orElseThrow(()->new EntityNotFoundException("Reservation not found"));
    }

    @Override
    public List<ReservationReponseDTO> getAll() {
        return reservationRepository.findAll().stream().map(reservationMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ReservationReponseDTO update(Long id, ReservationRequestDTO reservation) {
        Reservation reservationToUpdate = reservationRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Reservation not found"));
        reservationToUpdate.setReservationDate(reservation.getReservationDate());
        reservationToUpdate.setReservationTime(reservation.getReservationTime());
        reservationToUpdate.setNumberOfPeople(reservation.getNumberOfPeople());
        reservationToUpdate.setClientFullName(reservation.getClientFullName());
        reservationToUpdate.setClientPhone(reservation.getClientPhone());
        reservationToUpdate.setClientEmail(reservation.getClientEmail());
        reservationToUpdate.setClientComment(reservation.getClientComment());

        return reservationMapper.toDto(reservationRepository.save(reservationToUpdate));
    }

    @Override
    public ReservationReponseDTO updateStatus(Long id, String status) {
        ReservationStatus reservationStatus ;
        try{
            reservationStatus = ReservationStatus.valueOf(status);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Invalid reservation status");
        }
        Reservation reservationToUpdate = reservationRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Reservation not found"));
        reservationToUpdate.setStatus(reservationStatus.getValue());

        return reservationMapper.toDto(reservationRepository.save(reservationToUpdate));
    }

    @Override
    public List<ReservationReponseDTO> getByStatus(String status) {
        return reservationRepository.findByStatus(status).stream().map(reservationMapper::toDto).toList();
    }

    @Override
    public List<ReservationReponseDTO> getDate(LocalDate date) {
        return reservationRepository.findByReservationDate(date).stream().map(reservationMapper::toDto).toList();
    }

    @Override
    public List<ReservationReponseDTO> getByClientPhone(String phone) {
        return reservationRepository.findAll().stream().map(reservationMapper::toDto).toList();
    }
}