package at.fhv.hotelsoftware.domain.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Guest extends Customer {

    private Long id;
    private CustomerId customerId;
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

    public Guest() {}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerId getGuestId() {
        return customerId;
    }

    public void setGuestId(CustomerId customerId) {
        this.customerId = customerId;
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
                ", guestId=" + customerId +
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

        public Builder withGuestId(CustomerId customerId) {
            this.instance.customerId = customerId;
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
