package at.fhv.hotelsoftware.domain.model;

import java.util.UUID;

public class GuestId {

    private UUID guestId;


    public GuestId() {}

    public GuestId(UUID guestId) {this.guestId = guestId;}


    public UUID getGuestId() {return guestId;}
}
