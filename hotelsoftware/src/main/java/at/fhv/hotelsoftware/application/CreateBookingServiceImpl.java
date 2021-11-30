package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.CreateBookingService;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.model.*;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingStatus;
import at.fhv.hotelsoftware.domain.model.valueobjects.GuestId;
import at.fhv.hotelsoftware.domain.model.valueobjects.VoucherCode;
import at.fhv.hotelsoftware.view.form.BookingForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class CreateBookingServiceImpl implements CreateBookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Transactional
    public void createBooking(BookingForm bookingForm, GuestId guestId){

        Booking booking = Booking.builder().
                                withBookingId(new BookingId(UUID.randomUUID())).
                                withCancellationDeadLine(null).
                withGuestId(guestId).
                                withBookingStatus(BookingStatus.PENDING).
                                withVoucherCode(new VoucherCode(bookingForm.getVoucherCode())).
                                withCheckInDate(LocalDate.parse(bookingForm.getCheckInDate())).
                                withCheckOutDate(LocalDate.parse(bookingForm.getCheckOutDate())).
                                withSingleRoom(bookingForm.getSingleRoomCount()).
                                withDoubleRoom(bookingForm.getDoubleRoomCount()).
                withSuperiorRoom(bookingForm.getSuperiorRoomCount()).
                                build();


        bookingRepository.addBooking(booking);
    }


}
