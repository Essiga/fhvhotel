package at.fhv.hotelsoftware.application.dto;

import at.fhv.hotelsoftware.domain.model.CustomerId;

public class GuestDTO extends CustomerDTO {
    public GuestDTO(CustomerId customerId, String firstName, String lastName, String streetAddress, String zip, String city, String country, String phoneNumber, String email) {
        super(customerId, firstName, lastName, streetAddress, zip, city, country, phoneNumber, email);
    }
}
