package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.ViewCustomerService;
import at.fhv.hotelsoftware.application.dto.CustomerDTO;
import at.fhv.hotelsoftware.domain.model.exceptions.CustomerNotFoundException;
import at.fhv.hotelsoftware.domain.api.CustomerRepository;
import at.fhv.hotelsoftware.domain.model.Customer;
import at.fhv.hotelsoftware.domain.model.CustomerId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ViewCustomerServiceImpl implements ViewCustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public CustomerDTO findCustomerById(CustomerId customerId) throws CustomerNotFoundException {

        Optional<Customer> customerOpt = customerRepository.findCustomerById(customerId);

        if (customerOpt.isEmpty()){
            throw new CustomerNotFoundException("Customer with ID: " + customerId.getCustomerId().toString() + " not found");
        }

        return CustomerDTO.fromCustomer(customerOpt.get());
    }
}
