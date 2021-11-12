package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.model.BookingId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CheckInServiceImpl {

    @Autowired
    private final BookingRepository bookingRepository;

    public CheckInServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Transactional
    public void ChangeBookingStatus(BookingId bookingId) {
        bookingRepository.checkIn(bookingId);
    }
}
 //Booking aufrufen und ändern