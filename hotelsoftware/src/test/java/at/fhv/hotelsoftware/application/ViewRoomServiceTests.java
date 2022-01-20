package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.CreateBookingService;
import at.fhv.hotelsoftware.application.api.ViewRoomService;
import at.fhv.hotelsoftware.application.dto.RoomDTO;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.api.RoomRepository;
import at.fhv.hotelsoftware.domain.model.Booking;
import at.fhv.hotelsoftware.domain.model.Room;
import at.fhv.hotelsoftware.domain.model.exceptions.BookingNotFoundException;
import at.fhv.hotelsoftware.domain.model.exceptions.RoomNotFoundException;
import at.fhv.hotelsoftware.domain.model.valueobjects.*;
import at.fhv.hotelsoftware.view.form.BookingForm;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ViewRoomServiceTests {

    @Autowired
    ViewRoomService viewRoomService;

    @Autowired
    CreateBookingService createBookingService;

    @MockBean
    RoomRepository roomRepository;

    @MockBean
    BookingRepository bookingRepository;

    @Test
    public void given_room_when_findroomsbybookingid_then_returncorrectroom() throws RoomNotFoundException {
        //given
        BookingId bookingId = new BookingId(UUID.randomUUID());

        Room room = new Room().builder().
                bookingId(bookingId).
                roomNumber(101).
                build();

        Mockito.when(roomRepository.findRoomsByBookingId(room.getBookingId())).thenReturn(List.of(room));

        //when
        List<RoomDTO> rooms = viewRoomService.findRoomsByBookingId(room.getBookingId());

        //then
        assertEquals(rooms.get(0).getBookingId().getBookingId(), room.getBookingId().getBookingId());
        assertEquals(rooms.get(0).getRoomNumber(), room.getRoomNumber());
    }

    @Test
    public void given_novalidbookingid_when_findroomsbybookingid_then_throwroomnotfoundexception() {
        //given
        BookingId bookingId = new BookingId(UUID.randomUUID());

        Room room = new Room().builder().
                bookingId(bookingId).
                roomNumber(101).
                build();

        Mockito.when(roomRepository.findRoomsByBookingId(room.getBookingId())).thenReturn(List.of(room));

        //when...then
        assertThrows(RoomNotFoundException.class, () -> viewRoomService.findRoomsByBookingId(new BookingId(UUID.randomUUID())));
    }

    @Test
    public void given_room_when_clean_then_expectroomstatusfree(){
        //given
        Room singleRoom = Room.builder().
                roomStatus(RoomStatus.CLEANING).
                bookingId(null).
                roomCategory(RoomCategory.SINGLE).
                roomNumber(100).build();

        Mockito.when(roomRepository.findRoomByRoomNumber(100)).thenReturn(Optional.of(singleRoom));

        //when
        viewRoomService.clean("100");

        //then
        assertEquals(RoomStatus.FREE, singleRoom.getRoomStatus());
    }

    @Test
    public void given_rooms_when_persistedflushedfetched_then_expectallrooms() throws RoomNotFoundException {
        Room singleRoom[] = new Room[10];
        Room doubleRoom[] = new Room[10];
        Room luxusRoom[] = new Room[10];
        List <Room> room = new LinkedList();

        int expectedNumberOfRooms = 30;


        for (int i = 0; i < singleRoom.length; i++) {
            singleRoom[i] = Room.builder().
                    roomStatus(RoomStatus.FREE).
                    bookingId(null).
                    roomCategory(RoomCategory.SINGLE).
                    roomNumber(100 + i).build();
                    room.add(singleRoom[i]);

            doubleRoom[i] = Room.builder().
                    roomStatus(RoomStatus.FREE).
                    bookingId(null).
                    roomCategory(RoomCategory.DOUBLE).
                    roomNumber(200 + i).build();
                    room.add(doubleRoom[i]);

            luxusRoom[i] = Room.builder().
                    roomStatus(RoomStatus.FREE).
                    bookingId(null).
                    roomCategory(RoomCategory.SUPERIOR).
                    roomNumber(300 + i).build();
                    room.add(luxusRoom[i]);
        }

        Mockito.when(roomRepository.findAllRooms()).thenReturn(room);

        //when
        List<RoomDTO> rooms = viewRoomService.findAllRooms();

        //then
        assertEquals(expectedNumberOfRooms, rooms.size());

    }

    @Test
    public void given_norooms_when_findallrooms_then_throwroomnotfoundexception() {

        //given
        List <Room> rooms = new LinkedList();

        Mockito.when(roomRepository.findAllRooms()).thenReturn(rooms);

        //when..then
        assertThrows(RoomNotFoundException.class, () -> viewRoomService.findAllRooms());

    }

    @Test
    public void given_roomsandbooking_when_findFreeContingentOfRooms_then_expectallrooms() throws BookingNotFoundException {
        List<Integer> expectedNumberOfRooms = new LinkedList<>();
        expectedNumberOfRooms.add(9);
        expectedNumberOfRooms.add(9);
        expectedNumberOfRooms.add(9);

        Booking booking = Booking.builder()
                          .bookingId(new BookingId(UUID.randomUUID()))
                          .bookingStatus(BookingStatus.CONFIRMED)
                .checkInDate(LocalDate.now())
                .checkOutDate(LocalDate.now().plusDays(1))
                .singleRoom(1)
                .doubleRoom(1)
                .superiorRoom(1)
                .build();


        LocalDate checkIn = LocalDate.now();
        LocalDate checkOut = LocalDate.now().plusDays(1);

        Mockito.when(bookingRepository.findBookingsByDate(checkIn, checkOut)).thenReturn(List.of(booking));
        Mockito.when(roomRepository.findAllSingleRoomCount()).thenReturn(10);
        Mockito.when(roomRepository.findAllDoubleRoomCount()).thenReturn(10);
        Mockito.when(roomRepository.findAllSuperiorRoomCount()).thenReturn(10);

        //when
        List<Integer> rooms = viewRoomService.findFreeContingentOfRooms(checkIn, checkOut);

        //then
        assertEquals(expectedNumberOfRooms, rooms);
    }

//
//    @Test
//    public void given_nobooking_when_findFreeContingentOfRooms_then_throwbookingnotfoundexception()  {
//        List<Integer> expectedNumberOfRooms = new LinkedList<>();
//        expectedNumberOfRooms.add(9);
//        expectedNumberOfRooms.add(9);
//        expectedNumberOfRooms.add(9);
//
//
//        LocalDate checkIn = LocalDate.now();
//        LocalDate checkOut = LocalDate.now().plusDays(1);
//
//
//        Mockito.when(roomRepository.findAllSingleRoomCount()).thenReturn(10);
//        Mockito.when(roomRepository.findAllDoubleRoomCount()).thenReturn(10);
//        Mockito.when(roomRepository.findAllSuperiorRoomCount()).thenReturn(10);
//
//        //when then
//        assertThrows(BookingNotFoundException.class, () -> viewRoomService.findFreeContingentOfRooms(checkIn, checkOut));
//
//    }
}
