package at.fhv.hotelsoftware.domain.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Data
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
