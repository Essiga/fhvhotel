package at.fhv.hotelsoftware.application.api;

import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;
import at.fhv.hotelsoftware.domain.model.valueobjects.GuestId;
import at.fhv.hotelsoftware.view.form.BookingForm;

public interface CreateBookingService{

   BookingId createBooking(BookingForm bookingForm, GuestId guestId);
}
