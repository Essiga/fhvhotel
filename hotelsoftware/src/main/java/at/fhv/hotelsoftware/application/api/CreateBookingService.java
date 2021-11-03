package at.fhv.hotelsoftware.application.api;

import at.fhv.hotelsoftware.domain.Booking;
import at.fhv.hotelsoftware.domain.api.BookingRepository;

import java.util.List;

public interface CreateBookingService{

   // void createBooking(Booking booking);
   public List<Booking> findAllBookings();
}
