package at.fhv.hotelsoftware.domain.api;

import at.fhv.hotelsoftware.domain.Booking;
import org.springframework.stereotype.Component;


public interface BookingRepository {
    void addToDatabase(Booking booking);
}
