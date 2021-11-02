package at.fhv.hotelsoftware.application.api;

import at.fhv.hotelsoftware.domain.Booking;
import at.fhv.hotelsoftware.domain.api.BookingRepository;

public interface CreateBookingService{

    void createBooking(Booking booking);
}
