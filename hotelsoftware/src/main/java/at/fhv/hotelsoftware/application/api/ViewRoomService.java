package at.fhv.hotelsoftware.application.api;

import at.fhv.hotelsoftware.application.dto.RoomDTO;
import at.fhv.hotelsoftware.domain.model.Room;
import at.fhv.hotelsoftware.domain.model.exceptions.RoomNotFoundException;

import java.util.List;

public interface ViewRoomService {

    List<RoomDTO> findRoomsByBookingId(String bookingId) throws RoomNotFoundException;
    void createRoom(Room room);
}
