package at.fhv.hotelsoftware.application.api;

import at.fhv.hotelsoftware.domain.Booking;

import java.util.List;
import java.util.Optional;

public interface CheckInService{
    Optional<List<Booking>> findTodaysCheckIns();
}
