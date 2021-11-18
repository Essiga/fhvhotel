package at.fhv.hotelsoftware.domain.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TravelAgencyGuest extends Customer{

    private Long id;
    private CustomerId customerId;
    private String voucherCode;
    private String agencyName;
    private String firstName;
    private String lastName;
    private String streetAddress;
    private String zip;
    private String city;
    private String country;
    private String phoneNumber;
    private String email;
}
