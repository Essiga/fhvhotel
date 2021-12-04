package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.CheckInService;
import at.fhv.hotelsoftware.application.dto.RoomDTO;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.api.RoomRepository;
import at.fhv.hotelsoftware.domain.model.Booking;
import at.fhv.hotelsoftware.domain.model.Room;
import at.fhv.hotelsoftware.domain.model.exceptions.*;
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

@SpringBootTest
public class CheckInServiceTest {

    @Autowired
    private CheckInService checkInService;

    @MockBean
    private RoomRepository roomRepository;

    @MockBean
    private BookingRepository bookingRepository;


    @Test
    void given_bookingId_when_freeRoomsavailable_thenexpectFreeRoomsinBooking() throws NotEnoughRoomsException, BookingNotFoundException {
        BookingId bookingId = new BookingId(UUID.randomUUID());
        Booking booking = Booking.builder().
                bookingId(bookingId).
                guestId(new GuestId()).
                bookingStatus(BookingStatus.CONFIRMED).
                checkInDate(LocalDate.now()).
                checkOutDate(LocalDate.now()).
                singleRoom(1).
                doubleRoom(1).
                superiorRoom(1).
                voucherCode(new VoucherCode("")).
                build();

        //when
            checkInService.findFreeRoomsForBooking(bookingId);

         //then
        for (int i = 0; i < list.size(); i++) {
            assertEquals(list.get(i).getBookingId(), bookingId);
            assertEquals(list.get(i).getRoomStatus(), RoomStatus.OCCUPIED);
        }

    }

    @Test
    void given_roomsandbookingId_when_then_roomStatusis_occupied_and_bookingStatusis_checkedIn() throws RoomNotFoundException, RoomAlreadyOccupiedException, BookingNotFoundException, NotEnoughRoomsException {

        // given
        List<Room> list = new ArrayList<>();
        BookingId bookingId = new BookingId(UUID.randomUUID());

        Booking booking = Booking.builder().
                bookingId(bookingId).
                guestId(new GuestId()).
                bookingStatus(BookingStatus.CONFIRMED).
                checkInDate(LocalDate.now()).
                checkOutDate(LocalDate.now()).
                singleRoom(1).
                doubleRoom(1).
                superiorRoom(1).
                voucherCode(new VoucherCode("")).
                build();

        List<RoomDTO> roomList = checkInService.findFreeRoomsForBooking(bookingId);

        Mockito.when(roomRepository.findRoomsByBookingId(bookingId)).thenReturn(list);
        Mockito.when(bookingRepository.findBookingById(bookingId)).thenReturn(Optional.ofNullable(booking));


        //when
        checkInService.checkIn(bookingId, roomList);


        //then
        assertEquals(booking.getBookingStatus(), BookingStatus.CHECKEDIN);

        for (int i = 0; i < list.size(); i++) {
            assertEquals(list.get(i).getBookingId(), bookingId);
            assertEquals(list.get(i).getRoomStatus(), RoomStatus.OCCUPIED);
        }

    }
}

