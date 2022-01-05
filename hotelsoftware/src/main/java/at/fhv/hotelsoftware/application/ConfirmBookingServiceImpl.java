package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.ConfirmBookingService;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.model.Booking;
import at.fhv.hotelsoftware.domain.model.exceptions.BookingNotFoundException;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Component
public class ConfirmBookingServiceImpl implements ConfirmBookingService {

    @Autowired
    private final BookingRepository bookingRepository;

    public ConfirmBookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    @Transactional
    public void confirmBooking(BookingId bookingId) throws BookingNotFoundException {
        Optional<Booking> booking = bookingRepository.findBookingById(bookingId);

        if (booking.isPresent()) {
            booking.get().confirmBooking();
        } else {
            throw new BookingNotFoundException("BookingComponent with ID: " + bookingId.getBookingId() + " not found");
        }
    }

}
