package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.CheckInService;
import at.fhv.hotelsoftware.application.dto.RoomDTO;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.api.RoomRepository;
import at.fhv.hotelsoftware.domain.model.*;
import at.fhv.hotelsoftware.domain.model.exceptions.BookingNotFoundException;
import at.fhv.hotelsoftware.domain.model.exceptions.NotEnoughRoomsException;
import at.fhv.hotelsoftware.domain.model.exceptions.RoomAlreadyOccupiedException;
import at.fhv.hotelsoftware.domain.model.exceptions.RoomNotFoundException;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;
import at.fhv.hotelsoftware.domain.model.valueobjects.RoomCategory;
import at.fhv.hotelsoftware.domain.model.valueobjects.RoomStatus;
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
    public List<RoomDTO> findFreeRoomsForBooking(BookingId bookingId) throws BookingNotFoundException, NotEnoughRoomsException {
        Optional<Booking> optBooking = bookingRepository.findBookingById(bookingId);

        if (optBooking.isEmpty()) {
            throw new BookingNotFoundException("Booking with ID: " + bookingId + " Not Found");
        }

        Booking booking = optBooking.get();
        Integer singleRoomCount = booking.getSingleRoom();
        Integer doubleRoomCount = booking.getDoubleRoom();
        Integer luxusRoomCount = booking.getSuperiorRoom();

        List<Room> allRooms = roomRepository.findAllRooms();
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
    public void checkIn(BookingId bookingId, List<RoomDTO> checkInRooms) throws RoomNotFoundException, RoomAlreadyOccupiedException, BookingNotFoundException {

        for (RoomDTO checkInRoom : checkInRooms)
        {
            Optional<Room> optRoom = roomRepository.findRoomByRoomNumber(checkInRoom.getRoomNumber());

            if (optRoom.isEmpty()) {
                throw new RoomNotFoundException("Room with room number: " + checkInRoom.getRoomNumber() + "not found");
            }

            Room room = optRoom.get();

            room.occupy(bookingId);
        }

        Optional<Booking> booking = bookingRepository.findBookingById(bookingId);

        if (booking.isPresent()) {
            booking.get().checkIn();
        }
        else
            throw new BookingNotFoundException("Booking with ID: " + bookingId.getBookingId() + " not found");
    }
}

