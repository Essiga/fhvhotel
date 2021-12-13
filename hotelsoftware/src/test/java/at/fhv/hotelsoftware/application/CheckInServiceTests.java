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
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CheckInServiceTests {

    @Autowired
    private CheckInService checkInService;

    @MockBean
    private RoomRepository roomRepository;

    @MockBean
    private BookingRepository bookingRepository;


    @Test
    void given_bookingId_when_findfreeroomsforbooking_thenexpectfreeroomsforbooking() throws NotEnoughRoomsException, BookingNotFoundException {
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

        Room singleRoom = Room.builder().
                roomStatus(RoomStatus.FREE).
                bookingId(bookingId).
                roomCategory(RoomCategory.SINGLE).
                roomNumber(101).build();

        Room doubleRoom = Room.builder().
                roomStatus(RoomStatus.FREE).
                bookingId(bookingId).
                roomCategory(RoomCategory.DOUBLE).
                roomNumber(102).build();

        Room superiorRoom = Room.builder().
                roomStatus(RoomStatus.FREE).
                bookingId(bookingId).
                roomCategory(RoomCategory.SUPERIOR).
                roomNumber(103).build();

        list.add(singleRoom);
        list.add(doubleRoom);
        list.add(superiorRoom);

        Mockito.when(bookingRepository.findBookingById(bookingId)).thenReturn(Optional.of(booking));
        Mockito.when(roomRepository.findAllFreeRooms()).thenReturn(list);


        //when
        List<RoomDTO> rooms = checkInService.findFreeRoomsForBooking(bookingId);


        //then
        for (int i = 0; i < list.size(); i++) {
            assertEquals(rooms.get(i).getRoomNumber(), list.get(i).getRoomNumber());
        }
    }


    @Test
    void given_bookingId_when_findfreeroomsforbooking_thenexpectthrowsnotenoughroomsexception() throws NotEnoughRoomsException, BookingNotFoundException {
        List<Room> list = new ArrayList<>();
        BookingId bookingId = new BookingId(UUID.randomUUID());

        Booking booking = Booking.builder().
                bookingId(bookingId).
                guestId(new GuestId()).
                bookingStatus(BookingStatus.CONFIRMED).
                checkInDate(LocalDate.now()).
                checkOutDate(LocalDate.now()).
                singleRoom(2).
                doubleRoom(1).
                superiorRoom(1).
                voucherCode(new VoucherCode("")).
                build();

        Room singleRoom = Room.builder().
                roomStatus(RoomStatus.FREE).
                bookingId(bookingId).
                roomCategory(RoomCategory.SINGLE).
                roomNumber(101).build();

        Room doubleRoom = Room.builder().
                roomStatus(RoomStatus.FREE).
                bookingId(bookingId).
                roomCategory(RoomCategory.DOUBLE).
                roomNumber(102).build();

        Room superiorRoom = Room.builder().
                roomStatus(RoomStatus.FREE).
                bookingId(bookingId).
                roomCategory(RoomCategory.SUPERIOR).
                roomNumber(103).build();

        list.add(singleRoom);
        list.add(doubleRoom);
        list.add(superiorRoom);

        Mockito.when(bookingRepository.findBookingById(bookingId)).thenReturn(Optional.of(booking));
        Mockito.when(roomRepository.findAllFreeRooms()).thenReturn(list);


        // when ... then
        assertThrows(NotEnoughRoomsException.class, () -> checkInService.findFreeRoomsForBooking(bookingId));
    }


    @Test
    void given_bookingId_when_findfreeroomsforbooking_thenexpectthrowsbookingnotfoundexception() throws NotEnoughRoomsException, BookingNotFoundException {
        List<Room> list = new ArrayList<>();
        BookingId bookingId = new BookingId(UUID.randomUUID());

        Booking booking = Booking.builder().
                bookingId(bookingId).
                guestId(new GuestId()).
                bookingStatus(BookingStatus.CONFIRMED).
                checkInDate(LocalDate.now()).
                checkOutDate(LocalDate.now()).
                singleRoom(2).
                doubleRoom(1).
                superiorRoom(1).
                voucherCode(new VoucherCode("")).
                build();

        Room singleRoom = Room.builder().
                roomStatus(RoomStatus.FREE).
                bookingId(bookingId).
                roomCategory(RoomCategory.SINGLE).
                roomNumber(101).build();

        Room doubleRoom = Room.builder().
                roomStatus(RoomStatus.FREE).
                bookingId(bookingId).
                roomCategory(RoomCategory.DOUBLE).
                roomNumber(102).build();

        Room superiorRoom = Room.builder().
                roomStatus(RoomStatus.FREE).
                bookingId(bookingId).
                roomCategory(RoomCategory.SUPERIOR).
                roomNumber(103).build();

        list.add(singleRoom);
        list.add(doubleRoom);
        list.add(superiorRoom);

        Mockito.when(bookingRepository.findBookingById(bookingId)).thenReturn(Optional.of(booking));
        Mockito.when(roomRepository.findAllFreeRooms()).thenReturn(list);


        // when ... then
        assertThrows(BookingNotFoundException.class, () -> checkInService.findFreeRoomsForBooking(new BookingId(UUID.randomUUID())));
    }


    @Test
    void given_roomsandbookingId_when_checkIn_then_roomstatusis_occupied_and_bookingstatusis_checkedIn() throws RoomNotFoundException, RoomAlreadyOccupiedException, BookingNotFoundException, NotEnoughRoomsException {
        List<RoomDTO> list = new ArrayList<>();
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

        RoomDTO singleRoom = RoomDTO.builder().
                roomstatus(RoomStatus.FREE).
                bookingId(bookingId).
                roomCategory(RoomCategory.SINGLE).
                roomNumber(101).build();

        Room singleRoom2 = Room.builder().
                roomStatus(RoomStatus.FREE).
                bookingId(bookingId).
                roomCategory(RoomCategory.SINGLE).
                roomNumber(101).build();

        list.add(singleRoom);

        Mockito.when(bookingRepository.findBookingById(bookingId)).thenReturn(Optional.of(booking));
        Mockito.when(roomRepository.findRoomByRoomNumber(101)).thenReturn(Optional.of(singleRoom2));


        //when
        checkInService.checkIn(bookingId, list);


        //then
        assertEquals(BookingStatus.CHECKEDIN, booking.getBookingStatus());
        assertEquals(RoomStatus.OCCUPIED, singleRoom2.getRoomStatus());
    }


    @Test
    void given_roomsandbookingId_when_checkIn_then_henexpectthrowsroomnotfoundexception() throws RoomNotFoundException, RoomAlreadyOccupiedException, BookingNotFoundException, NotEnoughRoomsException {
        List<RoomDTO> list = new ArrayList<>();
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

        RoomDTO singleRoom = RoomDTO.builder().
                roomstatus(RoomStatus.FREE).
                bookingId(bookingId).
                roomCategory(RoomCategory.SINGLE).
                roomNumber(102).build();

        Room singleRoom2 = Room.builder().
                roomStatus(RoomStatus.FREE).
                bookingId(bookingId).
                roomCategory(RoomCategory.SINGLE).
                roomNumber(101).build();

        list.add(singleRoom);

        Mockito.when(bookingRepository.findBookingById(bookingId)).thenReturn(Optional.of(booking));
        Mockito.when(roomRepository.findRoomByRoomNumber(101)).thenReturn(Optional.of(singleRoom2));


        // when ... then
        assertThrows(RoomNotFoundException.class, () -> checkInService.checkIn(bookingId, list));
    }


    @Test
    void given_roomsandbookingId_when_checkIn_then_henexpectthrowsbookingnotfoundexception() throws RoomNotFoundException, RoomAlreadyOccupiedException, BookingNotFoundException, NotEnoughRoomsException {
        List<RoomDTO> list = new ArrayList<>();
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

        RoomDTO singleRoom = RoomDTO.builder().
                roomstatus(RoomStatus.FREE).
                bookingId(bookingId).
                roomCategory(RoomCategory.SINGLE).
                roomNumber(101).build();

        Room singleRoom2 = Room.builder().
                roomStatus(RoomStatus.FREE).
                bookingId(bookingId).
                roomCategory(RoomCategory.SINGLE).
                roomNumber(101).build();

        list.add(singleRoom);

        Mockito.when(bookingRepository.findBookingById(bookingId)).thenReturn(Optional.of(booking));
        Mockito.when(roomRepository.findRoomByRoomNumber(101)).thenReturn(Optional.of(singleRoom2));


        // when ... then
        assertThrows(BookingNotFoundException.class, () -> checkInService.checkIn(new BookingId(UUID.randomUUID()), list));
    }


    @Test
    void given_roomsandbookingId_when_checkIn_then_expectthrowsroomalreadyoccupiedexception() throws RoomNotFoundException, RoomAlreadyOccupiedException, BookingNotFoundException, NotEnoughRoomsException {
        List<RoomDTO> list = new ArrayList<>();
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

        RoomDTO singleRoom = RoomDTO.builder().
                roomstatus(RoomStatus.OCCUPIED).
                bookingId(bookingId).
                roomCategory(RoomCategory.SINGLE).
                roomNumber(101).build();

        Room singleRoom2 = Room.builder().
                roomStatus(RoomStatus.OCCUPIED).
                bookingId(bookingId).
                roomCategory(RoomCategory.SINGLE).
                roomNumber(101).build();

        list.add(singleRoom);

        Mockito.when(bookingRepository.findBookingById(bookingId)).thenReturn(Optional.of(booking));
        Mockito.when(roomRepository.findRoomByRoomNumber(101)).thenReturn(Optional.of(singleRoom2));


        // when ... then
        assertThrows(RoomAlreadyOccupiedException.class, () -> checkInService.checkIn(bookingId, list));
    }

}

