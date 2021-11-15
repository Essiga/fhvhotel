package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.CheckInService;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.model.Booking;
import at.fhv.hotelsoftware.domain.model.BookingId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CheckInServiceImpl implements CheckInService {

    @Autowired
    private final BookingRepository bookingRepository;

    public CheckInServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    @Transactional
    public void checkIn(BookingId bookingId) {
        Booking booking = bookingRepository.findBookingById(bookingId);
        booking.checkIn();
    }
}
 //Booking aufrufen und ändern