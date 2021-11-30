package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.CreateGuestService;
import at.fhv.hotelsoftware.domain.api.GuestRepository;
import at.fhv.hotelsoftware.domain.model.Guest;
import at.fhv.hotelsoftware.domain.model.GuestId;
import at.fhv.hotelsoftware.view.form.CustomerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
public class CreateGuestServiceImpl implements CreateGuestService {

    @Autowired
    private GuestRepository guestRepository;

    @Transactional
    public GuestId createGuest(CustomerForm customerForm){
        GuestId guestId = new GuestId(UUID.randomUUID());

        Guest guest = Guest.builder().
                                    guestId(guestId).
                                    firstName(customerForm.getFname()).
                                    lastName(customerForm.getLname()).
                                    streetAddress(customerForm.getStreetAdr()).
                                    zip(customerForm.getZip()).
                                    city(customerForm.getCity()).
                                    country(customerForm.getCountry()).
                                    phoneNumber(customerForm.getPhoneNumber()).
                                    email(customerForm.getEmail()).
                                    build();

        guestRepository.addGuest(guest);

        return guestId;
    }
}
