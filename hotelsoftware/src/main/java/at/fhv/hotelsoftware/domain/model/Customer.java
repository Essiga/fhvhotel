package at.fhv.hotelsoftware.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
public class Customer {

    protected Long id;
    protected CustomerId customerId;
    protected String firstName;
    protected String lastName;
    protected String streetAddress;
    protected String zip;
    protected String city;
    protected String country;
    protected String phoneNumber;
    protected String email;

    //add agency name and company name
    //add type



    private Customer(){}

    @Builder
    public Customer(CustomerId customerId, String firstName, String lastName, String streetAddress, String zip, String city, String country, String phoneNumber, String email) {

        this.customerId = customerId;
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
