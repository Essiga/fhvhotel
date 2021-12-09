package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.domain.api.GuestRepository;
import at.fhv.hotelsoftware.domain.model.Guest;
import at.fhv.hotelsoftware.domain.model.valueobjects.GuestId;
import at.fhv.hotelsoftware.view.form.GuestForm;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class CreateGuestTest {
    @InjectMocks
    @Autowired
    private CreateGuestServiceImpl createGuestService;

    @Captor
    private ArgumentCaptor<Guest> guestCaptor;

    @MockBean
    private GuestRepository guestRepository;

    @Test
    public void given_guestform_when_createguest_then_avalidguestid(){
        //given
        String firstName = "Achim";
        String lastName = "Unterkofler";
        String phoneNumber = "06504304902";
        String email = "achim@gmail.com";
        String street = "Achstraße";
        String zip = "6845";
        String city = "Hohenems";
        String country = "Austria";

        GuestForm guestForm = new GuestForm();
        guestForm.setCity(city);
        guestForm.setCountry(country);
        guestForm.setEmail(email);
        guestForm.setFirstName(firstName);
        guestForm.setLastName(lastName);
        guestForm.setPhoneNumber(phoneNumber);
        guestForm.setStreetAdr(street);
        guestForm.setZip(zip);

        GuestId guestIdExpected = createGuestService.createGuest(guestForm);

        Mockito.verify(guestRepository).addGuest(guestCaptor.capture());
        Guest guest = guestCaptor.getValue();

        assertEquals(guestIdExpected, guest.getGuestId());
    }
}
