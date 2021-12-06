package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.ViewGuestService;
import at.fhv.hotelsoftware.application.dto.GuestDTO;
import at.fhv.hotelsoftware.domain.api.GuestRepository;
import at.fhv.hotelsoftware.domain.model.Guest;
import at.fhv.hotelsoftware.domain.model.exceptions.GuestNotFoundException;
import at.fhv.hotelsoftware.domain.model.valueobjects.GuestId;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ViewGuestServiceTests {

    @Autowired
    ViewGuestService viewGuestService;

    @MockBean
    GuestRepository guestRepository;

    @Test
    public void given_guest_when_findguestbyid_then_returncorrectguest() throws GuestNotFoundException {
        //given
        GuestId guestId = new GuestId(UUID.randomUUID());

        Guest guest = Guest.builder().
                guestId(guestId).
                firstName("Fabian").
                lastName("Egartner").
                build();

        Mockito.when(guestRepository.findGuestById(guest.getGuestId())).thenReturn(Optional.of(guest));

        //when
        GuestDTO guestDTO = viewGuestService.findGuestById(guestId);

        //then
        assertEquals(guestDTO.getGuestId().getGuestId(), guest.getGuestId().getGuestId());
        assertEquals(guestDTO.getFirstName(), guest.getFirstName());
        assertEquals(guestDTO.getLastName(), guest.getLastName());
    }

    @Test
    public void given_novalidguestid_when_findguestbyid_then_throwguestnotfoundexception() {
        //given
        GuestId guestId = new GuestId(UUID.randomUUID());

        Guest guest = Guest.builder().
                guestId(guestId).
                firstName("Fabian").
                lastName("Egartner").
                build();

        Mockito.when(guestRepository.findGuestById(guest.getGuestId())).thenReturn(Optional.of(guest));

        //when...then
        assertThrows(GuestNotFoundException.class, () -> viewGuestService.findGuestById(new GuestId(UUID.randomUUID())));
    }
}
