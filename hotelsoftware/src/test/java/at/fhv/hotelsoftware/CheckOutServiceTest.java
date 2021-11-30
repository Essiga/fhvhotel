package at.fhv.hotelsoftware;

import at.fhv.hotelsoftware.application.api.CheckOutService;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.api.RoomRepository;
import at.fhv.hotelsoftware.domain.model.*;
import at.fhv.hotelsoftware.domain.model.exceptions.BookingNotFoundException;
import at.fhv.hotelsoftware.domain.model.exceptions.RoomNotFoundException;
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
    void given_rooms_when_rommsinbooking_and_booking_checkout_then_expectroomsbookingidisnull_and_expectroomCategoryis_cleaning_and_bookingstatusis_checkedout() throws BookingNotFoundException, RoomNotFoundException, RoomNotFoundException {
        List<Room> list = new ArrayList<>();
        BookingId bookingId = new BookingId(UUID.randomUUID());

        Booking booking = Booking.builder().withBookingId(bookingId).withCustomerId(new CustomerId()).
                withBookingStatus(BookingStatus.CONFIRMED).withCheckInDate(LocalDate.now()).withCheckOutDate(LocalDate.now()).
                withSingleRoom(1).withDoubleRoom(0).withSuperiorRoom(0).withVoucherCode(new VoucherCode("")).build();

        Room doubleRoom = Room.builder().
                withRoomStatus(RoomStatus.OCCUPIED).
                withBookingId(bookingId).
                withRoomCategory(RoomCategory.DOUBLE).
                withRoomNumber(101).build();

        Room singleRoom = Room.builder().
                withRoomStatus(RoomStatus.OCCUPIED).
                withBookingId(bookingId).
                withRoomCategory(RoomCategory.SINGLE).
                withRoomNumber(102).build();

        Room superiorRoom = Room.builder().
                withRoomStatus(RoomStatus.OCCUPIED).
                withBookingId(bookingId).
                withRoomCategory(RoomCategory.SUPERIOR).
                withRoomNumber(103).build();

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
