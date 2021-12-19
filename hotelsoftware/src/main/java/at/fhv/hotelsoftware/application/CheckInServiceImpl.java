package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.CheckInService;
import at.fhv.hotelsoftware.application.dto.RoomDTO;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.api.RoomRepository;
import at.fhv.hotelsoftware.domain.model.*;
import at.fhv.hotelsoftware.domain.model.exceptions.*;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;
import at.fhv.hotelsoftware.domain.model.valueobjects.RoomCategory;
import at.fhv.hotelsoftware.domain.model.valueobjects.RoomStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public List<RoomDTO> findFreeRoomsForBooking(BookingId bookingId) throws BookingNotFoundException, NotEnoughRoomsException {
        Optional<Booking> optBooking = bookingRepository.findBookingById(bookingId);

        if (optBooking.isEmpty()) {
            throw new BookingNotFoundException("Booking with ID: " + bookingId + " Not Found");
        }

        Booking booking = optBooking.get();
        Integer singleRoomCount = booking.getSingleRoom();
        Integer doubleRoomCount = booking.getDoubleRoom();
        Integer luxusRoomCount = booking.getSuperiorRoom();

        List<Room> allRooms = roomRepository.findAllFreeRooms();
        List<RoomDTO> freeRoomsForBooking = new LinkedList<>();

        for (int i = 0; i < allRooms.size(); i++) {

            if (singleRoomCount != 0 && allRooms.get(i).getRoomCategory() == RoomCategory.SINGLE) {
                freeRoomsForBooking.add(RoomDTO.fromRoom(allRooms.get(i)));
                --singleRoomCount;

            } else if (doubleRoomCount != 0 && allRooms.get(i).getRoomCategory() == RoomCategory.DOUBLE) {
                freeRoomsForBooking.add(RoomDTO.fromRoom(allRooms.get(i)));
                --doubleRoomCount;

            } else if (luxusRoomCount != 0 && allRooms.get(i).getRoomCategory() == RoomCategory.SUPERIOR) {
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
    public void checkIn(BookingId bookingId, List<RoomDTO> checkInRooms) throws RoomNotFoundException, RoomAlreadyOccupiedException, BookingNotFoundException, RoomCategoryMismatchException, DoubleRoomNumberException {

        Optional<Booking> booking = bookingRepository.findBookingById(bookingId);

        if (booking.isEmpty())
            throw new BookingNotFoundException("Booking with ID: " + bookingId.getBookingId() + " not found");

        List<Integer> checkInRoomNumbers = new ArrayList<>();

        for (RoomDTO checkInRoom : checkInRooms)
        {
            Integer checkInRoomNumber = checkInRoom.getRoomNumber();

            if (checkInRoomNumber == null)
                throw new RoomNotFoundException("Room number must not be empty");

            if (checkInRoomNumbers.contains(checkInRoomNumber))
                throw new DoubleRoomNumberException("Room with number: " + checkInRoomNumber + " cannot be chosen twice");

            checkInRoomNumbers.add(checkInRoomNumber);

            Optional<Room> optRoom = roomRepository.findRoomByRoomNumber(checkInRoomNumber);

            if (optRoom.isEmpty())
                throw new RoomNotFoundException("Room with number: " + checkInRoomNumber + " not found");

            Room room = optRoom.get();

            if (RoomStatus.OCCUPIED.equals(room.getRoomStatus()))
                throw new RoomAlreadyOccupiedException("Room with room number: " + checkInRoomNumber + " already occupied.");

            if ( !(checkInRoom.getRoomCategory().equals(room.getRoomCategory())) )
                throw new RoomCategoryMismatchException("Category of room with number: " + checkInRoomNumber + " does not match");
        }

        for (RoomDTO checkInRoom : checkInRooms)
        {
            Optional<Room> optRoom = roomRepository.findRoomByRoomNumber(checkInRoom.getRoomNumber());
            Room room = optRoom.get();
            room.occupy(bookingId);
        }

        booking.get().checkIn();
    }
}

