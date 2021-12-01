package at.fhv.hotelsoftware.domain.model;

public class TravelAgencyGuest extends Customer{

    private String agencyName;

    public TravelAgencyGuest(CustomerId customerId, String firstName, String lastName, String streetAddress, String zip, String city, String country, String phoneNumber, String email, String agencyName) {

        super(customerId, firstName, lastName, streetAddress, zip, city, country, phoneNumber, email);
        this.agencyName = agencyName;

    }
}
