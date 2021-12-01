package at.fhv.hotelsoftware.application.api;

import at.fhv.hotelsoftware.application.dto.GuestDTO;
import at.fhv.hotelsoftware.domain.model.valueobjects.GuestId;
import at.fhv.hotelsoftware.domain.model.exceptions.GuestNotFoundException;

public interface ViewGuestService {

    GuestDTO findGuestById(GuestId guestId) throws GuestNotFoundException;
}
