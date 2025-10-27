package com.lms.LMS.repo;

import com.lms.LMS.model.Reservation;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ReservationRepo {
    private final Map<String, Reservation> reservations = new HashMap<>();

    public Reservation save(Reservation reservation) {
        reservations.put(reservation.getId(), reservation);
        return reservation;
    }

    public List<Reservation> findAll() {
        return new ArrayList<>(reservations.values());
    }

    public Optional<Reservation> findById(String id) {
        return Optional.ofNullable(reservations.get(id));
    }

    public boolean deleteById(String id) {
        return reservations.remove(id) != null;
    }

    public boolean existsById(String id) {
        return reservations.containsKey(id);
    }

    public long count() {
        return reservations.size();
    }

    // Find by member ID
    public List<Reservation> findByMemberId(String memberId) {
        return reservations.values().stream()
                .filter(reservation -> reservation.getMemberId().equals(memberId))
                .toList();
    }

    // Find by readable item ID
    public Optional<Reservation> findByReadableItemId(String readableItemId) {
        return reservations.values().stream()
                .filter(reservation -> reservation.getReadableItemId().equals(readableItemId))
                .findFirst();
    }
}