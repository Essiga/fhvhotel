package at.fhv.hotelsoftware.application.api;

import at.fhv.hotelsoftware.application.dto.RoomDTO;
import at.fhv.hotelsoftware.domain.model.BookingId;
import at.fhv.hotelsoftware.domain.model.Room;

import java.util.List;

public interface ViewRoomService {

    List<RoomDTO> findRoomByBookingId(String bookingId);
    void createRoom(Room room);
}
