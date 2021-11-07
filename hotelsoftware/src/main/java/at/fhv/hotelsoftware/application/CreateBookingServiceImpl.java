package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.CreateBookingService;
import at.fhv.hotelsoftware.domain.*;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.view.form.BookingForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
    public List<Booking> findAllBookings(){
        return bookingRepository.findAllBookings();
    }

    @Transactional
    public void createBooking(BookingForm bookingForm){

        //TODO: Input validation (later not this sprint)
        Booking booking = Booking.builder().
                withLongId(99L).
                withId(new Id("99")).
                withCustomer(bookingForm.getFname() + " " + bookingForm.getLname()).
                withVoucherCode(new VoucherCode(bookingForm.getVoucherCode())).
                withCancellationDeadLine(null).
                withBookingStatus(BookingStatus.PENDING).
                withFromDate(LocalDate.parse(bookingForm.getFromDate())).
                withToDate(LocalDate.parse(bookingForm.getToDate())).
                withRoomCategory(RoomCategory.SINGLE).
                withRoomCount(bookingForm.getSingleRoomCount()).
                build();

        bookingRepository.addBooking(booking);
    }


}
