package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.CheckOutService;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.api.RoomRepository;
import at.fhv.hotelsoftware.domain.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class CheckOutServiceImpl implements CheckOutService {
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    RoomRepository roomRepository;

    @Transactional
    @Override
    public void checkOut(BookingId bookingId) throws RoomNotFoundException, BookingNotFoundException {
        Optional<Booking> optBooking = bookingRepository.findBookingById(bookingId);

        if (optBooking.isEmpty()){
            throw new BookingNotFoundException("Booking not found");
        }
        Booking booking = optBooking.get();
        booking.complete();

        List<Room> rooms = roomRepository.findRoomByBookingId(bookingId);

        for (int i = 0; i < rooms.size(); i++) {
            rooms.get(i).cleaning();
        }
    }
}
