package at.fhv.hotelsoftware.application.api;

import at.fhv.hotelsoftware.domain.Booking;
import at.fhv.hotelsoftware.view.form.BookingForm;

import java.util.List;
import java.util.Optional;

public interface CreateBookingService{

   void createBooking(BookingForm bookingForm);
   Optional<List<Booking>> findAllBookings();
}
