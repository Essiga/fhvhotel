package at.fhv.hotelsoftware.application.api;

import at.fhv.hotelsoftware.domain.model.BookingId;

public interface CheckInService {
    //
    void checkIn(BookingId bookingId);
}
