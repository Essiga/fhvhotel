package at.fhv.hotelsoftware.domain.api;

import at.fhv.hotelsoftware.domain.model.Booking;
import at.fhv.hotelsoftware.domain.model.BookingId;

import java.util.List;


public interface BookingRepository {
   // void addToDatabase(Booking booking);

    List<Booking> findAllBookings();
    void addBooking(Booking booking);
    List<Booking> findTodaysCheckIns();
    void checkIn(BookingId bookingId);
    List<Booking> findTodaysCheckOuts();
    Booking findBookingById(String bookingId);
}
