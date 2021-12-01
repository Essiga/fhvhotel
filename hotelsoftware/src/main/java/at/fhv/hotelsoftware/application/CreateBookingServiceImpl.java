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
    public void createBooking(BookingForm bookingForm, GuestId guestId) {

        Booking booking = Booking.builder().
                                bookingId(new BookingId(UUID.randomUUID())).
                                cancellationDeadLine(null).
                                guestId(guestId).
                                bookingStatus(BookingStatus.PENDING).
                                voucherCode(new VoucherCode(bookingForm.getVoucherCode())).
                                checkInDate(LocalDate.parse(bookingForm.getCheckInDate())).
                                checkOutDate(LocalDate.parse(bookingForm.getCheckOutDate())).
                                singleRoom(bookingForm.getSingleRoomCount()).
                                doubleRoom(bookingForm.getDoubleRoomCount()).
                                superiorRoom(bookingForm.getSuperiorRoomCount()).
                                build();

        bookingRepository.addBooking(booking);
    }
}
