package at.fhv.hotelsoftware.view.form;

import at.fhv.hotelsoftware.application.dto.BookingDataDTO;
import at.fhv.hotelsoftware.application.dto.GuestDTO;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class GuestForm {

    @NotEmpty(message = "First name cannot be empty")
    private String firstName;

    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;

    @NotEmpty(message = "Street address cannot be empty")
    private String streetAdr;

    @NotEmpty(message = "Zip cannot be empty")
    private String zip;

    @NotEmpty(message = "City cannot be empty")
    private String city;

    @NotEmpty(message = "Country cannot be empty")
    private String country;

    @NotEmpty(message = "Phone number cannot be empty")
    private String phoneNumber;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid syntax")
    private String email;

    @Builder
    public GuestForm(@NotEmpty(message = "First name cannot be empty") String firstName, @NotEmpty(message = "Last name cannot be empty") String lastName, @NotEmpty(message = "Street address cannot be empty") String streetAdr, @NotEmpty(message = "Zip cannot be empty") String zip, @NotEmpty(message = "City cannot be empty") String city, @NotEmpty(message = "Country cannot be empty") String country, @NotEmpty(message = "Phone number cannot be empty") String phoneNumber, @NotEmpty(message = "Email cannot be empty") @Email(message = "Invalid syntax") String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAdr = streetAdr;
        this.zip = zip;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    //    public static GuestForm fromBookingData(BookingDataDTO bookingData) {
//
//        GuestForm guestForm = new GuestForm();
//
//        guestForm.setFirstName(bookingData.getFirstName());
//        guestForm.setLastName(bookingData.getLastName());
//        guestForm.setStreetAdr(bookingData.getStreetAdr());
//        guestForm.setZip(bookingData.getZip());
//        guestForm.setCity(bookingData.getCity());
//        guestForm.setCountry(bookingData.getCountry());
//        guestForm.setPhoneNumber(bookingData.getPhone());
//        guestForm.setEmail(bookingData.getEmail());
//
//        return guestForm;
//    }

    public static GuestForm fromGuestDTO(GuestDTO guestDTO) {
        return GuestForm.builder()
                .firstName(guestDTO.getFirstName())
                .lastName(guestDTO.getLastName())
                .streetAdr(guestDTO.getStreetAddress())
                .zip(guestDTO.getZip())
                .city(guestDTO.getCity())
                .country(guestDTO.getCountry())
                .phoneNumber(guestDTO.getPhoneNumber())
                .email(guestDTO.getEmail()).build();
    }
}
