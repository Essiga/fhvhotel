package at.fhv.hotelsoftware.domain.model;

public class TravelAgencyGuest extends Customer{

    private String agencyName;

    public TravelAgencyGuest(Long id, CustomerId customerId, String firstName, String lastName, String streetAddress, String zip, String city, String country, String phoneNumber, String email, String agencyName) {

        super(id, customerId, firstName, lastName, streetAddress, zip, city, country, phoneNumber, email);
        this.agencyName = agencyName;

    }
}
