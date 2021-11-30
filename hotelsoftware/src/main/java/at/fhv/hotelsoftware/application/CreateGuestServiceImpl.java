package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.CreateGuestService;
import at.fhv.hotelsoftware.domain.api.GuestRepository;
import at.fhv.hotelsoftware.domain.model.Guest;
import at.fhv.hotelsoftware.domain.model.valueobjects.GuestId;
import at.fhv.hotelsoftware.view.form.GuestForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
public class CreateGuestServiceImpl implements CreateGuestService {

    @Autowired
    private GuestRepository guestRepository;

    @Transactional
    public GuestId createGuest(GuestForm guestForm) {

        GuestId guestId = new GuestId(UUID.randomUUID());

        Guest guest = Guest.builder().
                                    guestId(guestId).
                                    firstName(guestForm.getFirstName()).
                                    lastName(guestForm.getLastName()).
                                    street(guestForm.getStreetAdr()).
                                    zip(guestForm.getZip()).
                                    city(guestForm.getCity()).
                                    country(guestForm.getCountry()).
                                    phoneNumber(guestForm.getPhoneNumber()).
                                    email(guestForm.getEmail()).
                                    build();

        guestRepository.addGuest(guest);

        return guestId;
    }
}
