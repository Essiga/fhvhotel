package at.fhv.hotelsoftware.domain.model;

import java.util.UUID;

public class BookingId {
    private UUID bookingId;

    public BookingId() {
    }

    public BookingId(UUID bookingId){
        this.bookingId = bookingId;
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }
}
