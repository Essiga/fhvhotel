package at.fhv.hotelsoftware.infrastructure;

import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.model.Booking;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingStatus;
import at.fhv.hotelsoftware.domain.model.valueobjects.GuestId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
public class BookingRepositoryTests {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private BookingRepository bookingRepository;

    @Test
    void given_bookings_when_findallbookings_then_expectallbookings() {
        //given
        BookingId idExpected = new BookingId(UUID.randomUUID());
        BookingId idExpected2 = new BookingId(UUID.randomUUID());

        GuestId guestId = new GuestId(UUID.randomUUID());
        GuestId guestId2 = new GuestId(UUID.randomUUID());

        Booking bookingExpected = new Booking().builder().
                bookingId(idExpected).
                bookingStatus(BookingStatus.CHECKEDIN).
                checkInDate(LocalDate.now()).
                checkOutDate(LocalDate.now()).
                singleRoom(1).
                doubleRoom(1).
                superiorRoom(1).
                guestId(guestId).
                build();

        Booking bookingExpected2 = new Booking().builder().
                bookingId(idExpected2).
                bookingStatus(BookingStatus.COMPLETED).
                checkInDate(LocalDate.now()).
                checkOutDate(LocalDate.now()).
                singleRoom(2).
                doubleRoom(2).
                superiorRoom(2).
                guestId(guestId2).
                build();

        bookingRepository.addBooking(bookingExpected);
        bookingRepository.addBooking(bookingExpected2);
        em.flush();

        //when
        List<Booking> bookingsActual = bookingRepository.findAllBookings();

        //then
        boolean bookingExpectedExists = false;
        boolean bookingExpected2Exists = false;

        for (Booking booking : bookingsActual) {
            if (booking.getBookingId().getBookingId().equals(idExpected.getBookingId())) {
                bookingExpectedExists = true;
            }

            if (booking.getBookingId().getBookingId().equals(idExpected2.getBookingId())) {
                bookingExpected2Exists = true;
            }
        }

        assertTrue(bookingExpectedExists);
        assertTrue(bookingExpected2Exists);
    }

    @Test
    void given_booking_when_findbookingbyid_then_expectbookingwithgivenbookingid() {
        //given
        BookingId idExpected = new BookingId(UUID.randomUUID());
        GuestId guestId = new GuestId(UUID.randomUUID());

        Booking bookingExpected = new Booking().builder().
                bookingId(idExpected).
                bookingStatus(BookingStatus.CHECKEDIN).
                checkInDate(LocalDate.now()).
                checkOutDate(LocalDate.now()).
                singleRoom(1).
                doubleRoom(1).
                superiorRoom(1).
                guestId(guestId).
                build();

        bookingRepository.addBooking(bookingExpected);
        em.flush();

        //when
        Optional<Booking> actualBookingOpt = bookingRepository.findBookingById(idExpected);

        //then
        assertTrue(actualBookingOpt.isPresent());
        assertEquals(bookingExpected.getBookingId().getBookingId(), actualBookingOpt.get().getBookingId().getBookingId());
    }

    @Test
    void given_bookings_when_findtodayscheckins_then_expectbookingswithstatusconfirmedandcheckindatetoday() {
        //given
        BookingId idExpected = new BookingId(UUID.randomUUID());
        BookingId idNotExpected = new BookingId(UUID.randomUUID());
        BookingId idNotExpected2 = new BookingId(UUID.randomUUID());

        GuestId guestId = new GuestId(UUID.randomUUID());
        GuestId guestId2 = new GuestId(UUID.randomUUID());
        GuestId guestId3 = new GuestId(UUID.randomUUID());

        Booking bookingExpected = new Booking().builder().
                bookingId(idExpected).
                bookingStatus(BookingStatus.CONFIRMED).
                checkInDate(LocalDate.now()).
                checkOutDate(LocalDate.now().plusDays(2)).
                singleRoom(1).
                doubleRoom(1).
                superiorRoom(1).
                guestId(guestId).
                build();

        Booking bookingNotExpected = new Booking().builder().
                bookingId(idNotExpected).
                bookingStatus(BookingStatus.CHECKEDIN).
                checkInDate(LocalDate.now()).
                checkOutDate(LocalDate.now().plusDays(1)).
                singleRoom(2).
                doubleRoom(2).
                superiorRoom(2).
                guestId(guestId2).
                build();

        Booking bookingNotExpected2 = new Booking().builder().
                bookingId(idNotExpected2).
                bookingStatus(BookingStatus.CONFIRMED).
                checkInDate(LocalDate.now().plusDays(2)).
                checkOutDate(LocalDate.now().plusDays(5)).
                singleRoom(3).
                doubleRoom(3).
                superiorRoom(3).
                guestId(guestId3).
                build();

        bookingRepository.addBooking(bookingExpected);
        bookingRepository.addBooking(bookingNotExpected);
        bookingRepository.addBooking(bookingNotExpected2);
        em.flush();

        //when
        List<Booking> actualCheckIns = bookingRepository.findTodaysCheckIns();

        assertFalse(actualCheckIns.isEmpty());

        boolean expectedBookingExists = false;

        for (Booking booking : actualCheckIns) {
            if (booking.getBookingId().getBookingId().equals(idExpected.getBookingId())) {
                expectedBookingExists = true;
            }
        }

        assertTrue(expectedBookingExists);
    }

