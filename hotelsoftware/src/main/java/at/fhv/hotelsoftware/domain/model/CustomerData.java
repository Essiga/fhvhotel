package at.fhv.hotelsoftware.domain.model;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomerData {

    private CustomerId customerId;
    private String firstName;
    private String lastName;
    private String streetAddress;
    private String zip;
    private String city;
    private String country;
    private String phoneNumber;
    private String email;

    //TODO: Thaler: should we use inheritance for CustomerData?
    //What is the best way to do this?
    private String agencyName;
    private String companyName;

    @Builder
    public CustomerData(CustomerId customerId, String firstName, String lastName, String streetAddress, String zip, String city, String country, String phoneNumber, String email) {
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


    public static CustomerData fromCustomer(Customer customer){
                return new CustomerData(customer.getCustomerId(), customer.getFirstName(), customer.getLastName(), customer.getStreetAddress(), customer.getZip(), customer.getCity(), customer.getCountry(), customer.getPhoneNumber(), customer.getEmail());
    }
}
