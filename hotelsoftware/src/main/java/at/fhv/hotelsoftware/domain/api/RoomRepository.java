package at.fhv.hotelsoftware.domain.api;

import at.fhv.hotelsoftware.domain.model.Booking;
import at.fhv.hotelsoftware.domain.model.BookingId;
import at.fhv.hotelsoftware.domain.model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {

    List<Room> allRooms();
    List<Room> roomByBookingId(BookingId bookingid);
    Optional<Room> findByRoomNumber(Integer roomNumber);
}
