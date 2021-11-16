package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.CreateBookingService;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.model.*;
import at.fhv.hotelsoftware.view.form.BookingForm;
import at.fhv.hotelsoftware.view.form.CustomerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
    public void createBooking(BookingForm bookingForm, CustomerForm customerForm){



        //TODO: Input validation (later not this sprint)
        Booking booking = Booking.builder().
                withBookingId(new BookingId(UUID.randomUUID())).
                withCancellationDeadLine(null).
                withCustomer(customerForm.getFname() + " " + customerForm.getLname()).
                withBookingStatus(BookingStatus.PENDING).
                withCheckInDate(LocalDate.parse(bookingForm.getCheckInDate())).
                withCheckOutDate(LocalDate.parse(bookingForm.getCheckOutDate())).
                withRoomCategory(RoomCategory.SINGLE).
                build();

        bookingRepository.addBooking(booking);
    }


}
