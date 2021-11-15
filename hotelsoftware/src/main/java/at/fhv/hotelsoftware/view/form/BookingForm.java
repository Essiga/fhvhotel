package at.fhv.hotelsoftware.view.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class BookingForm {

    @NotEmpty(message = "Fname cannot be empty")
    @Size(min = 1, message = "Fname must have at least 5 characters")
    private String fname;

    private String lname;
    private String voucherCode;
    private String streetAdr;
    private String zip;
    private String city;
    private String country;
    private String phoneNumber;
    private String email;
    private Integer singleRoomCount;
    private Integer doubleRoomCount;
    private Integer luxusRoomCount;
    private String checkInDate;
    private String checkOutDate;

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

    public Integer getSingleRoomCount() {
        return singleRoomCount;
    }

    public void setSingleRoomCount(Integer singleRoomCount) {
        this.singleRoomCount = singleRoomCount;
    }

    public Integer getDoubleRoomCount() {
        return doubleRoomCount;
    }

    public void setDoubleRoomCount(Integer doubleRoomCount) {
        this.doubleRoomCount = doubleRoomCount;
    }

    public Integer getLuxusRoomCount() {
        return luxusRoomCount;
    }

    public void setLuxusRoomCount(Integer luxusRoomCount) {
        this.luxusRoomCount = luxusRoomCount;
    }

    public String getCheckInDate() {return checkInDate;}

    public void setCheckInDate(String checkInDate) {this.checkInDate = checkInDate;}

    public String getCheckOutDate() {return checkOutDate;}

    public void setCheckOutDate(String checkOutDate) {this.checkOutDate = checkOutDate;}
}
