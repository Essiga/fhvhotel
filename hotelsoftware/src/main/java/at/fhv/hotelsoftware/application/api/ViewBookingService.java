package at.fhv.hotelsoftware.application.api;

import at.fhv.hotelsoftware.application.dto.BookingDTO;
import at.fhv.hotelsoftware.application.dto.RoomDTO;
import at.fhv.hotelsoftware.domain.model.BookingId;
import at.fhv.hotelsoftware.domain.model.BookingNotFoundException;
import at.fhv.hotelsoftware.domain.model.Room;

import java.util.List;

public interface ViewBookingService {
    List<BookingDTO> findTodaysCheckIns();
    List<BookingDTO> findTodaysCheckOuts();

    List<BookingDTO> findAllBookings();
    BookingDTO findBookingById(String bookingId) throws BookingNotFoundException;

    List<RoomDTO> roomByBookingId(BookingId bookingId);
;    void createRoom(Room room);
}
