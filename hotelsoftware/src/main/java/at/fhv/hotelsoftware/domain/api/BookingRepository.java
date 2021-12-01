package at.fhv.hotelsoftware.domain.api;

import at.fhv.hotelsoftware.domain.model.Booking;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;

import java.util.List;
import java.util.Optional;

public interface BookingRepository {

    void addBooking(Booking booking);
    List<Booking> findTodaysCheckIns();
    List<Booking> findTodaysCheckOuts();
    Optional<Booking> findBookingById(BookingId bookingId);
}
