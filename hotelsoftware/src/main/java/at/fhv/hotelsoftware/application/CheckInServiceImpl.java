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
            throw new BookingNotFoundException("Booking with ID: " + bookingIdString + " Not Found");
        }

        Booking booking = optBooking.get();
        Integer singleRoomCount = booking.getSingleRoom();
        Integer doubleRoomCount = booking.getDoubleRoom();
        Integer luxusRoomCount = booking.getSuperiorRoom();

        List<Room> allRooms = roomRepository.findAllRooms();
        List<RoomDTO> freeRoomsForBooking = new LinkedList<>();

        for (int i = 0; i < allRooms.size(); i++) {

            if (singleRoomCount == 0 && doubleRoomCount == 0 && luxusRoomCount == 0) {
                break;
            }
            if (singleRoomCount != 0 && allRooms.get(i).getRoomCategory() == RoomCategory.SINGLE) {
                freeRoomsForBooking.add(RoomDTO.fromRoom(allRooms.get(i)));
                singleRoomCount--;
            } else if (doubleRoomCount != 0 && allRooms.get(i).getRoomCategory() == RoomCategory.DOUBLE) {
                freeRoomsForBooking.add(RoomDTO.fromRoom(allRooms.get(i)));
                doubleRoomCount--;
            } else if (luxusRoomCount != 0 && allRooms.get(i).getRoomCategory() == RoomCategory.SUPERIOR) {
                freeRoomsForBooking.add(RoomDTO.fromRoom(allRooms.get(i)));
                luxusRoomCount--;
            }
        }

        if (!(singleRoomCount == 0 && doubleRoomCount == 0 && luxusRoomCount == 0)) {
            throw new NotEnoughRoomsException("Not Enough Rooms Available. " +
                                              singleRoomCount + " Single, " + doubleRoomCount + " Double, " + luxusRoomCount + " Luxus " +
                                              "Rooms Could Not Be Assigned");
        }

        return freeRoomsForBooking;
    }

    @Override
    @Transactional
    public void checkIn(BookingId bookingId, List<RoomDTO> checkInRooms) throws RoomNotFoundException, RoomAlreadyOccupiedException, BookingNotFoundException {

        for (RoomDTO checkInRoom : checkInRooms)
        {
            Optional<Room> optRoom = roomRepository.findRoomByRoomNumber(checkInRoom.getRoomNumber());

            if (optRoom.isEmpty()) {
                throw new RoomNotFoundException("Room with RoomNumber: " + checkInRoom.getRoomNumber() + "Not Found");
            }

            Room room = optRoom.get();

            if (room.getRoomStatus() == RoomStatus.FREE) {
                room.occupy(bookingId);
            } else {
                throw new RoomAlreadyOccupiedException("Room with RoomNumber: " + room.getRoomNumber() + " Already Occupied");
            }
        }

        Optional<Booking> booking = bookingRepository.findBookingById(bookingId);

        if (booking.isPresent()) {
            booking.get().checkIn();
        }
        else
            throw new BookingNotFoundException("Booking with ID: " + bookingId.getBookingId() + " Not Found");
    }
}

