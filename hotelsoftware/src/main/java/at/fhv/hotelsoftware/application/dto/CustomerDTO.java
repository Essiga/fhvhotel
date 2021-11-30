package at.fhv.hotelsoftware.application.dto;

import at.fhv.hotelsoftware.domain.model.Customer;
import at.fhv.hotelsoftware.domain.model.CustomerId;
import lombok.Builder;
import lombok.Data;

//TODO: only one DTO with all fields

@Data
public class CustomerDTO {

    protected Long id;
    protected CustomerId customerId;
    protected String firstName;
    protected String lastName;
    protected String streetAddress;
    protected String zip;
    protected String city;
    protected String country;
    protected String phoneNumber;
    protected String email;

    public CustomerDTO(){}

    public CustomerDTO(CustomerId customerId, String firstName, String lastName, String streetAddress, String zip, String city, String country, String phoneNumber, String email) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.zip = zip;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public static CustomerDTO fromCustomer(Customer customer) {
        return new CustomerDTO(customer.getCustomerId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getStreetAddress(),
                customer.getZip(),
                customer.getCity(),
                customer.getCountry(),
                customer.getPhoneNumber(),
                customer.getEmail());
    }
}
