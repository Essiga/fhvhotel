package at.fhv.hotelsoftware.application.api;

import at.fhv.hotelsoftware.application.dto.BookingDTO;

import java.util.List;

public interface ViewBookingService {
    List<BookingDTO> findTodaysCheckIns();
    List<BookingDTO> findTodaysCheckOuts();

    List<BookingDTO> findAllBookings();
    List<BookingDTO> findBooking();
}
