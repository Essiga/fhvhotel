package at.fhv.hotelsoftware.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
public class Guest {

    private Long id;
    private GuestId guestId;
    private String firstName;
    private String lastName;
    private String streetAddress;
    private String zip;
    private String city;
    private String country;
    private String phoneNumber;
    private String email;
    private GuestType guestType;
    private String companyName;
    private String agencyName;


    private Guest(){}

    @Builder
    public Guest(GuestId guestId, String firstName, String lastName, String streetAddress, String zip, String city, String country, String phoneNumber, String email) {

        this.guestId = guestId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.zip = zip;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
