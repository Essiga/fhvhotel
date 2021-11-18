package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.CheckInService;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.api.RoomRepository;
import at.fhv.hotelsoftware.domain.model.Booking;
import at.fhv.hotelsoftware.domain.model.BookingId;
import at.fhv.hotelsoftware.domain.model.Room;
import at.fhv.hotelsoftware.domain.model.RoomStatus;
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
        if(!booking.isEmpty()) {
            booking.get().checkIn();
        }

        List<Room> allRooms = roomRepository.allRooms();

        int i = 0;
        for (Room room:allRooms) {
            if(allRooms.get(i).getRoomStatus() == RoomStatus.FREE) {

            }
            i++;
        }

        for(int i = 0; i <= (booking.get().getSingleRoom()); i++){

            if(allRooms.get(i).getRoomStatus() == RoomStatus.FREE) {

            }
            }
        // nächster Raum auswählen

            // im raum die Buchungsnummer ändern
        }
    }
}
