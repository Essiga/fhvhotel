package at.fhv.hotelsoftware.domain.api;

import at.fhv.hotelsoftware.domain.Booking;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


public interface BookingRepository {
   // void addToDatabase(Booking booking);

    List<Booking> findAllBookings();
    void addBooking(Booking booking);
    List<Booking> findTodaysCheckIns();
}
