package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.ViewRoomService;
import at.fhv.hotelsoftware.application.dto.RoomDTO;
import at.fhv.hotelsoftware.domain.api.RoomRepository;
import at.fhv.hotelsoftware.domain.model.Room;
import at.fhv.hotelsoftware.domain.model.exceptions.RoomNotFoundException;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ViewRoomServiceTests {

    @Autowired
    ViewRoomService viewRoomService;

    @MockBean
    RoomRepository roomRepository;

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
}
