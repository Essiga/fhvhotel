package at.fhv.hotelsoftware.domain.model.valueobjects;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class RoomId {

    private UUID roomId;

    public RoomId(UUID id) {
        this.roomId = id;
    }
}
