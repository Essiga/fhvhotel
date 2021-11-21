package at.fhv.hotelsoftware.application.api;

import at.fhv.hotelsoftware.domain.model.BookingId;
import at.fhv.hotelsoftware.view.form.BookingForm;
import at.fhv.hotelsoftware.view.form.CustomerForm;

public interface CreateBookingService{

   void createBooking(BookingForm bookingForm, CustomerForm customerForm);
}
