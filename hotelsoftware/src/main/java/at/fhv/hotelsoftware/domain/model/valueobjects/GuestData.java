package at.fhv.hotelsoftware.domain.model.valueobjects;


import at.fhv.hotelsoftware.domain.model.Guest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
public class GuestData {

    private GuestId guestId;
    private String firstName;
    private String lastName;
    private Address address;
    private String phoneNumber;
    private String email;
    private GuestType guestType;
    private String agencyName;
    private String companyName;

    public GuestData() {
        firstName = "";
        lastName = "";
        address = new Address();
        phoneNumber = "";
        email = "";
    }

    @Builder
    public GuestData(GuestId guestId, String firstName, String lastName, String street, String zip, String city, String country, String phoneNumber, String email, GuestType guestType, String agencyName, String companyName) {
        this.guestId = guestId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = new Address(street, zip, city, country);
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.guestType = guestType;
        this.agencyName = agencyName;
        this.companyName = companyName;
    }

    @Builder
    public GuestData(GuestId guestId, String firstName, String lastName, Address address, String phoneNumber, String email, GuestType guestType, String agencyName, String companyName) {
        this.guestId = guestId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.guestType = guestType;
        this.agencyName = agencyName;
        this.companyName = companyName;
    }

    public static GuestData fromGuest(Guest guest) {

        Address address = guest.getAddress();

        return new GuestData(guest.getGuestId(),
                guest.getFirstName(),
                guest.getLastName(),
                address.getStreet(),
                address.getZip(),
                address.getCity(),
                address.getCountry(),
                guest.getPhoneNumber(),
                guest.getEmail(),
                guest.getGuestType(),
                guest.getAgencyName(),
                guest.getCompanyName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuestData guestData = (GuestData) o;
        return Objects.equals(guestId.getGuestId(), guestData.guestId.getGuestId()) && Objects.equals(firstName, guestData.firstName) && Objects.equals(lastName, guestData.lastName) && Objects.equals(address, guestData.address) && Objects.equals(phoneNumber, guestData.phoneNumber) && Objects.equals(email, guestData.email) && guestType == guestData.guestType && Objects.equals(agencyName, guestData.agencyName) && Objects.equals(companyName, guestData.companyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guestId, firstName, lastName, address, phoneNumber, email, guestType, agencyName, companyName);
    }
}
