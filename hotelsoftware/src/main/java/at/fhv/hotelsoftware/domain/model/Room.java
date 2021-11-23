package at.fhv.hotelsoftware.domain.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
public class Room {
    public Long id;
    private RoomCategory roomCategory;
    private Integer roomNumber;
    private RoomStatus roomStatus;
    private BookingId bookingId;

    public static Builder builder() {
        return new Builder();
    }

    public Room() {
    }

    public void occupy(BookingId bookingId){
        this.roomStatus = RoomStatus.OCCUPIED;
        this.bookingId = bookingId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoomCategory getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(RoomCategory roomCategory) {
        this.roomCategory = roomCategory;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

    public BookingId getBookingId() {
        return bookingId;
    }

    public void setBookingId(BookingId bookingId) {
        this.bookingId = bookingId;
    }

    public static class Builder {

        private final Room instance;

        public Builder() {
            this.instance = new Room();
        }

        public Builder withRoomCategory(RoomCategory roomCategory) {
            this.instance.roomCategory = roomCategory;
            return this;
        }

        public Builder withRoomNumber(Integer roomNumber) {
            this.instance.roomNumber = roomNumber;
            return this;
        }

        public Builder withRoomStatus(RoomStatus roomStatus) {
            this.instance.roomStatus = roomStatus;
            return this;
        }

        public Builder withBookingId(BookingId bookingId){
            this.instance.bookingId = bookingId;
            return this;
        }


        public Room build() {
  //          Objects.requireNonNull(this.instance.roomId, "type must be set in room");
            return this.instance;
        }
    }
}
