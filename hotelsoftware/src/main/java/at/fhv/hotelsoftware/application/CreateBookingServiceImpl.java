package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.CreateBookingService;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.model.*;
import at.fhv.hotelsoftware.view.form.BookingForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.LinkedList;
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
    public void createBooking(BookingForm bookingForm){

        Room room = Room.builder().
                withRoomCategory(RoomCategory.LUXUS).
                withRoomId(new RoomId(UUID.randomUUID())).
                withRoomNumber(2).
                build();



        //TODO: Input validation (later not this sprint)
        Booking booking = Booking.builder().
                withBookingId(new BookingId(UUID.randomUUID())).
                withCustomer(bookingForm.getFname() + " " + bookingForm.getLname()).
                withVoucherCode(new VoucherCode(bookingForm.getVoucherCode())).
                withCancellationDeadLine(null).
                withBookingStatus(BookingStatus.PENDING).
                withFromDate(LocalDate.parse(bookingForm.getFromDate())).
                withToDate(LocalDate.parse(bookingForm.getToDate())).
                withRoomCategory(RoomCategory.SINGLE).
                withSingleRoom(room).
                build();

        bookingRepository.addBooking(booking);
    }


}
