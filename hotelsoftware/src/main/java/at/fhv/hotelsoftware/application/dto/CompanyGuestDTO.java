package at.fhv.hotelsoftware.application.dto;

import at.fhv.hotelsoftware.domain.model.CustomerId;

public class CompanyGuestDTO extends CustomerDTO{
    private String companyName;

    public CompanyGuestDTO(Long id, CustomerId customerId, String voucherCode, String firstName, String lastName, String streetAddress, String zip, String city, String country, String phoneNumber, String email, String companyName) {
        super(id, customerId, voucherCode, firstName, lastName, streetAddress, zip, city, country, phoneNumber, email);
        this.companyName = companyName;
    }
}
