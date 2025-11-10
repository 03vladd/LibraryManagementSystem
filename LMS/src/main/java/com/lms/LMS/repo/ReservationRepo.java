package com.lms.LMS.repo;

import com.lms.LMS.model.Reservation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationRepo extends InFileRepo<Reservation> {

    public ReservationRepo(
            @Value("${app.data.directory:src/main/resources/data}") String dataDirectory
    ) {
        super(Reservation.class, "reservations.json", dataDirectory);
    }
}