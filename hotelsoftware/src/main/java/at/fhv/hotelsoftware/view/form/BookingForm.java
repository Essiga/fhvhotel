package at.fhv.hotelsoftware.view.form;

import at.fhv.hotelsoftware.domain.model.BookingStatus;
import at.fhv.hotelsoftware.domain.model.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookingForm {

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
    private String fromDate;
    private String toDate;


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

    public String getFromDate() {return fromDate;}

    public void setFromDate(String fromDate) {this.fromDate = fromDate;}

    public String getToDate() {return toDate;}

    public void setToDate(String toDate) {this.toDate = toDate;}



}
