package at.fhv.hotelsoftware.application.dto;
import at.fhv.hotelsoftware.domain.model.Room;
import at.fhv.hotelsoftware.domain.model.RoomCategory;
import at.fhv.hotelsoftware.domain.model.RoomId;

import java.rmi.server.UID;
import java.util.Objects;
import java.util.UUID;

public final class RoomDTO {

    private RoomId roomId;
    private RoomCategory roomCategory;
    private Integer roomNumber;
    private Integer roomCount;

    public RoomDTO(){}

    public RoomDTO(RoomId roomId,
                   RoomCategory roomCategory,
                   Integer roomNumber,
                   Integer roomCount){

        this.roomId = roomId;
        this.roomCategory = roomCategory;
        this.roomNumber = roomNumber;
        this.roomCount = roomCount;
    }

    public RoomId getRoomId() {
        return roomId;
    }

    public RoomCategory getRoomCategory() {
        return roomCategory;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public Integer getRoomCount() {
        return roomCount;
    }

    public static class Builder {

        private final RoomDTO instance;

        public Builder() {
            this.instance = new RoomDTO();
        }

        public RoomDTO.Builder withRoomId(RoomId roomId) {
            this.instance.roomId = roomId;
            return this;
        }


        public RoomDTO.Builder withRoomCategory(RoomCategory roomCategory) {
            this.instance.roomCategory = roomCategory;
            return this;
        }

        public RoomDTO.Builder withRoomNumber(Integer roomNumber) {
            this.instance.roomNumber = roomNumber;
            return this;
        }

        public RoomDTO.Builder withRoomCount(Integer roomCount) {
            this.instance.roomCount = roomCount;
            return this;
        }

        public RoomDTO build() {
            Objects.requireNonNull(this.instance.roomId, "type must be set in room");
            return this.instance;
        }
    }
}
