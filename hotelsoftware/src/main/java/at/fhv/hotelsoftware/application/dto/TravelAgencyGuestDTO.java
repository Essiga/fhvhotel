package at.fhv.hotelsoftware.application.dto;

import at.fhv.hotelsoftware.domain.model.CustomerId;

public class TravelAgencyGuestDTO extends CustomerDTO {

    private String agencyName;

    public TravelAgencyGuestDTO(Long id, CustomerId customerId, String voucherCode, String firstName, String lastName, String streetAddress, String zip, String city, String country, String phoneNumber, String email, String agencyName) {

        super(id, customerId, voucherCode, firstName, lastName, streetAddress, zip, city, country, phoneNumber, email);
        this.agencyName = agencyName;

    }
}
