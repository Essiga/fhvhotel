package at.fhv.hotelsoftware.application.api;

import at.fhv.hotelsoftware.application.dto.RoomDTO;
import at.fhv.hotelsoftware.domain.model.exceptions.*;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;

import java.util.List;

public interface CheckInService {

    void checkIn(BookingId bookingId, List<RoomDTO> freeRoomList) throws RoomNotFoundException, RoomAlreadyOccupiedException, BookingNotFoundException, RoomCategoryMismatchException, DoubleRoomNumberException;
    List<RoomDTO> findFreeRoomsForBooking(BookingId bookingId) throws BookingNotFoundException, NotEnoughRoomsException;
}
