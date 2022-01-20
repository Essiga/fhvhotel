package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.ViewRoomService;
import at.fhv.hotelsoftware.application.dto.RoomDTO;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.api.RoomRepository;
import at.fhv.hotelsoftware.domain.model.Booking;
import at.fhv.hotelsoftware.domain.model.exceptions.BookingNotFoundException;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;
import at.fhv.hotelsoftware.domain.model.Room;
import at.fhv.hotelsoftware.domain.model.exceptions.RoomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class ViewRoomServiceImpl implements ViewRoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BookingRepository bookingRepository;

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
    public void clean(String roomNumberString) {
        Integer roomNumber = Integer.parseInt(roomNumberString);
        Optional<Room> roomOpt = roomRepository.findRoomByRoomNumber(roomNumber);
        Room room  = roomOpt.get();
        room.clean();
    }

    @Override
    public List<Integer> findFreeContingentOfRooms(LocalDate checkIn, LocalDate checkOut) throws BookingNotFoundException {
        int occupiedSingleRoomCount = 0;
        int occupiedDoubleRoomCount = 0;
        int occupiedSuperiorRoomCount = 0;

        List<Booking> bookings = bookingRepository.findBookingsByDate(checkIn, checkOut);

        if (bookings.isEmpty()){
            throw new BookingNotFoundException("No bookings found");
        }

        for (int i = 0; i < bookings.size(); i++) {
            occupiedSingleRoomCount += bookings.get(i).getSingleRoom();
            occupiedDoubleRoomCount += bookings.get(i).getDoubleRoom();
            occupiedSuperiorRoomCount += bookings.get(i).getSuperiorRoom();
        }

        List<Integer> resultList = new LinkedList<>();
        resultList.add(roomRepository.findAllSingleRoomCount() - occupiedSingleRoomCount);
        resultList.add(roomRepository.findAllDoubleRoomCount() - occupiedDoubleRoomCount);
        resultList.add(roomRepository.findAllSuperiorRoomCount() - occupiedSuperiorRoomCount);

        return resultList;
    }
}
