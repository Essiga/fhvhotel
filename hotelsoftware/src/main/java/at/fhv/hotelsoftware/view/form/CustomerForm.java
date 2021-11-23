package at.fhv.hotelsoftware.view.form;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CustomerForm {
    @NotEmpty(message = "First name cannot be empty")
    private String fname;
    //
    @NotEmpty(message = "Last name cannot be empty")
    private String lname;

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


}
