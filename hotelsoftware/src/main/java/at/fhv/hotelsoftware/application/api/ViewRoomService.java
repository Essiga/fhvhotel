package at.fhv.hotelsoftware.application.api;

import at.fhv.hotelsoftware.application.dto.RoomDTO;
import at.fhv.hotelsoftware.domain.model.Room;
import at.fhv.hotelsoftware.domain.model.exceptions.BookingNotFoundException;
import at.fhv.hotelsoftware.domain.model.exceptions.RoomNotFoundException;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ViewRoomService {

    List<RoomDTO> findRoomsByBookingId(BookingId bookingId) throws RoomNotFoundException;
    List<RoomDTO> findAllRooms() throws RoomNotFoundException;
    void clean(String roomNumberString);
    List<Integer> findFreeContingentOfRooms(LocalDate checkIn, LocalDate checkOut) throws BookingNotFoundException;
}
