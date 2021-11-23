package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.ViewRoomService;
import at.fhv.hotelsoftware.application.dto.RoomDTO;
import at.fhv.hotelsoftware.domain.api.RoomRepository;
import at.fhv.hotelsoftware.domain.model.BookingId;
import at.fhv.hotelsoftware.domain.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ViewRoomServiceImpl implements ViewRoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    @Transactional(readOnly = true)
    public List<RoomDTO> findRoomByBookingId(String bookingIdString) {
        BookingId bookingId = new BookingId(bookingIdString);
        List<Room> allRooms = roomRepository.findRoomByBookingId(bookingId);
        return RoomDTO.fromRoomList(allRooms);
    }

    @Override
    @Transactional
    public void createRoom(Room room) {
        roomRepository.addRoom(room);
    }
}
