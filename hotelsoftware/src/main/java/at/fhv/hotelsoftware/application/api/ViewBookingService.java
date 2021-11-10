package at.fhv.hotelsoftware.application.api;

import at.fhv.hotelsoftware.application.dto.BookingDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ViewBookingService {
    @Transactional(readOnly = true)
    List<BookingDTO> findTodaysCheckIns();
    List<BookingDTO> findAllBookings();
    BookingDTO findBooking();
}
