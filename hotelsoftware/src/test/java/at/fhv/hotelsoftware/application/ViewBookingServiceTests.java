package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.ViewBookingService;
import at.fhv.hotelsoftware.application.dto.BookingDTO;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.model.Booking;
import at.fhv.hotelsoftware.domain.model.exceptions.BookingNotFoundException;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;
import at.fhv.hotelsoftware.domain.model.valueobjects.VoucherCode;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ViewBookingServiceTests {

    @Autowired
    ViewBookingService viewBookingService;

    @MockBean
    BookingRepository bookingRepository;

    @Test
    public void given_booking_when_findbookingbyid_then_returncorrectbooking() throws BookingNotFoundException {
        //given
        BookingId bookingId = new BookingId(UUID.randomUUID());

        Booking booking = Booking.builder().
                bookingId(bookingId).
                voucherCode(new VoucherCode("test")).
                singleRoom(1).
                doubleRoom(1).
                superiorRoom(1).
                build();

        Mockito.when(bookingRepository.findBookingById(booking.getBookingId())).thenReturn(Optional.of(booking));

        //when
        BookingDTO bookingDTO = viewBookingService.findBookingById(bookingId);

        //then
        assertEquals(bookingDTO.getBookingId().getBookingId(), booking.getBookingId().getBookingId());
        assertEquals(bookingDTO.getVoucherCode().getVoucherCode(), booking.getVoucherCode().getVoucherCode());
    }

    @Test
    public void given_novalidbookingid_when_findbookingbyid_then_throwbookingnotfoundexception() {
        //given
        BookingId bookingId = new BookingId(UUID.randomUUID());

        Booking booking = Booking.builder().
                bookingId(bookingId).
                voucherCode(new VoucherCode("test")).
                singleRoom(1).
                doubleRoom(1).
                superiorRoom(1).
                build();

        Mockito.when(bookingRepository.findBookingById(booking.getBookingId())).thenReturn(Optional.of(booking));

        //when...then
        assertThrows(BookingNotFoundException.class, () -> viewBookingService.findBookingById(new BookingId(UUID.randomUUID())));
    }

    @Test
    public void given_bookings_when_findtodayscheckins_then_returntodayscheckins() {
        //given
        LocalDate checkInToday = LocalDate.now();
        LocalDate checkInTomorrow = checkInToday.plusDays(1);
        int expectedListSize = 2;

        List<Booking> bookings = new ArrayList<>();

        Booking booking1 = Booking.builder().
                bookingId(new BookingId(UUID.randomUUID())).
                checkInDate(checkInToday).
                build();

        Booking booking2 = Booking.builder().
                bookingId(new BookingId(UUID.randomUUID())).
                checkInDate(checkInToday).
                build();

        Booking booking3 = Booking.builder().
                bookingId(new BookingId(UUID.randomUUID())).
                checkInDate(checkInTomorrow).
                build();

        bookings.add(booking1);
        bookings.add(booking2);
        bookings.add(booking3);

        Mockito.when(bookingRepository.findTodaysCheckIns()).thenReturn(List.of(booking1, booking2));

        //when
        List<BookingDTO> todaysCheckIns = viewBookingService.findTodaysCheckIns();

        //then
        assertEquals(expectedListSize, todaysCheckIns.size());

        for (int i = 0; i < todaysCheckIns.size(); i++) {
            assertEquals(todaysCheckIns.get(i).getBookingId().getBookingId(), bookings.get(i).getBookingId().getBookingId());
            assertEquals(checkInToday, bookings.get(i).getCheckInDate());
        }
    }

    @Test
    public void given_bookings_when_findtodayscheckouts_then_returntodayscheckouts() {
        //given
        LocalDate checkOutToday = LocalDate.now();
        LocalDate checkOutTomorrow = checkOutToday.plusDays(1);
        int expectedListSize = 2;

        List<Booking> bookings = new ArrayList<>();

        Booking booking1 = Booking.builder().
                bookingId(new BookingId(UUID.randomUUID())).
                checkOutDate(checkOutToday).
                build();

        Booking booking2 = Booking.builder().
                bookingId(new BookingId(UUID.randomUUID())).
                checkOutDate(checkOutToday).
                build();

        Booking booking3 = Booking.builder().
                bookingId(new BookingId(UUID.randomUUID())).
                checkOutDate(checkOutTomorrow).
                build();

        bookings.add(booking1);
        bookings.add(booking2);
        bookings.add(booking3);

        Mockito.when(bookingRepository.findTodaysCheckOuts()).thenReturn(List.of(booking1, booking2));

        //when
        List<BookingDTO> todaysCheckOuts = viewBookingService.findTodaysCheckOuts();

        //then
        assertEquals(expectedListSize, todaysCheckOuts.size());

        for (int i = 0; i < todaysCheckOuts.size(); i++) {
            assertEquals(todaysCheckOuts.get(i).getBookingId().getBookingId(), bookings.get(i).getBookingId().getBookingId());
            assertEquals(checkOutToday, bookings.get(i).getCheckOutDate());
        }
    }
}
