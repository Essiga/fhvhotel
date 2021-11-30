package at.fhv.hotelsoftware.domain.model;


public class Guest extends Customer {


    public Guest(CustomerId customerId, String firstName, String lastName, String streetAddress, String zip, String city, String country, String phoneNumber, String email) {
        super(customerId, firstName, lastName, streetAddress, zip, city, country, phoneNumber, email);
    }
}
