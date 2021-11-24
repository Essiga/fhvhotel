package at.fhv.hotelsoftware.application.dto;

import at.fhv.hotelsoftware.domain.model.CustomerId;

public class CompanyGuestDTO extends CustomerDTO{
    private String companyName;

    public CompanyGuestDTO(CustomerId customerId, String firstName, String lastName, String streetAddress, String zip, String city, String country, String phoneNumber, String email, String companyName) {
        super(customerId, firstName, lastName, streetAddress, zip, city, country, phoneNumber, email);
        this.companyName = companyName;
    }
}
