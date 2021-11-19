package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.CheckInService;
import at.fhv.hotelsoftware.application.dto.BookingDTO;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.api.RoomRepository;
import at.fhv.hotelsoftware.domain.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void checkIn(BookingId bookingId) {

        Optional<Booking> booking = bookingRepository.findBookingById(bookingId);
        if (!booking.isEmpty()) {
            booking.get().checkIn();
        }

        Integer singleRoomCount = booking.get().getSingleRoom();
        Integer doubleRoomCount = booking.get().getDoubleRoom();
        Integer luxusRoomCount = booking.get().getLuxusRoom();
        List<Room> allRooms = roomRepository.allRooms();

        //sucht mir einen freien room der eine bestimmteen RoomCategory hat und setzt den roomStatus to Occupied und
        // setzt die bookingId im Raum
        for (int i = 0; i < allRooms.size(); i++) {
            if (singleRoomCount == 0 && doubleRoomCount == 0 && luxusRoomCount == 0){
                break;
            }
            if (singleRoomCount != 0 && allRooms.get(i).getRoomStatus() == RoomStatus.FREE &&
                    allRooms.get(i).getRoomCategory() == RoomCategory.SINGLE) {
                allRooms.get(i).setRoomOccupiedAndAssigneBookingId(bookingId);
                singleRoomCount--;
            } else if (doubleRoomCount != 0 && allRooms.get(i).getRoomStatus() == RoomStatus.FREE &&
                    allRooms.get(i).getRoomCategory() == RoomCategory.DOUBLE) {
                allRooms.get(i).setRoomOccupiedAndAssigneBookingId(bookingId);
                doubleRoomCount--;
            } else if (luxusRoomCount != 0 && allRooms.get(i).getRoomStatus() == RoomStatus.FREE &&
                    allRooms.get(i).getRoomCategory() == RoomCategory.LUXUS) {
                allRooms.get(i).setRoomOccupiedAndAssigneBookingId(bookingId);
                luxusRoomCount--;
            }
        }
    }
}

