package at.fhv.hotelsoftware.application.api;

import at.fhv.hotelsoftware.application.dto.BookingDTO;
import at.fhv.hotelsoftware.application.dto.RoomDTO;
import at.fhv.hotelsoftware.domain.model.BookingId;
import at.fhv.hotelsoftware.domain.model.Room;
import at.fhv.hotelsoftware.domain.model.RoomNotFoundException;

import java.util.List;

public interface ViewRoomService {

    RoomDTO findRoomByBookingId(BookingId bookingId) throws RoomNotFoundException;
    void createRoom(Room room);
}
