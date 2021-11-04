package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.CreateBookingService;
import at.fhv.hotelsoftware.domain.*;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.model.Dummy;
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

    @Transactional
    public void createBooking(Dummy dummy){

        //TODO: convert string date to localDate (cancellationDeadline, fromDate, toDate)
        //TODO: Input validation (later not this sprint)
        Booking booking = Booking.builder().
                withLongId(99L).
                withId(new Id("99")).
                withCustomer(dummy.getFname() + dummy.getLname()).
                withVoucherCode(new VoucherCode(dummy.getVoucherCode())).
                withCancellationDeadLine(null).
                withBookingStatus(BookingStatus.PENDING).
                withFromDate(null).withToDate(null).
                withRoomCategory(RoomCategory.SINGLE).
                withRoomCount(Integer.parseInt(dummy.getSingleRoomCount())).
                build();

        bookingRepository.addBooking(booking);
    }


}
