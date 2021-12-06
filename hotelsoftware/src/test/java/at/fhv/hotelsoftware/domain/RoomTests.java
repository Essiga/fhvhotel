package at.fhv.hotelsoftware.domain;

import at.fhv.hotelsoftware.domain.model.Room;
import at.fhv.hotelsoftware.domain.model.exceptions.RoomAlreadyOccupiedException;
import at.fhv.hotelsoftware.domain.model.exceptions.RoomNotOccupiedException;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;
import at.fhv.hotelsoftware.domain.model.valueobjects.RoomCategory;
import at.fhv.hotelsoftware.domain.model.valueobjects.RoomStatus;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoomTests {

    @Test
    public void given_existingroom_when_occupy_then_roomstatuschanged_and_bookingidsaved() throws RoomAlreadyOccupiedException {
        //given
        Room room = new Room(RoomCategory.SINGLE, 2, RoomStatus.FREE, null);
        BookingId bookingId = new BookingId(UUID.randomUUID());

        //when
        room.occupy(bookingId);

        //then
        assertEquals(RoomStatus.OCCUPIED, room.getRoomStatus());
        //and
        assertEquals(bookingId, room.getBookingId());
    }

    @Test
    public void given_existingalreadyoccupiedroom_when_occupy_then_throwroomalreadyoccupiedexception() {
        //given
        BookingId bookingId = new BookingId(UUID.randomUUID());
        Room room = new Room(RoomCategory.SINGLE, 2, RoomStatus.OCCUPIED, bookingId);

        //when..then
        assertThrows(RoomAlreadyOccupiedException.class, () -> room.occupy(bookingId));

    }

    @Test
    public void given_checkedinroom_when_checkout_then_roomstatuscleaning_and_bookingidnull() throws RoomNotOccupiedException {
        //given
        BookingId bookingId = new BookingId(UUID.randomUUID());
        Room room = new Room(RoomCategory.SINGLE, 2, RoomStatus.OCCUPIED, bookingId);

        //when
        room.checkOut();

        //then
        assertEquals(RoomStatus.CLEANING, room.getRoomStatus());
        //and
        assertEquals(null, room.getBookingId());
    }

    @Test
    public void given_freeroom_when_checkout_then_throwroomnotoccupiedexception() {
        //given
        Room room = new Room(RoomCategory.SINGLE, 2, RoomStatus.FREE, null);

        //when...then
        assertThrows(RoomNotOccupiedException.class, () -> room.checkOut());
    }

    @Test
    public void given_roomwithstatuscleaning_when_checkout_then_throwroomnotoccupiedexception() {
        //given
        Room room = new Room(RoomCategory.SINGLE, 2, RoomStatus.CLEANING, null);

        //when...then
        assertThrows(RoomNotOccupiedException.class, room::checkOut);
    }


}
