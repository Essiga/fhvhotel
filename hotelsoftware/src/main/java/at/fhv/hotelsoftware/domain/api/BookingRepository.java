package at.fhv.hotelsoftware.domain.api;

import at.fhv.hotelsoftware.domain.Booking;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;


public interface BookingRepository {
   // void addToDatabase(Booking booking);

    List<Booking> findAllBookings();
}
