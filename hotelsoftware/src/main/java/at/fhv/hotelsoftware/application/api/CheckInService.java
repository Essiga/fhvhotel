package at.fhv.hotelsoftware.application.api;

import at.fhv.hotelsoftware.application.dto.RoomDTO;
import at.fhv.hotelsoftware.domain.model.BookingId;
import at.fhv.hotelsoftware.domain.model.RoomAlreadyOccupiedException;
import at.fhv.hotelsoftware.domain.model.RoomNotFoundException;

import java.util.List;

public interface CheckInService {
    //
    void checkIn(BookingId bookingId, List<RoomDTO> rooms) throws RoomNotFoundException, RoomAlreadyOccupiedException;
    List<RoomDTO> findAvailableRooms(String bookingIdString);

}
