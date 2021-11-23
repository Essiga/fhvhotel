package at.fhv.hotelsoftware.domain.api;

import at.fhv.hotelsoftware.domain.model.Booking;
import at.fhv.hotelsoftware.domain.model.BookingId;
import at.fhv.hotelsoftware.domain.model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {

    List<Room> findAllRooms();
    List<Room> findRoomByBookingId(BookingId bookingid);
    Optional<Room> findRoomByRoomNumber(Integer roomNumber);

    void addRoom(Room room);
}
