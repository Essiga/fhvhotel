package at.fhv.hotelsoftware.application.api;

import at.fhv.hotelsoftware.domain.Booking;
import at.fhv.hotelsoftware.domain.api.BookingRepository;

import java.util.List;
import java.util.Optional;

public interface CreateBookingService{

   // void createBooking(Booking booking);
   Optional<List<Booking>> findAllBookings();
}
