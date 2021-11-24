package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.CreateCustomerService;
import at.fhv.hotelsoftware.domain.api.CustomerRepository;
import at.fhv.hotelsoftware.domain.model.Customer;
import at.fhv.hotelsoftware.domain.model.CustomerId;
import at.fhv.hotelsoftware.domain.model.Guest;
import at.fhv.hotelsoftware.view.form.CustomerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
public class CreateCustomerServiceImpl implements CreateCustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public CustomerId createCustomer(CustomerForm customerForm){
        CustomerId customerId = new CustomerId(UUID.randomUUID());

        Customer customer = Guest.builder().
                                    customerId(customerId).
                                    firstName(customerForm.getFname()).
                                    lastName(customerForm.getLname()).
                                    streetAddress(customerForm.getStreetAdr()).
                                    zip(customerForm.getZip()).
                                    city(customerForm.getCity()).
                                    country(customerForm.getCountry()).
                                    phoneNumber(customerForm.getPhoneNumber()).
                                    email(customerForm.getEmail()).
                                    build();

        customerRepository.addCustomer(customer);

        return customerId;
    }
}
