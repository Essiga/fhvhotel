package at.fhv.hotelsoftware.application.api;

import at.fhv.hotelsoftware.application.dto.RoomDTO;
import at.fhv.hotelsoftware.domain.model.Room;
import at.fhv.hotelsoftware.domain.model.exceptions.RoomNotFoundException;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;

import java.util.List;
import java.util.Optional;

public interface ViewRoomService {

    List<RoomDTO> findRoomsByBookingId(BookingId bookingId) throws RoomNotFoundException;
    List<RoomDTO> findAllRooms() throws RoomNotFoundException;
    void clean(String roomNumberString);
}
