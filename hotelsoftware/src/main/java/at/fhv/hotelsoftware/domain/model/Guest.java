package at.fhv.hotelsoftware.domain.model;

public class Guest extends Customer {

    public Guest(Long id, CustomerId customerId, String firstName, String lastName, String streetAddress, String zip, String city, String country, String phoneNumber, String email) {
        super(id, customerId, firstName, lastName, streetAddress, zip, city, country, phoneNumber, email);
    }
}
