package at.fhv.hotelsoftware.domain.model;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomerData {

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
    private String agencyName;
    private String companyName;

    @Builder
    public CustomerData(GuestId guestId, String firstName, String lastName, String streetAddress, String zip, String city, String country, String phoneNumber, String email) {
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


    public static CustomerData fromGuest(Guest guest){
                return new CustomerData(guest.getGuestId(), guest.getFirstName(), guest.getLastName(), guest.getStreetAddress(), guest.getZip(), guest.getCity(), guest.getCountry(), guest.getPhoneNumber(), guest.getEmail());
    }
}
