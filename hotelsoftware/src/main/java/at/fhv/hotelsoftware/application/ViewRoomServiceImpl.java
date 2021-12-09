package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.ViewRoomService;
import at.fhv.hotelsoftware.application.dto.RoomDTO;
import at.fhv.hotelsoftware.domain.api.RoomRepository;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;
import at.fhv.hotelsoftware.domain.model.Room;
import at.fhv.hotelsoftware.domain.model.exceptions.RoomNotFoundException;
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
    public List<RoomDTO> findRoomsByBookingId(BookingId bookingId) throws RoomNotFoundException {
        List<Room> rooms = roomRepository.findRoomsByBookingId(bookingId);

        if (rooms.isEmpty()){
            throw new RoomNotFoundException("room assigned to booking : " + bookingId + " not found");
        }

        return RoomDTO.fromRoomList(rooms);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoomDTO> findAllRooms () throws RoomNotFoundException{
        List<Room> rooms = roomRepository.findAllRooms();

        if (rooms.isEmpty()){
            throw new RoomNotFoundException("No Rooms exist (CreateDummyData)");
        }

        return RoomDTO.fromRoomList(rooms);
    }

    @Override
    @Transactional
    public void createRoom(Room room) {
        roomRepository.addRoom(room);
    }
}
