package at.fhv.hotelsoftware.view.form;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CustomerForm {
    @NotEmpty(message = "First name cannot be empty")
    private String fname;

    @NotEmpty(message = "Last name cannot be empty")
    private String lname;

    @NotEmpty(message = "Voucher code cannot be empty")
    @Size(min = 8, message = "Voucher code must have at least 8 characters")
    private String voucherCode;

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

    public CustomerForm(){}


    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public String getStreetAdr() {
        return streetAdr;
    }

    public void setStreetAdr(String streetAdr) {
        this.streetAdr = streetAdr;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
