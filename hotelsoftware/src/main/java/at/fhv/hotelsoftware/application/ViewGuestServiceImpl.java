package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.ViewGuestService;
import at.fhv.hotelsoftware.application.dto.GuestDTO;
import at.fhv.hotelsoftware.domain.model.Guest;
import at.fhv.hotelsoftware.domain.model.valueobjects.GuestId;
import at.fhv.hotelsoftware.domain.model.exceptions.GuestNotFoundException;
import at.fhv.hotelsoftware.domain.api.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ViewGuestServiceImpl implements ViewGuestService {

    @Autowired
    private GuestRepository guestRepository;

    public GuestDTO findGuestById(GuestId guestId) throws GuestNotFoundException {
        Optional<Guest> guestOpt = guestRepository.findGuestById(guestId);

        if (guestOpt.isEmpty()){
            throw new GuestNotFoundException("Guest with ID: " + guestId.getGuestId().toString() + " not found");
        }

        Guest guest = guestOpt.get();

        return GuestDTO.fromGuest(guest);
    }

    public List<GuestDTO> findAllGuest() throws GuestNotFoundException {
        List<Guest> allGuests = guestRepository.findAllGuests();

        if(allGuests.isEmpty()){
            throw new GuestNotFoundException("No guests have been found");
        }

        return GuestDTO.fromGuestList(allGuests);
    }
}
