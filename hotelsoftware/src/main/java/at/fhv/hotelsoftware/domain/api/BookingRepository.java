package at.fhv.hotelsoftware.domain.api;

import at.fhv.hotelsoftware.domain.model.Booking;
import at.fhv.hotelsoftware.domain.model.BookingId;

import java.util.List;


public interface BookingRepository {
    List<Booking> findTodaysCheckIns();
    List<Booking> findTodaysCheckOuts();
    List<Booking> findAllBookings();



    void addBooking(Booking booking);
    Booking findBookingById(BookingId bookingId);


}
