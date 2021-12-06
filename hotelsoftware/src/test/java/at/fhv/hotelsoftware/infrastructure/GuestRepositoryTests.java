package at.fhv.hotelsoftware.infrastructure;

import at.fhv.hotelsoftware.domain.api.GuestRepository;
import at.fhv.hotelsoftware.domain.model.Guest;
import at.fhv.hotelsoftware.domain.model.valueobjects.GuestId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class GuestRepositoryTests {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private GuestRepository guestRepository;

    @Test
    void given_newguest_when_persistedflushedfetched_then_expectequalguest() {
        //given
        GuestId idExpected = new GuestId(UUID.randomUUID());
        String firstNameExpected = "Fabian";
        String lastNameExpected = "Egartner";

        Guest guestExpected = new Guest().builder().
                                guestId(idExpected).
                                firstName(firstNameExpected).
                                lastName(lastNameExpected).
                                street("Jahngasse 1").
                                zip("6850").
                                city("Dornbirn").
                                country("Austria").
                                phoneNumber("066023874").
                                email("abc@test.de").
                                build();

        //when
        guestRepository.addGuest(guestExpected);
        em.flush();
        Optional<Guest> guestActual = guestRepository.findGuestById(idExpected);

        //then
        assertEquals(idExpected.getGuestId(), guestActual.get().getGuestId().getGuestId());
        assertEquals(firstNameExpected, guestActual.get().getFirstName());
        assertEquals(lastNameExpected, guestActual.get().getLastName());
    }
}
