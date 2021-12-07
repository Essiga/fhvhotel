package at.fhv.hotelsoftware.application.api;

import at.fhv.hotelsoftware.application.dto.BookingDTO;
import at.fhv.hotelsoftware.domain.model.exceptions.BookingNotFoundException;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;

import java.util.List;

public interface ViewBookingService {

    BookingDTO findBookingById(BookingId bookingId) throws BookingNotFoundException;
    List<BookingDTO> findTodaysCheckIns();
    List<BookingDTO> findTodaysCheckOuts();

    List<BookingDTO> findAllBookings();
}