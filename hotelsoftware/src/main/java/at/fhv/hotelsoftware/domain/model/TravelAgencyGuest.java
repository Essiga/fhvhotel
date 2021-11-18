package at.fhv.hotelsoftware.domain.model;

public class TravelAgencyGuest extends Customer{

    private String agencyName;

    public TravelAgencyGuest(Long id, CustomerId customerId, String voucherCode, String firstName, String lastName, String streetAddress, String zip, String city, String country, String phoneNumber, String email) {
        super(id, customerId, voucherCode, firstName, lastName, streetAddress, zip, city, country, phoneNumber, email);
    }
}
