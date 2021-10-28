package at.fhv.hotelsoftware.domain.api;

import at.fhv.hotelsoftware.domain.Booking;

public interface BookingRepository {
    void addToDatabase(Booking booking);
}
