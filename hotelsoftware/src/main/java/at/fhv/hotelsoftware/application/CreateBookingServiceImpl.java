package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.CreateBookingService;
import at.fhv.hotelsoftware.domain.Booking;
import at.fhv.hotelsoftware.domain.Id;
import at.fhv.hotelsoftware.domain.VoucherCode;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.infrastructure.BookingRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class CreateBookingServiceImpl implements CreateBookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public CreateBookingServiceImpl(BookingRepository bookingRepository){
        this.bookingRepository = bookingRepository;
    }

    @Transactional
    public Optional<List<Booking>> findAllBookings(){
        return bookingRepository.findAllBookings();
    }

    public void createBooking(){

        Booking booking = Booking.builder().
                withLongId(99L).
                withId(new Id("99")).
                withCustomer("Test").
                withVoucherCode(new VoucherCode("voucherCode")).
                build();

        bookingRepository.addBooking(booking);
    }
}
