package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.CheckInService;
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
    public List<RoomDTO> findFreeRoomsForBooking(String bookingIdString) throws BookingNotFoundException, NotEnoughRoomsException {
        Optional<Booking> optBooking = bookingRepository.findBookingById(new BookingId(bookingIdString));

        if (optBooking.isEmpty()) {
            throw new BookingNotFoundException("Booking not found");
        }

        Booking booking = optBooking.get();
        Integer singleRoomCount = booking.getSingleRoom();
        Integer doubleRoomCount = booking.getDoubleRoom();
        Integer luxusRoomCount = booking.getLuxusRoom();

        List<Room> allRooms = roomRepository.findAllRooms();
        List<RoomDTO> freeRoomsForBooking = new LinkedList<>();

        for (int i = 0; i < allRooms.size(); i++) {
            if (singleRoomCount != 0 && allRooms.get(i).getRoomCategory() == RoomCategory.SINGLE) {
                freeRoomsForBooking.add(RoomDTO.fromRoom(allRooms.get(i)));
                --singleRoomCount;
            } else if (doubleRoomCount != 0 && allRooms.get(i).getRoomCategory() == RoomCategory.DOUBLE) {
                freeRoomsForBooking.add(RoomDTO.fromRoom(allRooms.get(i)));
                --doubleRoomCount;
            } else if (luxusRoomCount != 0 && allRooms.get(i).getRoomCategory() == RoomCategory.LUXUS) {
                freeRoomsForBooking.add(RoomDTO.fromRoom(allRooms.get(i)));
                --luxusRoomCount;
            }
            if (singleRoomCount == 0 && doubleRoomCount == 0 && luxusRoomCount == 0) {
                return freeRoomsForBooking;
            }
        }
             throw new NotEnoughRoomsException("Not enough rooms available");
    }

    @Override
    @Transactional
    public void checkIn(BookingId bookingId, List<RoomDTO> checkInRooms) throws RoomNotFoundException, RoomAlreadyOccupiedException, BookingNotFoundException {

        for (RoomDTO checkInRoom : checkInRooms)
        {
            Optional<Room> optRoom = roomRepository.findRoomByRoomNumber(checkInRoom.getRoomNumber());

            if (optRoom.isEmpty()) {
                throw new RoomNotFoundException("Room not found");
            }

            Room room = optRoom.get();

            if (room.getRoomStatus() == RoomStatus.FREE) {
                room.occupy(bookingId);
            } else {
                throw new RoomAlreadyOccupiedException("Room occupied");
            }
        }

        Optional<Booking> booking = bookingRepository.findBookingById(bookingId);

        if (booking.isPresent()) {
            booking.get().checkIn();
        }
        else
            throw new BookingNotFoundException("Booking not found");
    }
}

