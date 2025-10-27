package com.lms.LMS.service;

import com.lms.LMS.model.Reservation;
import com.lms.LMS.model.ReservationStatus;
import com.lms.LMS.repo.ReservationRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationRepo reservationRepo;

    public ReservationService(ReservationRepo reservationRepo) {
        this.reservationRepo = reservationRepo;
    }

    // Create or update reservation
    public Reservation saveReservation(Reservation reservation) {
        return reservationRepo.save(reservation);
    }

    // Get all reservations
    public List<Reservation> getAllReservations() {
        return reservationRepo.findAll();
    }

    // Get reservation by ID
    public Optional<Reservation> getReservationById(String id) {
        return reservationRepo.findById(id);
    }

    // Delete reservation
    public boolean deleteReservation(String id) {
        return reservationRepo.deleteById(id);
    }

    // Get reservations by member ID
    public List<Reservation> getReservationsByMemberId(String memberId) {
        return reservationRepo.findByMemberId(memberId);
    }

    // Get reservation by readable item ID
    public Optional<Reservation> getReservationByReadableItemId(String readableItemId) {
        return reservationRepo.findByReadableItemId(readableItemId);
    }

    // Cancel reservation
    public Reservation cancelReservation(String reservationId, ReservationStatus Status) {
        Optional<Reservation> reservationOpt = reservationRepo.findById(reservationId);
        if (reservationOpt.isPresent()) {
            Reservation reservation = reservationOpt.get();
            reservation.setStatus(Status);
            return reservationRepo.save(reservation);
        }
        return null;
    }

    // Get total reservations count
    public long getReservationsCount() {
        return reservationRepo.count();
    }
}