package at.fhv.hotelsoftware.application.dto;

import at.fhv.hotelsoftware.domain.model.CustomerId;
import lombok.Builder;
import lombok.Data;



@Data
public class CustomerDTO {

    private Long id;
    private CustomerId customerId;
    private String voucherCode;
    private String fname;
    private String lname;
    private String streetAddress;
    private String zip;
    private String city;
    private String country;
    private String phoneNumber;
    private String email;

    public CustomerDTO(){}
}
