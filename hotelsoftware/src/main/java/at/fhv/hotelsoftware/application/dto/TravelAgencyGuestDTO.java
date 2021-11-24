package at.fhv.hotelsoftware.application.dto;

import at.fhv.hotelsoftware.domain.model.CustomerId;

public class TravelAgencyGuestDTO extends CustomerDTO {

    private String agencyName;

    public TravelAgencyGuestDTO(CustomerId customerId, String firstName, String lastName, String streetAddress, String zip, String city, String country, String phoneNumber, String email, String agencyName) {

        super(customerId, firstName, lastName, streetAddress, zip, city, country, phoneNumber, email);
        this.agencyName = agencyName;

    }
}
