package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.CheckInService;
import at.fhv.hotelsoftware.application.api.ConfirmBookingService;
import at.fhv.hotelsoftware.application.dto.RoomDTO;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.api.RoomRepository;
import at.fhv.hotelsoftware.domain.model.Booking;
import at.fhv.hotelsoftware.domain.model.Room;
import at.fhv.hotelsoftware.domain.model.exceptions.BookingNotFoundException;
import at.fhv.hotelsoftware.domain.model.exceptions.NotEnoughRoomsException;
import at.fhv.hotelsoftware.domain.model.valueobjects.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ConfirmBookingServiceTest {

    @Autowired
    private ConfirmBookingService confirmBookingService;

    @MockBean
    private BookingRepository bookingRepository;


    @Test
    void given_bookingId_when_confirmBooking__thenexpectconfirmedbooking() throws BookingNotFoundException {
        BookingId bookingId = new BookingId(UUID.randomUUID());

        Booking booking = Booking.builder().
                bookingId(bookingId).
                guestId(new GuestId()).
                bookingStatus(BookingStatus.PENDING).
                checkInDate(LocalDate.now()).
                checkOutDate(LocalDate.now()).
                singleRoom(1).
                doubleRoom(1).
                superiorRoom(1).
                voucherCode(new VoucherCode("")).
                build();

        Mockito.when(bookingRepository.findBookingById(bookingId)).thenReturn(Optional.of(booking));

        //when
        confirmBookingService.confirmBooking(bookingId);


        //then
        assertEquals(BookingStatus.CONFIRMED, booking.getBookingStatus());
    }

    @Test
    void given_bookingId_when_confirmBooking__thenexpectbookingnotfoundexception() throws BookingNotFoundException {
        BookingId bookingId = new BookingId(UUID.randomUUID());

        Booking booking = Booking.builder().
                bookingId(bookingId).
                guestId(new GuestId()).
                bookingStatus(BookingStatus.PENDING).
                checkInDate(LocalDate.now()).
                checkOutDate(LocalDate.now()).
                singleRoom(1).
                doubleRoom(1).
                superiorRoom(1).
                voucherCode(new VoucherCode("")).
                build();

        Mockito.when(bookingRepository.findBookingById(bookingId)).thenReturn(Optional.of(booking));

        //when ... then
        assertThrows(BookingNotFoundException.class, () -> confirmBookingService.confirmBooking(new BookingId(UUID.randomUUID())));
    }
}
