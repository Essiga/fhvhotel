import at.fhv.hotelsoftware.domain.model.Customer;
import at.fhv.hotelsoftware.domain.model.CustomerId;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface CustomerRepository {

    void addCustomer(Customer customer);
    Optional<Customer> findCustomerById(CustomerId customerId);
}
