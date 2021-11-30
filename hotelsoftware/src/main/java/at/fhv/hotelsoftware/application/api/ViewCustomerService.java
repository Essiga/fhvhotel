package at.fhv.hotelsoftware.application.api;

import at.fhv.hotelsoftware.application.dto.CustomerDTO;
import at.fhv.hotelsoftware.domain.model.exceptions.CustomerNotFoundException;
import at.fhv.hotelsoftware.domain.model.CustomerId;

public interface ViewCustomerService {

    CustomerDTO findCustomerById(CustomerId customerId) throws CustomerNotFoundException;
}
