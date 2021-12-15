package at.fhv.hotelsoftware.domain.model;

import at.fhv.hotelsoftware.domain.model.exceptions.RoomAlreadyOccupiedException;
import at.fhv.hotelsoftware.domain.model.exceptions.RoomNotOccupiedException;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;
import at.fhv.hotelsoftware.domain.model.valueobjects.RoomCategory;
import at.fhv.hotelsoftware.domain.model.valueobjects.RoomStatus;
import lombok.*;

import java.util.Objects;

//TODO: try again without setter
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

    public void occupy(BookingId bookingId) throws RoomAlreadyOccupiedException {
        if(this.roomStatus == RoomStatus.OCCUPIED){
            throw new RoomAlreadyOccupiedException("Room with room number: " + roomNumber + " already occupied.");
        } else {
            this.roomStatus = RoomStatus.OCCUPIED;
            this.bookingId = bookingId;
        }
    }

    public void free(){
        if (this.roomStatus == RoomStatus.CLEANING){
            this.roomStatus = RoomStatus.FREE;
        }
    }

    public void checkOut() throws RoomNotOccupiedException {
        if(roomStatus != RoomStatus.OCCUPIED){
            throw new RoomNotOccupiedException("Room with room number: " + roomNumber + " is not occupied.");
        }
        else {
            this.roomStatus = RoomStatus.CLEANING;
            this.bookingId = null;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomCategory == room.roomCategory && roomNumber.equals(room.roomNumber) && roomStatus == room.roomStatus && Objects.equals(bookingId, room.bookingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomCategory, roomNumber, roomStatus, bookingId);
    }
}
