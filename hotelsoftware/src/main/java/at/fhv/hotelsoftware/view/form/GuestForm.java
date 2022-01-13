package at.fhv.hotelsoftware.view.form;

import at.fhv.hotelsoftware.application.dto.BookingDataDTO;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
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

    public static GuestForm fromBookingData(BookingDataDTO bookingData) {

        GuestForm guestForm = new GuestForm();

        guestForm.setFirstName(bookingData.getFirstName());
        guestForm.setLastName(bookingData.getLastName());
        guestForm.setStreetAdr(bookingData.getStreetAdr());
        guestForm.setZip(bookingData.getZip());
        guestForm.setCity(bookingData.getCity());
        guestForm.setCountry(bookingData.getCountry());
        guestForm.setPhoneNumber(bookingData.getPhone());
        guestForm.setEmail(bookingData.getEmail());

        return guestForm;
    }
}
