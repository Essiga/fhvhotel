package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.CheckInService;
import at.fhv.hotelsoftware.application.dto.BookingDTO;
import at.fhv.hotelsoftware.application.dto.RoomDTO;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.api.RoomRepository;
import at.fhv.hotelsoftware.domain.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class CheckInServiceImpl implements CheckInService {

    @Autowired
    private final BookingRepository bookingRepository;

    @Autowired
    private final RoomRepository roomRepository;

    public CheckInServiceImpl(BookingRepository bookingRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public List<RoomDTO> findAvailableRooms(String bookingIdString) {
        BookingId bookingId = new BookingId(bookingIdString);
        Optional<Booking> booking = bookingRepository.findBookingById(bookingId);
        List<RoomDTO> rooms = new LinkedList<>();

        Integer singleRoomCount = booking.get().getSingleRoom();
        Integer doubleRoomCount = booking.get().getDoubleRoom();
        Integer luxusRoomCount = booking.get().getLuxusRoom();
        List<Room> allRooms = roomRepository.allRooms();

        for (int i = 0; i < allRooms.size(); i++) {
            if (singleRoomCount == 0 && doubleRoomCount == 0 && luxusRoomCount == 0) {
                break;
            }
            if (singleRoomCount != 0 && allRooms.get(i).getRoomCategory() == RoomCategory.SINGLE) {
                rooms.add(RoomDTO.fromRoom(allRooms.get(i)));
                singleRoomCount--;
            } else if (doubleRoomCount != 0 && allRooms.get(i).getRoomCategory() == RoomCategory.DOUBLE) {
                rooms.add(RoomDTO.fromRoom(allRooms.get(i)));
                doubleRoomCount--;
            } else if (luxusRoomCount != 0 && allRooms.get(i).getRoomCategory() == RoomCategory.LUXUS) {
                rooms.add(RoomDTO.fromRoom(allRooms.get(i)));
                luxusRoomCount--;
            }
        }

        return rooms;
    }


    @Override
    @Transactional
    public void checkIn(BookingId bookingId, List<RoomDTO> roomDTOs) throws RoomNotFoundException, RoomAlreadyOccupiedException {

        for (RoomDTO roomDTO : roomDTOs) {
            Optional<Room> room = roomRepository.findByRoomNumber(roomDTO.getRoomNumber());

            if (room.isEmpty()) {
                throw new RoomNotFoundException("Room not found");
            }

            if (room.get().getRoomStatus() == RoomStatus.FREE) {
                room.get().occupy(bookingId);
            } else {
                throw new RoomAlreadyOccupiedException("Room occupied");
            }
        }

        Optional<Booking> booking = bookingRepository.findBookingById(bookingId);
        if (!booking.isEmpty()) {
            booking.get().checkIn();
        }
    }
}

