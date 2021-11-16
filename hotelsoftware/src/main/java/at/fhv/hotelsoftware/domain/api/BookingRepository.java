package at.fhv.hotelsoftware.domain.api;

import at.fhv.hotelsoftware.domain.model.Booking;
import at.fhv.hotelsoftware.domain.model.BookingId;

import java.util.List;
import java.util.Optional;


public interface BookingRepository {
    List<Booking> findTodaysCheckIns();
    List<Booking> findTodaysCheckOuts();
    List<Booking> findAllBookings();



    void addBooking(Booking booking);
    Optional<Booking> findBookingById(BookingId bookingId);

}
