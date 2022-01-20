package at.fhv.hotelsoftware.domain.api;

import at.fhv.hotelsoftware.domain.model.Booking;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;
import at.fhv.hotelsoftware.domain.model.Room;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RoomRepository {

    List<Room> findAllFreeRooms();
    List<Room> findAllRooms();
    List<Room> findRoomsByBookingId(BookingId bookingid);
    Optional<Room> findRoomByRoomNumber(int roomNumber);
    Integer findAllSingleRoomCount();
    Integer findAllDoubleRoomCount();
    Integer findAllSuperiorRoomCount();
}
