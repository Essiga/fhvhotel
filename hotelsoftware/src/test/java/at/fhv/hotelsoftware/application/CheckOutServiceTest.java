package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.CheckOutService;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.api.RoomRepository;
import at.fhv.hotelsoftware.domain.model.*;
import at.fhv.hotelsoftware.domain.model.exceptions.BookingNotFoundException;
import at.fhv.hotelsoftware.domain.model.exceptions.RoomNotFoundException;
import at.fhv.hotelsoftware.domain.model.exceptions.RoomNotOccupiedException;
import at.fhv.hotelsoftware.domain.model.valueobjects.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.*;

@SpringBootTest
public class CheckOutServiceTest {
    @Autowired
    private CheckOutService checkOutService;

    @MockBean
    private RoomRepository roomRepository;

    @MockBean
    private BookingRepository bookingRepository;


    @Test
    void given_rooms_when_rommsinbooking_and_booking_checkout_then_expectroomsbookingidisnull_and_expectroomCategoryis_cleaning_and_bookingstatusis_checkedout() throws BookingNotFoundException, RoomNotFoundException, RoomNotFoundException, RoomNotOccupiedException {
        List<Room> list = new ArrayList<>();
        BookingId bookingId = new BookingId(UUID.randomUUID());

        Booking booking = Booking.builder().
                bookingId(bookingId).
                guestId(new GuestId()).
                bookingStatus(BookingStatus.CONFIRMED).
                checkInDate(LocalDate.now()).
                checkOutDate(LocalDate.now()).
                singleRoom(1).
                doubleRoom(0).
                superiorRoom(0).
                voucherCode(new VoucherCode("")).
                build();

        Room doubleRoom = Room.builder().
                roomStatus(RoomStatus.OCCUPIED).
                bookingId(bookingId).
                roomCategory(RoomCategory.DOUBLE).
                roomNumber(101).build();

        Room singleRoom = Room.builder().
                roomStatus(RoomStatus.OCCUPIED).
                bookingId(bookingId).
                roomCategory(RoomCategory.SINGLE).
                roomNumber(102).build();

        Room superiorRoom = Room.builder().
                roomStatus(RoomStatus.OCCUPIED).
                bookingId(bookingId).
                roomCategory(RoomCategory.SUPERIOR).
                roomNumber(103).build();

        list.add(singleRoom);
        list.add(superiorRoom);
        list.add(doubleRoom);

        Mockito.when(roomRepository.findRoomsByBookingId(bookingId)).thenReturn(list);
        Mockito.when(bookingRepository.findBookingById(bookingId)).thenReturn(Optional.ofNullable(booking));

        //when
        checkOutService.checkOut(bookingId);

        //then
        assertEquals(booking.getBookingStatus(), BookingStatus.COMPLETED);

        for (int i = 0; i < list.size(); i++) {
            assertEquals(list.get(i).getBookingId(), null);
            assertEquals(list.get(i).getRoomStatus(), RoomStatus.CLEANING);
        }


        assertDoesNotThrow(() -> checkOutService.checkOut(bookingId));
        assertThrows((BookingNotFoundException.class), (() -> checkOutService.checkOut(null)), "Booking not found");
    }
}
