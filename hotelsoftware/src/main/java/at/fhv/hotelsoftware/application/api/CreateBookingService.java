package at.fhv.hotelsoftware.application.api;

import at.fhv.hotelsoftware.domain.model.BookingId;
import at.fhv.hotelsoftware.view.form.BookingForm;

public interface CreateBookingService{

   void createBooking(BookingForm bookingForm);

   BookingId ChangeBookingStatus(BookingId bookingId);

}
