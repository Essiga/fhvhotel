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
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@Transactional
public class BookingRepositoryTests {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private BookingRepository bookingRepository;
    //
    @Test
    void given_booking_when_persistedflushedfetched_then_expectallbookings() {
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

        //when
        bookingRepository.addBooking(bookingExpected);
        em.flush();
        List<Booking> allBookings = bookingRepository.findAllBookings();

        //then
        assertEquals(idExpected.getBookingId(), allBookings.get(0).getBookingId().getBookingId());
    }
}
