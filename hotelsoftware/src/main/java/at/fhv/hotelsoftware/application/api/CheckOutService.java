package at.fhv.hotelsoftware.application.api;

import at.fhv.hotelsoftware.application.dto.RoomDTO;
import at.fhv.hotelsoftware.domain.model.BookingId;
import at.fhv.hotelsoftware.domain.model.BookingNotFoundException;
import at.fhv.hotelsoftware.domain.model.RoomAlreadyOccupiedException;
import at.fhv.hotelsoftware.domain.model.RoomNotFoundException;
import at.fhv.hotelsoftware.view.form.BookingForm;

import java.util.List;

public interface CheckOutService {
    void checkOut(BookingId bookingId) throws RoomNotFoundException, BookingNotFoundException;
}
