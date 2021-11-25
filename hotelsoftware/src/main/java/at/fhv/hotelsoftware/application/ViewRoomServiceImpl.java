package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.ViewRoomService;
import at.fhv.hotelsoftware.application.dto.BookingDTO;
import at.fhv.hotelsoftware.application.dto.RoomDTO;
import at.fhv.hotelsoftware.domain.api.RoomRepository;
import at.fhv.hotelsoftware.domain.model.Booking;
import at.fhv.hotelsoftware.domain.model.BookingId;
import at.fhv.hotelsoftware.domain.model.Room;
import at.fhv.hotelsoftware.domain.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class ViewRoomServiceImpl implements ViewRoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    @Transactional(readOnly = true)
    public RoomDTO findRoomByBookingId(BookingId bookingId) throws RoomNotFoundException {
        Optional<Room> roomOpt = roomRepository.findRoomByBookingId(bookingId);

        if (roomOpt.isEmpty()){
            throw new RoomNotFoundException("Room assigned to Booking : " + bookingId.getBookingId().toString() + " Not Found");
        }

        return RoomDTO.fromRoom(roomOpt.get());
    }

    @Override
    @Transactional
    public void createRoom(Room room) {
        roomRepository.addRoom(room);
    }
}
