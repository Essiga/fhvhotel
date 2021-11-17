package at.fhv.hotelsoftware.domain.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Guest {

    private Long id;
    private GuestId guestId;
    private GuestType guestType;  // radio button in form
    private String groupOrCompanyName;
    private String voucherCode;
    private String fname;
    private String lname;
    private String streetAddress;
    private String zip;
    private String city;
    private String country;
    private String phoneNumber;
    private String email;

    public static Builder builder() {return new Builder();}

    private Guest() {}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GuestId getGuestId() {
        return guestId;
    }

    public void setGuestId(GuestId guestId) {
        this.guestId = guestId;
    }

    public GuestType getCustomerType() {
        return guestType;
    }

    public void setCustomerType(GuestType guestType) {
        this.guestType = guestType;
    }

    public String getGroupOrCompanyName() {
        return groupOrCompanyName;
    }

    public void setGroupOrCompanyName(String groupOrCompanyName) {
        this.groupOrCompanyName = groupOrCompanyName;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

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

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
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

    @Override
    public String toString() {
        return "Guest{" +
                "id=" + id +
                ", guestId=" + guestId +
                ", guestType=" + guestType +
                ", groupOrCompanyName='" + groupOrCompanyName + '\'' +
                ", voucherCode='" + voucherCode + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", zip='" + zip + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public static class Builder {

        private final Guest instance;


        public Builder() {this.instance = new Guest();}


        public Builder withLongId(Long id) {
            this.instance.id = id;
            return this;
        }

        public Builder withGuestId(GuestId guestId) {
            this.instance.guestId = guestId;
            return this;
        }

        public Builder withGuestType(GuestType guestType) {
            this.instance.guestType = guestType;
            return this;
        }

        public Builder withGroupOrCompanyName(String groupOrCompanyName) {
            this.instance.groupOrCompanyName = groupOrCompanyName;
            return this;
        }

        public Builder withVoucherCode(String voucherCode) {
            this.instance.voucherCode = voucherCode;
            return this;
        }

        public Builder withFirstName(String fname) {
            this.instance.fname = fname;
            return this;
        }

        public Builder withLastName(String lname) {
            this.instance.lname = lname;
            return this;
        }

        public Builder withStreetAddress(String streetAddress) {
            this.instance.streetAddress = streetAddress;
            return this;
        }

        public Builder withZip(String zip) {
            this.instance.zip = zip;
            return this;
        }

        public Builder withCity(String city) {
            this.instance.city = city;
            return this;
        }

        public Builder withCountry(String country) {
            this.instance.country = country;
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber) {
            this.instance.phoneNumber = phoneNumber;
            return this;
        }

        public Builder withEmail(String email) {
            this.instance.email = email;
            return this;
        }

        public Guest build() {return this.instance;}
    }
}
