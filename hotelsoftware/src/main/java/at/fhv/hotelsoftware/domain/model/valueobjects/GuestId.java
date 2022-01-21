package at.fhv.hotelsoftware.domain.model.valueobjects;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class GuestId {

    private UUID guestId;

    public GuestId(UUID guestId) {
        this.guestId = guestId;
    }
    public GuestId(String guestId) {
        this.guestId = UUID.fromString(guestId);
    }
}
