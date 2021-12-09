package at.fhv.hotelsoftware.application.api;

import at.fhv.hotelsoftware.application.dto.GuestDTO;
import at.fhv.hotelsoftware.domain.model.valueobjects.GuestId;
import at.fhv.hotelsoftware.domain.model.exceptions.GuestNotFoundException;

import java.util.List;

public interface ViewGuestService {

    GuestDTO findGuestById(GuestId guestId) throws GuestNotFoundException;
    List<GuestDTO> findAllGuest() throws GuestNotFoundException;
}
