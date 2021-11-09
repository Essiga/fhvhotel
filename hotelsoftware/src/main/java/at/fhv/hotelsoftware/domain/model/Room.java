package at.fhv.hotelsoftware.domain.model;

import java.util.Objects;
import java.util.UUID;

public class Room {
    private RoomId roomId;
    private RoomCategory roomCategory;
    private Integer roomNumber;
    private Integer roomCount;

    public static Builder builder() {
        return new Builder();
    }

    public Room() {
    }

    public RoomId getRoomId() {
        return roomId;
    }

    public void setRoomId(RoomId roomId) {
        this.roomId = roomId;
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

    public Integer getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(Integer roomCount) {
        this.roomCount = roomCount;
    }

    public static class Builder {

        private final Room instance;

        public Builder() {
            this.instance = new Room();
        }

        public Builder withUID(RoomId roomId) {
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

        public Builder withRoomCount(Integer roomCount){
            this.instance.roomCount = roomCount;
            return this;
        }

        public Room build() {
            Objects.requireNonNull(this.instance.roomId, "type must be set in room");
            return this.instance;
        }
    }
}
