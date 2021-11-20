package at.fhv.hotelsoftware.application.api;

import at.fhv.hotelsoftware.application.dto.RoomDTO;
import at.fhv.hotelsoftware.domain.model.BookingId;
import at.fhv.hotelsoftware.domain.model.BookingNotFoundException;
import at.fhv.hotelsoftware.domain.model.RoomAlreadyOccupiedException;
import at.fhv.hotelsoftware.domain.model.RoomNotFoundException;

import java.util.List;

public interface CheckInService {

    void occupyRoom(RoomDTO room, BookingId bookingId) throws RoomNotFoundException, RoomAlreadyOccupiedException;
    void assignRoomToBooking(BookingId bookingId) throws BookingNotFoundException;
    List<RoomDTO> findFreeRoomsForBooking(String bookingIdString) throws BookingNotFoundException;
}
