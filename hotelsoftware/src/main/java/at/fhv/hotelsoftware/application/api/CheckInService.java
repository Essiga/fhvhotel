package at.fhv.hotelsoftware.application.api;

import at.fhv.hotelsoftware.application.dto.RoomDTO;
import at.fhv.hotelsoftware.domain.model.exceptions.BookingNotFoundException;
import at.fhv.hotelsoftware.domain.model.exceptions.NotEnoughRoomsException;
import at.fhv.hotelsoftware.domain.model.exceptions.RoomAlreadyOccupiedException;
import at.fhv.hotelsoftware.domain.model.exceptions.RoomNotFoundException;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;

import java.util.List;

public interface CheckInService {

    void checkIn(BookingId bookingId, List<RoomDTO> freeRoomList) throws RoomNotFoundException, RoomAlreadyOccupiedException, BookingNotFoundException;
    List<RoomDTO> findFreeRoomsForBooking(String bookingIdString) throws BookingNotFoundException, NotEnoughRoomsException;
}
