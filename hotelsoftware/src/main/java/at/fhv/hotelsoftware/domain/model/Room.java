package at.fhv.hotelsoftware.domain.model;

import java.util.Objects;
import java.util.UUID;

public class Room {
    public long id;
    private RoomId roomId;
    private RoomCategory roomCategory;
    private Integer roomNumber;

    public static Builder builder() {
        return new Builder();
    }

    public Room() {
    }

    private Long getId() {
        return id;
    }

    private RoomId getRoomId() {
        return roomId;
    }

    private RoomCategory getRoomCategory() {
        return roomCategory;
    }

    private Integer getRoomNumber() {
        return roomNumber;
    }

    private void setRoomCategory(RoomCategory roomCategory) {
        this.roomCategory = roomCategory;
    }

    private void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    private void setRoomId(RoomId roomId) {
        this.roomId = roomId;
    }

    private void setId(long id) {
        this.id = id;
    }

    public static class Builder {

        private final Room instance;

        public Builder() {
            this.instance = new Room();
        }

        public Builder withRoomId(RoomId roomId) {
            this.instance.roomId = roomId;
            return this;
        }

        public Builder withRoomCategory(RoomCategory roomCategory) {
            this.instance.roomCategory = roomCategory;
            return this;
        }

        public Builder withRoomNumber(Integer roomNumber) {
            this.instance.roomNumber = roomNumber;
            return this;
        }


        public Room build() {
            Objects.requireNonNull(this.instance.roomId, "type must be set in room");
            return this.instance;
        }
    }
}
