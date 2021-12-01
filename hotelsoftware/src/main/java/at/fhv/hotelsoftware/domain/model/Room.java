package at.fhv.hotelsoftware.domain.model;

import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;
import at.fhv.hotelsoftware.domain.model.valueobjects.RoomCategory;
import at.fhv.hotelsoftware.domain.model.valueobjects.RoomStatus;
import lombok.*;

@Data //setters required by Hibernate
@NoArgsConstructor
public class Room {

    private Long id;
    private RoomCategory roomCategory;
    private Integer roomNumber;
    private RoomStatus roomStatus;
    private BookingId bookingId;

    @Builder
    public Room(RoomCategory roomCategory, Integer roomNumber, RoomStatus roomStatus, BookingId bookingId) {
        this.roomCategory = roomCategory;
        this.roomNumber = roomNumber;
        this.roomStatus = roomStatus;
        this.bookingId = bookingId;
    }

    public void occupy(BookingId bookingId) {
        this.roomStatus = RoomStatus.OCCUPIED;
        this.bookingId = bookingId;
    }

    public void checkOut() {
        this.roomStatus = RoomStatus.CLEANING;
        this.bookingId = null;
    }
}
