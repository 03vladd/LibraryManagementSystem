package com.lms.LMS.repo;

import com.lms.LMS.model.Reservation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReservationRepo extends BaseRepo<Reservation> {

    @Override
    protected String getId(Reservation entity) {
        return entity.getId();
    }

    // Custom method - Find by member ID
    public List<Reservation> findByMemberId(String memberId) {
        return data.values().stream()
                .filter(reservation -> reservation.getMemberId().equals(memberId))
                .toList();
    }

    // Custom method - Find by readable item ID
    public Optional<Reservation> findByReadableItemId(String readableItemId) {
        return data.values().stream()
                .filter(reservation -> reservation.getReadableItemId().equals(readableItemId))
                .findFirst();
    }
}