package at.fhv.hotelsoftware.domain.model;

public class CompanyGuest extends Customer{

    private String companyName;

    public CompanyGuest(CustomerId customerId, String firstName, String lastName, String streetAddress, String zip, String city, String country, String phoneNumber, String email) {
        super(customerId, firstName, lastName, streetAddress, zip, city, country, phoneNumber, email);
    }
}
