package at.fhv.hotelsoftware;

import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.api.RoomRepository;
import at.fhv.hotelsoftware.domain.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import java.lang.Object;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

public class CheckoutServiceTest {
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    RoomRepository roomRepository;

    @Test
    public void testBookingStatus(BookingId bookingId){
        Optional<Booking> actual = bookingRepository.findBookingById(bookingId);

        assertEquals(BookingStatus.COMPLETED, actual);
    }

    @Test
    public void testRoomStatus(BookingId bookingId){
        List<Room> actual = roomRepository.findRoomByBookingId(bookingId);

        for (int i = 0; i < actual.size(); i++) {
            actual.get(i).setRoomStatus(RoomStatus.CLEANING);
            actual.get(i).setBookingId(null);

            assertEquals(RoomStatus.CLEANING, actual.get(i).getRoomStatus());
            assertEquals(null, actual.get(i).getBookingId());
        }
    }
}
