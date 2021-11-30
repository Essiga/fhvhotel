package at.fhv.hotelsoftware.application.api;

import at.fhv.hotelsoftware.domain.model.GuestId;
import at.fhv.hotelsoftware.view.form.BookingForm;

public interface CreateBookingService{

   void createBooking(BookingForm bookingForm, GuestId guestId);
}
