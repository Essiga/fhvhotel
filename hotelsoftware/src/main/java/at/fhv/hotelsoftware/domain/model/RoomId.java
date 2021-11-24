package at.fhv.hotelsoftware.domain.model;

import java.util.UUID;

public class RoomId {
    private UUID id;

    public RoomId() {
    }

    public RoomId(UUID id){
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
