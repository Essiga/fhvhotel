package at.fhv.hotelsoftware.domain.model;

import java.util.UUID;

public class CustomerId {

    private UUID guestId;


    public CustomerId() {}

    public CustomerId(UUID guestId) {this.guestId = guestId;}


    public UUID getGuestId() {return guestId;}
}
