package at.fhv.hotelsoftware.application.dto;

import at.fhv.hotelsoftware.domain.model.Guest;
import at.fhv.hotelsoftware.domain.model.GuestId;
import at.fhv.hotelsoftware.domain.model.GuestType;
import lombok.Data;

//TODO: only one DTO with all fields

@Data
public class GuestDTO {

    protected Long id;
    protected GuestId guestId;
    protected String firstName;
    protected String lastName;
    protected String streetAddress;
    protected String zip;
    protected String city;
    protected String country;
    protected String phoneNumber;
    protected String email;
    private GuestType guestType;
    private String companyName;
    private String agencyName;

    public GuestDTO(){}

    public GuestDTO(GuestId guestId, String firstName, String lastName, String streetAddress, String zip, String city, String country, String phoneNumber, String email) {
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

    public static GuestDTO fromGuest(Guest guest) {
        return new GuestDTO(guest.getGuestId(),
                guest.getFirstName(),
                guest.getLastName(),
                guest.getStreetAddress(),
                guest.getZip(),
                guest.getCity(),
                guest.getCountry(),
                guest.getPhoneNumber(),
                guest.getEmail());
    }
}
