package at.fhv.hotelsoftware.domain.api;

import at.fhv.hotelsoftware.domain.model.Guest;
import at.fhv.hotelsoftware.domain.model.valueobjects.GuestId;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface GuestRepository {

    void addGuest(Guest guest);
    Optional<Guest> findGuestById(GuestId guestId);
}
