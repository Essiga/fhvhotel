package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.CreateBookingService;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.api.CustomerRepository;
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

    @Transactional
    public void createBooking(BookingForm bookingForm, CustomerId customerId){

        Booking booking = Booking.builder().
                                withBookingId(new BookingId(UUID.randomUUID())).
                                withCancellationDeadLine(null).
                                withCustomerId(customerId).
                                withBookingStatus(BookingStatus.PENDING).
                                withVoucherCode(new VoucherCode(bookingForm.getVoucherCode())).
                                withCheckInDate(LocalDate.parse(bookingForm.getCheckInDate())).
                                withCheckOutDate(LocalDate.parse(bookingForm.getCheckOutDate())).
                                withSingleRoom(bookingForm.getSingleRoomCount()).
                                withDoubleRoom(bookingForm.getDoubleRoomCount()).
                                withLuxusRoom(bookingForm.getLuxusRoomCount()).
                                build();

        bookingRepository.addBooking(booking);
    }


}
