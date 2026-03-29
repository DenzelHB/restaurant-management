package com.denzel.system.controller;

import com.denzel.base.BaseController;
import com.denzel.exception.handler.CustomHttpRequestResponse;
import com.denzel.system.dto.ReservationReponseDTO;
import com.denzel.system.dto.ReservationRequestDTO;
import com.denzel.system.entity.Reservation;
import com.denzel.system.securtity.constants.RestConstant;
import com.denzel.system.service.ReservationService;
import com.denzel.utils.CustomHttpStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @creation 23/03/2026 01:16
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.system.controller
 **/
@RestController
@RequestMapping(RestConstant.APPLICATION_NAME + RestConstant.RESOURCE_RESERVATION)
public class ReservationController extends BaseController<Reservation, Long> {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        super(reservationService);
        this.reservationService = reservationService;
    }

    @GetMapping("/filter/phone")
    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    public CustomHttpRequestResponse<?> getByClientPhone(@RequestParam String phone) {

        return new CustomHttpRequestResponse<>(CustomHttpStatus.SUCCESS, reservationService.getByClientPhone(phone));

    }

    @GetMapping("/filter/date")
    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    public CustomHttpRequestResponse<?> getDate(@RequestParam LocalDate date) {

        return new CustomHttpRequestResponse<>(CustomHttpStatus.SUCCESS, reservationService.getDate(date));

    }

    @GetMapping("/filter/status")
    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    public CustomHttpRequestResponse<?> getByStatus(@RequestParam String status) {
        return new CustomHttpRequestResponse<>(CustomHttpStatus.SUCCESS, reservationService.getByStatus(status));

    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    public CustomHttpRequestResponse<?> updateStatus(@PathVariable Long id,@RequestParam String status) {
        return new CustomHttpRequestResponse<>(CustomHttpStatus.SUCCESS, reservationService.updateStatus(id, status));

    }
    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    @PutMapping("/{idReservation}")
    public CustomHttpRequestResponse<?> update(
           @PathVariable Long idReservation,@RequestBody ReservationRequestDTO reservation) {
        return new CustomHttpRequestResponse<>(CustomHttpStatus.SUCCESS, reservationService.update(idReservation, reservation));

    }

    @GetMapping("/getAll-reservation")
    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    public CustomHttpRequestResponse<?> getAll() {
        return new CustomHttpRequestResponse<>(CustomHttpStatus.SUCCESS, reservationService.getAll());

    }

    @GetMapping("/{idReservation}")
    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    public CustomHttpRequestResponse<?> getById(@PathVariable Long idReservation) {
        return new CustomHttpRequestResponse<>(CustomHttpStatus.SUCCESS,reservationService.getById(idReservation));
    }

    @PostMapping("/create-reservation")
    public CustomHttpRequestResponse<?> create(@RequestBody ReservationRequestDTO reservation) {
        return new CustomHttpRequestResponse<>(CustomHttpStatus.SUCCESS, reservationService.create(reservation));

    }

    @Override
    public CustomHttpRequestResponse<?> findAll(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
