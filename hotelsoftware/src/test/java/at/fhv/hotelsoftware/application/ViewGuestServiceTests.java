package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.ViewGuestService;
import at.fhv.hotelsoftware.application.dto.GuestDTO;
import at.fhv.hotelsoftware.domain.api.GuestRepository;
import at.fhv.hotelsoftware.domain.model.Guest;
import at.fhv.hotelsoftware.domain.model.Room;
import at.fhv.hotelsoftware.domain.model.exceptions.GuestNotFoundException;
import at.fhv.hotelsoftware.domain.model.exceptions.NotEnoughRoomsException;
import at.fhv.hotelsoftware.domain.model.valueobjects.GuestId;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
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

        //TODO Test all fields
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

    @Test
    public void given_guestsexists_when_findallguest_then_expectallguests() throws GuestNotFoundException {
        List<Guest> list = new ArrayList<>();

        GuestId guestId1 = new GuestId(UUID.randomUUID());
        GuestId guestId2 = new GuestId(UUID.randomUUID());

        Guest guest1 = Guest.builder().
                guestId(guestId1).
                firstName("Alp").
                lastName("Arslan").
                build();

        Guest guest2 = Guest.builder().
                guestId(guestId1).
                firstName("Tobias").
                lastName("Kurz").
                build();

        list.add(guest1);
        list.add(guest2);

        Mockito.when(guestRepository.findAllGuest()).thenReturn(list);

        // when
        List<GuestDTO> allGuests = viewGuestService.findAllGuest();

        // then
        for(int i = 0; i < list.size(); i++){
            assertEquals(allGuests.get(i).getGuestId(), list.get(i).getGuestId());
        }
    }

    @Test
    public void given_guestsexists_when_findallguest_then_expectguestnotfoundexception() throws GuestNotFoundException {
        List<Guest> list = new ArrayList<>();

        Mockito.when(guestRepository.findAllGuest()).thenReturn(list);

        // when ... then
        assertThrows(GuestNotFoundException.class, () -> viewGuestService.findAllGuest());

    }
}
