package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.CreateBookingService;
import at.fhv.hotelsoftware.domain.Booking;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.infrastructure.BookingRepositoryImpl;

public class CreateBookingServiceImpl implements CreateBookingService {

    public CreateBookingServiceImpl(){}

    public void createBooking(Booking booking){
        BookingRepository bookingRepository = new BookingRepositoryImpl();
        bookingRepository.addToDatabase(booking);
    }
}
