package at.fhv.hotelsoftware.domain.api;

import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;
import at.fhv.hotelsoftware.domain.model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {

    List<Room> findAllRooms();
    List<Room> findRoomsByBookingId(BookingId bookingid);
    Optional<Room> findRoomByRoomNumber(Integer roomNumber);

    void addRoom(Room room);
}
