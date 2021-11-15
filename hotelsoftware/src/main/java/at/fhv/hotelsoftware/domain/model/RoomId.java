package at.fhv.hotelsoftware.domain.model;

import java.util.UUID;

public class RoomId {
    private UUID roomId;

    public RoomId() {
    }

    public RoomId(UUID roomId){
        this.roomId = roomId;
    }

    public UUID getRoomId() {
        return roomId;
    }

    public void setRoomId(UUID roomId) {
        this.roomId = roomId;
    }
}