    @Test
    void given_bookings_when_findtodayscheckouts_then_expectbookingswithstatuscheckedinandcheckoutdatetoday() {
        //given
        BookingId idExpected = new BookingId(UUID.randomUUID());
        BookingId idNotExpected = new BookingId(UUID.randomUUID());
        BookingId idNotExpected2 = new BookingId(UUID.randomUUID());

        GuestId guestId = new GuestId(UUID.randomUUID());
        GuestId guestId2 = new GuestId(UUID.randomUUID());
        GuestId guestId3 = new GuestId(UUID.randomUUID());

        Booking bookingExpected = new Booking().builder().
                bookingId(idExpected).
                bookingStatus(BookingStatus.CHECKEDIN).
                checkInDate(LocalDate.now().minusDays(5)).
                checkOutDate(LocalDate.now()).
                singleRoom(1).
                doubleRoom(1).
                superiorRoom(1).
                guestId(guestId).
                build();

        Booking bookingNotExpected = new Booking().builder().
                bookingId(idNotExpected).
                bookingStatus(BookingStatus.CONFIRMED).
                checkInDate(LocalDate.now().minusDays(3)).
                checkOutDate(LocalDate.now()).
                singleRoom(2).
                doubleRoom(2).
                superiorRoom(2).
                guestId(guestId2).
                build();

        Booking bookingNotExpected2 = new Booking().builder().
                bookingId(idNotExpected2).
                bookingStatus(BookingStatus.CHECKEDIN).
                checkInDate(LocalDate.now().minusDays(1)).
                checkOutDate(LocalDate.now().plusDays(5)).
                singleRoom(3).
                doubleRoom(3).
                superiorRoom(3).
                guestId(guestId3).
                build();

        bookingRepository.addBooking(bookingExpected);
        bookingRepository.addBooking(bookingNotExpected);
        bookingRepository.addBooking(bookingNotExpected2);
        em.flush();

        //when
        List<Booking> actualCheckOuts = bookingRepository.findTodaysCheckOuts();

        assertFalse(actualCheckOuts.isEmpty());

        boolean expectedBookingExists = false;

        for (Booking booking : actualCheckOuts) {
            if (booking.getBookingId().getBookingId().equals(idExpected.getBookingId())) {
                expectedBookingExists = true;
            }
        }

        assertTrue(expectedBookingExists);
    }

    @Test
    void given_bookings_when_findbookingsbydate_then_returncorrectbookings(){
        //given
        Booking bookingExpected = new Booking().builder().
                bookingStatus(BookingStatus.CHECKEDIN).
                checkInDate(LocalDate.now().minusDays(5)).
                checkOutDate(LocalDate.now()).
                singleRoom(1).
                doubleRoom(1).
                superiorRoom(1).
                build();

        Booking bookingExpected2 = new Booking().builder().
                bookingStatus(BookingStatus.CONFIRMED).
                checkInDate(LocalDate.now().minusDays(3)).
                checkOutDate(LocalDate.now()).
                singleRoom(2).
                doubleRoom(2).
                superiorRoom(2).
                build();

        Booking bookingNotExpected = new Booking().builder().
                bookingStatus(BookingStatus.CHECKEDIN).
                checkInDate(LocalDate.now().plusDays(1)).
                checkOutDate(LocalDate.now().plusDays(5)).
                singleRoom(3).
                doubleRoom(3).
                superiorRoom(3).
                build();

        List<Booking> expectedBookings = new LinkedList<>();
        expectedBookings.add(bookingExpected);
        expectedBookings.add(bookingExpected2);

        bookingRepository.addBooking(bookingExpected);
        bookingRepository.addBooking(bookingExpected2);
        bookingRepository.addBooking(bookingNotExpected);
        em.flush();


        //when
        List<Booking> bookings = bookingRepository.findBookingsByDate(LocalDate.now().minusDays(5), LocalDate.now());

        //then
        assertEquals(expectedBookings.size(), bookings.size());
        assertEquals(expectedBookings, bookings);
    }
}
