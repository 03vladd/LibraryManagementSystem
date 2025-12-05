package com.lms.LMS.service;

import com.lms.LMS.model.Reservation;
import com.lms.LMS.model.ReservationStatus;
import com.lms.LMS.repo.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    public List<Reservation> getReservationsByMemberId(Long memberId) {
        return reservationRepository.findAll().stream()
                .filter(r -> r.getLoan().getMember().getId().equals(memberId))
                .toList();
    }

    public Optional<Reservation> getReservationByReadableItemId(Long readableItemId) {
        return reservationRepository.findAll().stream()
                .filter(r -> r.getReadableItem().getId().equals(readableItemId))
                .findFirst();
    }

    public Reservation cancelReservation(Long reservationId, ReservationStatus status) {
        Optional<Reservation> reservationOpt = reservationRepository.findById(reservationId);
        if (reservationOpt.isPresent()) {
            Reservation reservation = reservationOpt.get();
            reservation.setStatus(status);
            return reservationRepository.save(reservation);
        }
        return null;
    }

    public long getReservationsCount() {
        return reservationRepository.count();
    }
}