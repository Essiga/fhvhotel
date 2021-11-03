package at.fhv.hotelsoftware.application.api;

import at.fhv.hotelsoftware.application.dto.BookingDTO;

import java.util.List;

public interface CheckInService {
    List<BookingDTO> getTodaysCheckIns();
}
