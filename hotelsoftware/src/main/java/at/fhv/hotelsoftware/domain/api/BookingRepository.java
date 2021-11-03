package at.fhv.hotelsoftware.domain.api;

import at.fhv.hotelsoftware.application.dto.BookingDTO;
import at.fhv.hotelsoftware.domain.Booking;
import org.springframework.stereotype.Component;

import java.util.List;


public interface BookingRepository {
    void addToDatabase(Booking booking);
    List<BookingDTO> getTodaysCheckIns();
}
