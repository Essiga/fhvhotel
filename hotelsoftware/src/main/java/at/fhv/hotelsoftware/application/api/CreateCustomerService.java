package at.fhv.hotelsoftware.application.api;

import at.fhv.hotelsoftware.domain.model.Customer;
import at.fhv.hotelsoftware.domain.model.CustomerId;
import at.fhv.hotelsoftware.view.form.CustomerForm;

import java.util.Optional;

public interface CreateCustomerService {

    CustomerId createCustomer(CustomerForm customerForm);
}
