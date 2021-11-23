package at.fhv.hotelsoftware.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {

    protected Long id;
    protected CustomerId customerId;
    protected String voucherCode;
    protected String firstName;
    protected String lastName;
    protected String streetAddress;
    protected String zip;
    protected String city;
    protected String country;
    protected String phoneNumber;
    protected String email;


    private Customer(){}

    public Customer(Long id, CustomerId customerId, String voucherCode, String firstName, String lastName, String streetAddress, String zip, String city, String country, String phoneNumber, String email) {
        this.id = id;
        this.customerId = customerId;
        this.voucherCode = voucherCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.zip = zip;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
