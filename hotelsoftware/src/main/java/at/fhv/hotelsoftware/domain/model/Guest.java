package at.fhv.hotelsoftware.domain.model;

import at.fhv.hotelsoftware.domain.model.valueobjects.Address;
import at.fhv.hotelsoftware.domain.model.valueobjects.GuestId;
import at.fhv.hotelsoftware.domain.model.valueobjects.GuestType;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Guest {

    private Long id;
    private GuestId guestId;
    private String firstName;
    private String lastName;
    private Address address;
    private String phoneNumber;
    private String email;
    private GuestType guestType;
    private String companyName;
    private String agencyName;

    @Builder
    public Guest(GuestId guestId, String firstName, String lastName, String street, String zip, String city, String country, String phoneNumber, String email) {
        this.guestId = guestId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = new Address(street, zip, city, country);
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    @Builder
    public Guest(GuestId guestId, String firstName, String lastName, Address address, String phoneNumber, String email, GuestType guestType, String companyName, String agencyName) {
        this.guestId = guestId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.guestType = guestType;
        this.companyName = companyName;
        this.agencyName = agencyName;
    }
}
