package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.ViewBookingService;
import at.fhv.hotelsoftware.application.dto.BookingDTO;
import at.fhv.hotelsoftware.application.dto.RoomDTO;
import at.fhv.hotelsoftware.domain.api.RoomRepository;
import at.fhv.hotelsoftware.domain.model.Booking;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.model.BookingId;
import at.fhv.hotelsoftware.domain.model.BookingNotFoundException;
import at.fhv.hotelsoftware.domain.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ViewBookingServiceImpl implements ViewBookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Transactional(readOnly = true)
    @Override
    public List<BookingDTO> findTodaysCheckIns() {
        List<Booking> todaysCheckIns = bookingRepository.findTodaysCheckIns();
        return BookingDTO.fromBookingList(todaysCheckIns);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookingDTO> findAllBookings() {
        List<Booking> allBookings = bookingRepository.findAllBookings();
        return BookingDTO.fromBookingList(allBookings);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookingDTO> findTodaysCheckOuts() {
        List<Booking> allBookings = bookingRepository.findTodaysCheckOuts();
        return BookingDTO.fromBookingList(allBookings);
    }

    @Transactional(readOnly = true)
    @Override
    public BookingDTO findBookingById(String bookingIdString) throws BookingNotFoundException {
        BookingId bookingId = new BookingId(bookingIdString);
        Optional<Booking> result = bookingRepository.findBookingById(new BookingId(bookingIdString));

        if (result.isEmpty()) {
            throw new BookingNotFoundException("Couldn't find a booking under that ID");
        }

        Booking booking = result.get();
        return BookingDTO.fromBooking(booking);
    }

}
