package at.fhv.hotelsoftware.application.api;

import at.fhv.hotelsoftware.domain.model.exceptions.RoomNotOccupiedException;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;
import at.fhv.hotelsoftware.domain.model.exceptions.BookingNotFoundException;
import at.fhv.hotelsoftware.domain.model.exceptions.RoomNotFoundException;

public interface CheckOutService {

    void checkOut(BookingId bookingId) throws RoomNotFoundException, BookingNotFoundException, RoomNotOccupiedException;
}
