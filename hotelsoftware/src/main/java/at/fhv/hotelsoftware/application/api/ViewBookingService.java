package at.fhv.hotelsoftware.application.api;

import at.fhv.hotelsoftware.application.dto.BookingDTO;
import at.fhv.hotelsoftware.domain.Booking;

import java.util.List;
import java.util.Optional;

public interface ViewBookingService {
    List<BookingDTO> findTodaysCheckIns();
    List<BookingDTO> findAllBookings();



}
