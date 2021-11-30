package at.fhv.hotelsoftware.domain.model.valueobjects;


import at.fhv.hotelsoftware.domain.model.Guest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
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

    @Builder
    public GuestData(GuestId guestId, String firstName, String lastName, String street, String zip, String city, String country, String phoneNumber, String email) {
        this.guestId = guestId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = new Address(street, zip, city, country);
        this.phoneNumber = phoneNumber;
        this.email = email;
    }


    public static GuestData fromGuest(Guest guest){
        Address address = guest.getAddress();
        return new GuestData(guest.getGuestId(),
                guest.getFirstName(),
                guest.getLastName(),
                address.getStreet(),
                address.getZip(),
                address.getCity(),
                address.getCountry(),
                guest.getPhoneNumber(),
                guest.getEmail());
    }
}
