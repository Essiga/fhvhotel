package at.fhv.hotelsoftware.domain.api;

import at.fhv.hotelsoftware.domain.model.Booking;

import java.util.List;


public interface BookingRepository {
   // void addToDatabase(Booking booking);

    List<Booking> findAllBookings();
    void addBooking(Booking booking);
    List<Booking> findTodaysCheckIns();
    Booking findBooking();
}
