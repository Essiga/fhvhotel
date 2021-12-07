package at.fhv.hotelsoftware.domain;

import at.fhv.hotelsoftware.domain.model.Guest;
import at.fhv.hotelsoftware.domain.model.valueobjects.Address;
import at.fhv.hotelsoftware.domain.model.valueobjects.GuestId;
import at.fhv.hotelsoftware.domain.model.valueobjects.GuestType;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GuestTest {
    @Test
    public void given_properties_when_new_guest_then_reflectsprops() {
        //given
        GuestId guestId = new GuestId(UUID.randomUUID());
        String firstName = "Achim";
        String lastName = "Unterkofler";
        String phoneNumber = "06504304902";
        String email = "achim@gmail.com";
        String street = "Achstraße";
        String zip = "6845";
        String city = "Hohenems";
        String country = "Austria";

        //when
        Guest guest = new Guest(guestId, firstName, lastName,
                street, zip, city, country, phoneNumber, email);

        //then
        assertEquals(guestId, guest.getGuestId());
        assertEquals(firstName, guest.getFirstName());
        assertEquals(lastName, guest.getLastName());
        assertEquals(street, guest.getAddress().getStreet());
        assertEquals(zip, guest.getAddress().getZip());
        assertEquals(city, guest.getAddress().getCity());
        assertEquals(country, guest.getAddress().getCountry());
        assertEquals(phoneNumber, guest.getPhoneNumber());
        assertEquals(email, guest.getEmail());
    }

    @Test
    public void given_properties_when_new_companyoragency_then_reflectsprops() {
        //given
        GuestId guestId = new GuestId(UUID.randomUUID());
        String firstName = "Achim";
        String lastName = "Unterkofler";
        String phoneNumber = "06504304902";
        String email = "achim@gmail.com";
        GuestType guestType = GuestType.TRAVEL_AGENCY;
        String companyName = "Gold";
        String agencyName = "Agency";

        String street = "Achstraße";
        String zip = "6845";
        String city = "Hohenems";
        String country = "Austria";
        Address address = new Address(street, zip, city, country);

        //when
        Guest guest = new Guest(guestId, firstName, lastName, address,
                phoneNumber, email, guestType, companyName, agencyName);

        //then
        assertEquals(guestId, guest.getGuestId());
        assertEquals(firstName, guest.getFirstName());
        assertEquals(lastName, guest.getLastName());
        assertEquals(street, guest.getAddress().getStreet());
        assertEquals(zip, guest.getAddress().getZip());
        assertEquals(city, guest.getAddress().getCity());
        assertEquals(country, guest.getAddress().getCountry());
        assertEquals(phoneNumber, guest.getPhoneNumber());
        assertEquals(email, guest.getEmail());
        assertEquals(guestType, guest.getGuestType());
        assertEquals(companyName, guest.getCompanyName());
        assertEquals(agencyName, guest.getAgencyName());
    }
}
